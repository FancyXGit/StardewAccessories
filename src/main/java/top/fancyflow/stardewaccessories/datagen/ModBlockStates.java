package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModBlocks;

// 生成 blockstates/*.json + models/block/*.json + 方块物品的 models/item/*.json
public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // 新增方块时，在这里加一行
        // 矿石：cube_all 模型，方块物品模型由 simpleBlockWithItem 一并生成
        simpleBlockWithItem(ModBlocks.STARSHARD_ORE.get(), cubeAll(ModBlocks.STARSHARD_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_STARSHARD_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_STARSHARD_ORE.get()));
        simpleBlockWithItem(ModBlocks.PRISM_ORE.get(), cubeAll(ModBlocks.PRISM_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_PRISM_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_PRISM_ORE.get()));
    }
}
