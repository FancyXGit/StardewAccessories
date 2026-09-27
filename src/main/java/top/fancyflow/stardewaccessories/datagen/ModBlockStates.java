package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 生成 blockstates/*.json + models/block/*.json + 方块物品的 models/item/*.json
public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // 新增方块时，在这里加一行
        
    }
}
