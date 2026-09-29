package top.fancyflow.stardewaccessories.datagen;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModBlocks;

// 用 datagen 生成世界生成注册表：
//   ① ConfiguredFeature  生成什么（矿脉大小、替换哪些方块）
//   ② PlacedFeature      在哪生成（每区块几次、什么高度）
//   ③ BiomeModifier      塞进哪些群系、哪个生成阶段
//
// 新增矿石时，只需要往下面的 ORE_VEINS 列表里加一条，三个注册表会自动跟着生成。
public class ModWorldGen extends DatapackBuiltinEntriesProvider {

    // ===================== 矿石生成配置区（加矿石只改这里） =====================
    // 一条 = 一种"矿脉"。石头版和深板岩版写成 targets 列表，会生成到同一个 ConfiguredFeature 里，
    // 矿脉可以自然跨越石头/深板岩交界。
    //   name          特征名，同时用作 3 个 JSON 的文件名（configured / placed / biome_modifier）
    //   targets       多个"替换目标"：在 replaceable 标记的方块里生成对应矿石
    //   veinSize      一条矿脉最多几块
    //   veinsPerChunk 每个区块尝试生成几次
    //   height        高度分布规则
    //   discardChanceOnAirExposure  挨着空气时被丢弃的概率（0=可裸露在洞穴，1=完全埋藏）
    private record OreTarget(TagKey<Block> replaceable, Supplier<Block> ore) {
    }

    private record OreVein(
            String name,
            List<OreTarget> targets,
            int veinSize,
            int veinsPerChunk,
            PlacementModifier height,
            float discardChanceOnAirExposure
    ) {
        // 便捷构造：不写空气暴露参数时默认 0.0
        OreVein(String name, List<OreTarget> targets,
                int veinSize, int veinsPerChunk, PlacementModifier height) {
            this(name, targets, veinSize, veinsPerChunk, height, 0.0F);
        }
    }

    // 石头版 + 深板岩版写进同一个 target 列表的快捷构造
    private static List<OreTarget> stoneAndDeepslate(Supplier<Block> stoneOre, Supplier<Block> deepslateOre) {
        return List.of(
                new OreTarget(BlockTags.STONE_ORE_REPLACEABLES, stoneOre),
                new OreTarget(BlockTags.DEEPSLATE_ORE_REPLACEABLES, deepslateOre));
    }

    private static final List<OreVein> ORE_VEINS = List.of(
            // 星陨矿石：稀有，深层为主，浅层零星；一次只生成一个方块
            new OreVein("starshard_ore_deep",
                    stoneAndDeepslate(ModBlocks.STARSHARD_ORE, ModBlocks.DEEPSLATE_STARSHARD_ORE),
                    1, 4,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                    0.25F),
            new OreVein("starshard_ore_shallow",
                    stoneAndDeepslate(ModBlocks.STARSHARD_ORE, ModBlocks.DEEPSLATE_STARSHARD_ORE),
                    1, 1,
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(24), VerticalAnchor.absolute(64)),
                    0.25F),
            // 彩晶矿石：与钻石主矿脉相当，深层
            new OreVein("prism_ore",
                    stoneAndDeepslate(ModBlocks.PRISM_ORE, ModBlocks.DEEPSLATE_PRISM_ORE),
                    3, 7,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)),
                    0.5F)
    );
    // ==========================================================================

    // 总装配：往哪几个注册表里写
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModWorldGen::configuredFeatures)
            .add(Registries.PLACED_FEATURE, ModWorldGen::placedFeatures)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModWorldGen::biomeModifiers);

    public ModWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(StardewAccessories.MODID));
    }

    // ① 生成什么：把每条矿脉变成一个 ConfiguredFeature
    private static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        for (OreVein vein : ORE_VEINS) {
            List<OreConfiguration.TargetBlockState> targets = vein.targets().stream()
                    .map(target -> OreConfiguration.target(
                            new TagMatchTest(target.replaceable()),
                            target.ore().get().defaultBlockState()))
                    .toList();
            context.register(configuredKey(vein.name()), new ConfiguredFeature<>(Feature.ORE,
                    new OreConfiguration(targets, vein.veinSize(), vein.discardChanceOnAirExposure())));
        }
    }

    // ② 在哪生成：数量 / 散开 / 高度
    private static void placedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

        for (OreVein vein : ORE_VEINS) {
            context.register(placedKey(vein.name()), new PlacedFeature(
                    features.getOrThrow(configuredKey(vein.name())),
                    List.of(
                            CountPlacement.of(vein.veinsPerChunk()),
                            InSquarePlacement.spread(),
                            vein.height(),
                            BiomeFilter.biome())));
        }
    }

    // ③ 挂到主世界所有群系的地下矿石阶段
    private static void biomeModifiers(BootstrapContext<BiomeModifier> context) {
        HolderSet.Named<Biome> overworld = context.lookup(Registries.BIOME)
                .getOrThrow(BiomeTags.IS_OVERWORLD);
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        for (OreVein vein : ORE_VEINS) {
            context.register(biomeModifierKey(vein.name()), new BiomeModifiers.AddFeaturesBiomeModifier(
                    overworld,
                    HolderSet.direct(placed.getOrThrow(placedKey(vein.name()))),
                    GenerationStep.Decoration.UNDERGROUND_ORES));
        }
    }

    // ---- 下面是根据 name 推导各种 ID 的小工具，一般不用改 ----

    private static ResourceKey<ConfiguredFeature<?, ?>> configuredKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, id(name));
    }

    private static ResourceKey<PlacedFeature> placedKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, id(name));
    }

    private static ResourceKey<BiomeModifier> biomeModifierKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, id("add_" + name));
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(StardewAccessories.MODID, path);
    }
}
