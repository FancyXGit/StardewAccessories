package top.fancyflow.stardewaccessories.datagen;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import top.fancyflow.stardewaccessories.registry.ModBlocks;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 生成 data/stardewaccessories/loot_table/blocks/*.json
public class ModLootTables extends LootTableProvider {
    public ModLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Set.of(),
                List.of(new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)),
                lookupProvider);
    }

    private static class ModBlockLootTables extends BlockLootSubProvider {
        protected ModBlockLootTables(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate() {
            // 星陨矿石：精准采集掉方块，否则固定 1 个星之碎片（不吃时运）
            add(ModBlocks.STARSHARD_ORE.get(),
                    this.singleDropWithSilkTouch(ModBlocks.STARSHARD_ORE.get(), ModItems.STARSHARD.get()));
            add(ModBlocks.DEEPSLATE_STARSHARD_ORE.get(),
                    this.singleDropWithSilkTouch(ModBlocks.DEEPSLATE_STARSHARD_ORE.get(), ModItems.STARSHARD.get()));

            // 彩晶矿石：精准采集掉方块，否则 1 个随机宝石（6 种等权，吃时运）
            add(ModBlocks.PRISM_ORE.get(),
                    this.prismOreDrops(ModBlocks.PRISM_ORE.get()));
            add(ModBlocks.DEEPSLATE_PRISM_ORE.get(),
                    this.prismOreDrops(ModBlocks.DEEPSLATE_PRISM_ORE.get()));
        }

        // 精准采集掉方块本身，否则掉固定 1 个指定物品
        private LootTable.Builder singleDropWithSilkTouch(Block block, ItemLike drop) {
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(block).when(this.hasSilkTouch())))
                    .withPool(this.applyExplosionDecay(block, LootPool.lootPool()
                            .when(this.doesNotHaveSilkTouch())
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(drop))));
        }

        // 彩晶矿石掉落：1 个随机宝石，套原版矿物时运公式（时运 3 最多 4 个）
        private LootTable.Builder prismOreDrops(Block block) {
            Holder<Enchantment> fortune = this.registries.lookupOrThrow(Registries.ENCHANTMENT)
                    .getOrThrow(Enchantments.FORTUNE);
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(block).when(this.hasSilkTouch())))
                    .withPool(this.applyExplosionDecay(block, LootPool.lootPool()
                            .when(this.doesNotHaveSilkTouch())
                            .setRolls(ConstantValue.exactly(1.0F))
                            .apply(ApplyBonusCount.addOreBonusCount(fortune))
                            .add(LootItem.lootTableItem(ModItems.RUBY.get()))
                            .add(LootItem.lootTableItem(ModItems.EMERALD.get()))
                            .add(LootItem.lootTableItem(ModItems.JADE.get()))
                            .add(LootItem.lootTableItem(ModItems.AQUAMARINE.get()))
                            .add(LootItem.lootTableItem(ModItems.TOPAZ.get()))
                            .add(LootItem.lootTableItem(ModItems.AMETHYST.get()))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
    }
}
