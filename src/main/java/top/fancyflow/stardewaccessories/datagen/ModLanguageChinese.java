package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 生成 assets/stardewaccessories/lang/zh_cn.json
public class ModLanguageChinese extends LanguageProvider {
    public ModLanguageChinese(PackOutput output) {
        super(output, StardewAccessories.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.stardewaccessories", "星露谷饰品");
        add("tooltip.stardewaccessories.jade_ring", "+10% 暴击伤害");
        add(ModItems.CHICKEN.get(), "可爱小鸡");

        add(ModItems.RING_BLANK.get(), "空白戒指");
        add(ModItems.RUBY.get(), "红宝石");
        add(ModItems.EMERALD.get(), "绿宝石");
        add(ModItems.JADE.get(), "翡翠");

        add(ModItems.RUBY_RING.get(), "红宝石戒指");
        add(ModItems.EMERALD_RING.get(), "绿宝石戒指");
        add(ModItems.JADE_RING.get(), "翡翠戒指");
    }
}
