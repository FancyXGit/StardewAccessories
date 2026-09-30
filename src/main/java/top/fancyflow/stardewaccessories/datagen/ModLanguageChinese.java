package top.fancyflow.stardewaccessories.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModBlocks;
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
        add("effect.stardewaccessories.warrior_energy", "战士能量");
        add("effect.stardewaccessories.yobas_blessing", "由巴的祝福");
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
        add(ModItems.METAL_RING_BLANK.get(), "金属空白戒指");
        add(ModItems.DARK_ALLOY_RING_BLANK.get(), "暗合金空白戒指");
        add(ModItems.MAGNET_FRAGMENTS.get(), "磁石碎块");
        add(ModItems.MAGNET.get(), "磁石");
        add(ModItems.SLIME_CRYSTAL.get(), "史莱姆结晶");
        add(ModItems.BLOOD_ESSENCE.get(), "血之精华");

        // 矿石
        add(ModBlocks.STARSHARD_ORE.get(), "星陨矿石");
        add(ModBlocks.DEEPSLATE_STARSHARD_ORE.get(), "深板岩星陨矿石");
        add(ModBlocks.PRISM_ORE.get(), "彩晶矿石");
        add(ModBlocks.DEEPSLATE_PRISM_ORE.get(), "深板岩彩晶矿石");
        add(ModItems.STARSHARD.get(), "星之碎片");
        add(ModItems.STARSHARD_INGOT.get(), "星陨锭");

        add(ModItems.RUBY_RING.get(), "红宝石戒指");
        add(ModItems.EMERALD_RING.get(), "绿宝石戒指");
        add(ModItems.JADE_RING.get(), "翡翠戒指");
        add(ModItems.AQUAMARINE_RING.get(), "海蓝宝石戒指");
        add(ModItems.TOPAZ_RING.get(), "黄水晶戒指");
        add(ModItems.AMETHYST_RING.get(), "紫水晶戒指");
        add(ModItems.SMALL_GLOW_RING.get(), "小型光辉戒指");
        add(ModItems.GLOW_RING.get(), "光辉戒指");
        add(ModItems.SMALL_MAGNET_RING.get(), "小型磁铁戒指");
        add(ModItems.MAGNET_RING.get(), "磁铁戒指");
        add(ModItems.SLIME_CHARMER_RING.get(), "史莱姆克星戒指");
        add(ModItems.WARRIOR_RING.get(), "战士戒指");
        add(ModItems.VAMPIRE_RING.get(), "吸血戒指");
        add(ModItems.SAVAGE_RING.get(), "野蛮人戒指");
        add(ModItems.RING_OF_YOBA.get(), "由巴的戒指");

        add("tooltip.stardewaccessories.small_glow_ring", "略微照亮周围");
        add("tooltip.stardewaccessories.glow_ring", "明亮地照亮周围");
        add("tooltip.stardewaccessories.small_magnet_ring", "吸引附近的物品");
        add("tooltip.stardewaccessories.magnet_ring", "强力吸引附近的物品");
        add("tooltip.stardewaccessories.slime_charmer_ring", "史莱姆和岩浆怪无法伤害你");
        add("tooltip.stardewaccessories.warrior_ring", "击杀怪物时有概率获得战士能量，提高攻击力");
        add("tooltip.stardewaccessories.vampire_ring", "每击杀一个怪物恢复少量生命");
        add("tooltip.stardewaccessories.savage_ring", "击杀怪物后短暂提升移动速度");
        add("tooltip.stardewaccessories.ring_of_yoba", "受到伤害时有概率获得由巴的祝福，短时间内免疫伤害");
        add("tooltip.stardewaccessories.requires_lambdynlights", "需要安装 LambDynamicLights");
        add("tooltip.stardewaccessories.not_installed", "未安装时不发光");

        // 材料获取方式
        add("tooltip.stardewaccessories.lumen_dust.source", "挖掘萤石时小概率掉落");
        add("tooltip.stardewaccessories.magnet_fragments.source", "探索矿洞时偶尔在箱子里发现");
        add("tooltip.stardewaccessories.slime_crystal.source", "击杀史莱姆时小概率掉落");
        add("tooltip.stardewaccessories.blood_essence.source", "击杀蝙蝠或幻翼时小概率掉落");

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
        add("tooltip.stardewaccessories.metal_ring_blank.desc", "一枚沉甸甸的金属素圈，隐隐带着磁性");
        add("tooltip.stardewaccessories.dark_alloy_ring_blank.desc", "幽暗的合金素圈，泛着冷硬的暗光");
        add("tooltip.stardewaccessories.small_magnet_ring.desc", "碎磁石嵌在戒面上，散落的物件会自己找上门来");
        add("tooltip.stardewaccessories.magnet_ring.desc", "完整的磁石戒面，散落的物件纷纷聚拢过来");
        add("tooltip.stardewaccessories.slime_charmer_ring.desc", "史莱姆见了你，只剩下黏糊糊的亲近");
        add("tooltip.stardewaccessories.warrior_ring.desc", "战意凝成的戒指，斩杀之后仍有余勇");
        add("tooltip.stardewaccessories.vampire_ring.desc", "饮血之戒，斩杀之后生命悄然回流");
        add("tooltip.stardewaccessories.savage_ring.desc", "骨头串成的戒指，猎物倒下时脚步忽然轻快");
        add("tooltip.stardewaccessories.ring_of_yoba.desc", "由巴的庇护凝成的戒指，危难时会赐下一瞬的不破之身");
    }
}
