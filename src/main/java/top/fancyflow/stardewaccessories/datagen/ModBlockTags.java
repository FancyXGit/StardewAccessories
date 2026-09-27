package top.fancyflow.stardewaccessories.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 生成 data/minecraft/tags/block/mineable/*.json
public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                        ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
