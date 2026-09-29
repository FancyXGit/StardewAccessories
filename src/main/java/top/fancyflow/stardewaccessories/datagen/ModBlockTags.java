package top.fancyflow.stardewaccessories.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModBlocks;

// 生成 data/minecraft/tags/block/mineable/*.json
public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                        ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // 矿石都用镐挖
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.STARSHARD_ORE.get(),
                ModBlocks.DEEPSLATE_STARSHARD_ORE.get(),
                ModBlocks.PRISM_ORE.get(),
                ModBlocks.DEEPSLATE_PRISM_ORE.get());
        // 星陨矿石需要钻石镐才能掉落
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                ModBlocks.STARSHARD_ORE.get(),
                ModBlocks.DEEPSLATE_STARSHARD_ORE.get());
        // 彩晶矿石需要铁镐才能掉落
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.PRISM_ORE.get(),
                ModBlocks.DEEPSLATE_PRISM_ORE.get());
    }
}
