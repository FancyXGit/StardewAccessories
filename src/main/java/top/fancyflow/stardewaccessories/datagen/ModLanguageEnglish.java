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
        add("attribute.name.stardewaccessories.crit_chance", "Critical Chance");
        add("attribute.name.stardewaccessories.crit_damage", "Critical Damage");
        add(ModItems.CHICKEN.get(), "Golden Chicken");

        add(ModItems.RING_BLANK.get(), "Ring Blank");
        add(ModItems.RUBY.get(), "Ruby");
        add(ModItems.EMERALD.get(), "Emerald");
        add(ModItems.JADE.get(), "Jade");
        add(ModItems.AQUAMARINE.get(), "Aquamarine");
        add(ModItems.TOPAZ.get(), "Topaz");
        add(ModItems.AMETHYST.get(), "Amethyst");

        add(ModItems.RUBY_RING.get(), "Ruby Ring");
        add(ModItems.EMERALD_RING.get(), "Emerald Ring");
        add(ModItems.JADE_RING.get(), "Jade Ring");
        add(ModItems.AQUAMARINE_RING.get(), "Aquamarine Ring");
        add(ModItems.TOPAZ_RING.get(), "Topaz Ring");
        add(ModItems.AMETHYST_RING.get(), "Amethyst Ring");
    }
}
