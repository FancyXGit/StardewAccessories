package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 生成普通物品的 models/item/*.json
public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StardewAccessories.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // 新增普通物品时，在这里加一行
        // 图标
        basicItem(ModItems.CHICKEN.get());

        // 材料
        basicItem(ModItems.RING_BLANK.get());
        basicItem(ModItems.RUBY.get());
        basicItem(ModItems.EMERALD.get());
        basicItem(ModItems.JADE.get());
        basicItem(ModItems.AQUAMARINE.get());

        // 戒指
        basicItem(ModItems.RUBY_RING.get());
        basicItem(ModItems.EMERALD_RING.get());
        basicItem(ModItems.JADE_RING.get());
        basicItem(ModItems.AQUAMARINE_RING.get());
        // 方块物品的模型由 ModBlockStates 的 simpleBlockWithItem 生成，不要在这里重复写
    }
}
