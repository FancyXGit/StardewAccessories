package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 生成 assets/stardewaccessories/lang/en_us.json
public class ModLanguageEnglish extends LanguageProvider {
    public ModLanguageEnglish(PackOutput output) {
        super(output, StardewAccessories.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.stardewaccessories", "Stardew Accessories");
    }
}
