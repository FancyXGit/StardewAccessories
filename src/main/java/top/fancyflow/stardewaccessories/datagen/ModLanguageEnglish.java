package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 生成 assets/stardewaccessories/lang/en_us.json
public class ModLanguageEnglish extends LanguageProvider {
    public ModLanguageEnglish(PackOutput output) {
        super(output, StardewAccessories.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.stardewaccessories", "Stardew Accessories");
        add(ModItems.CHICKEN.get(), "Golden Chicken");

        add(ModItems.RING_BLANK.get(), "Ring Blank");
        add(ModItems.RUBY.get(), "Ruby");
        add(ModItems.EMERALD.get(), "Emerald");

        add(ModItems.RUBY_RING.get(), "Ruby Ring");
        add(ModItems.EMERALD_RING.get(), "Emerald Ring");
    }
}
