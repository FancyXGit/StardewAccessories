package top.fancyflow.stardewaccessories.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 所有方块的注册集中在这里；新增方块时在这里加字段
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(StardewAccessories.MODID);

    // 矿石
    // 星陨矿石：需要钻石镐，掉落星之碎片
    public static final DeferredBlock<Block> STARSHARD_ORE = BLOCKS.registerSimpleBlock("starshard_ore",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE));
    public static final DeferredBlock<Block> DEEPSLATE_STARSHARD_ORE = BLOCKS.registerSimpleBlock("deepslate_starshard_ore",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE));
    // 彩晶矿石：需要铁镐，掉落随机宝石
    public static final DeferredBlock<Block> PRISM_ORE = BLOCKS.registerSimpleBlock("prism_ore",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE));
    public static final DeferredBlock<Block> DEEPSLATE_PRISM_ORE = BLOCKS.registerSimpleBlock("deepslate_prism_ore",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
