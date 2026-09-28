package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 生成 assets/stardewaccessories/lang/zh_cn.json
public class ModLanguageChinese extends LanguageProvider {
    public ModLanguageChinese(PackOutput output) {
        super(output, StardewAccessories.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.stardewaccessories", "星露谷饰品");
        add(StardewAccessories.CHICKEN.get(), "可爱小鸡");
    }
}
