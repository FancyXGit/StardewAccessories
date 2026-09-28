package top.fancyflow.stardewaccessories.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.theillusivec4.curios.api.CuriosTags;

// 生成 Curios 的物品标签 data/curios/tags/item/*.json：决定物品能放进哪个饰品槽
public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(PackOutput output,
                       CompletableFuture<HolderLookup.Provider> lookupProvider,
                       CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
                       ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // 新增饰品时，在这里把它加进对应槽位的标签（CuriosTags.HEAD/NECKLACE/RING/...）
        tag(CuriosTags.RING).add(StardewAccessories.RUBY_RING.get());
        // 想让物品任何槽都能放，用通用标签：tag(CuriosTags.CURIO).add(...);
    }
}
