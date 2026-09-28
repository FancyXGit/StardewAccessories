package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 生成普通物品的 models/item/*.json
public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // 新增普通物品时，在这里加一行
        // 图标
        basicItem(StardewAccessories.CHICKEN.get());

        // 材料
        basicItem(StardewAccessories.RING_BLANK.get());
        basicItem(StardewAccessories.RUBY.get());

        // 戒指
        basicItem(StardewAccessories.RUBY_RING.get());
        // 方块物品的模型由 ModBlockStates 的 simpleBlockWithItem 生成，不要在这里重复写
    }
}
