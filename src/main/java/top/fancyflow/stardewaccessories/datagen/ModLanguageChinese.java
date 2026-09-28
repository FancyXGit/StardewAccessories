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
        add("attribute.name.stardewaccessories.crit_chance", "暴击率");
        add("attribute.name.stardewaccessories.crit_damage", "暴击伤害");
        add(ModItems.CHICKEN.get(), "可爱小鸡");

        add(ModItems.RING_BLANK.get(), "空白戒指");
        add(ModItems.RUBY.get(), "红宝石");
        add(ModItems.EMERALD.get(), "绿宝石");
        add(ModItems.JADE.get(), "翡翠");
        add(ModItems.AQUAMARINE.get(), "海蓝宝石");
        add(ModItems.TOPAZ.get(), "黄水晶");
        add(ModItems.AMETHYST.get(), "紫水晶");
        add(ModItems.LUMEN_DUST.get(), "流明尘");
        add(ModItems.LUMENITE.get(), "流明晶");
        add(ModItems.LUMENITE_RING_BLANK.get(), "流明空白戒指");

        add(ModItems.RUBY_RING.get(), "红宝石戒指");
        add(ModItems.EMERALD_RING.get(), "绿宝石戒指");
        add(ModItems.JADE_RING.get(), "翡翠戒指");
        add(ModItems.AQUAMARINE_RING.get(), "海蓝宝石戒指");
        add(ModItems.TOPAZ_RING.get(), "黄水晶戒指");
        add(ModItems.AMETHYST_RING.get(), "紫水晶戒指");
        add(ModItems.SMALL_GLOW_RING.get(), "小型光辉戒指");
        add(ModItems.GLOW_RING.get(), "光辉戒指");

        add("tooltip.stardewaccessories.small_glow_ring", "略微照亮周围");
        add("tooltip.stardewaccessories.glow_ring", "明亮地照亮周围");
        add("tooltip.stardewaccessories.requires_lambdynlights", "需要安装 LambDynamicLights");
        add("tooltip.stardewaccessories.not_installed", "未安装时不发光");

        // 风味描述
        add("tooltip.stardewaccessories.ruby_ring.desc", "附魔的红宝石，让每一击都更沉一分");
        add("tooltip.stardewaccessories.emerald_ring.desc", "附魔的绿宝石，让出手的节奏轻快起来");
        add("tooltip.stardewaccessories.jade_ring.desc", "温润的翡翠里，藏着一道锋芒");
        add("tooltip.stardewaccessories.aquamarine_ring.desc", "一汪附魔的海水，让破绽无处可藏");
        add("tooltip.stardewaccessories.topaz_ring.desc", "戴着这个会感觉安全一点点");
        add("tooltip.stardewaccessories.amethyst_ring.desc", "附魔的紫水晶，挥击时带着一股暗劲");
        add("tooltip.stardewaccessories.small_glow_ring.desc", "一簇微光，在黑暗里静静亮着");
        add("tooltip.stardewaccessories.glow_ring.desc", "一团明亮的光，照着你走过夜路");
        add("tooltip.stardewaccessories.ring_blank.desc", "一枚素圈，等着被镶上宝石");
        add("tooltip.stardewaccessories.lumenite_ring_blank.desc", "流明晶打磨的戒圈，还留着一丝微光");
    }
}
