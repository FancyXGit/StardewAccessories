package top.fancyflow.stardewaccessories.config;

import net.neoforged.neoforge.common.ModConfigSpec;

// 环形饰品的数值集中在这里，模组加载后自动生成 config/stardewaccessories-common.toml
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue RUBY_RING_ATTACK_DAMAGE = BUILDER
            .comment("红宝石戒指的额外攻击伤害（0.10 = +10%）")
            .defineInRange("rubyRingAttackDamage", 0.10, 0.0, 1024.0);

    public static final ModConfigSpec.DoubleValue EMERALD_RING_ATTACK_SPEED = BUILDER
            .comment("绿宝石戒指的额外攻速（0.10 = +10%）")
            .defineInRange("emeraldRingAttackSpeed", 0.10, 0.0, 1024.0);

    public static final ModConfigSpec.DoubleValue JADE_RING_CRIT_DAMAGE = BUILDER
            .comment("翡翠戒指的额外暴击伤害，加到暴击倍率上（0.10 = 暴击倍率 1.5 -> 1.6）")
            .defineInRange("jadeRingCritDamage", 0.10, 0.0, 1024.0);

    public static final ModConfigSpec.DoubleValue AQUAMARINE_RING_CRIT_CHANCE = BUILDER
            .comment("海蓝宝石戒指的额外暴击率（0.05 = +5%）")
            .defineInRange("aquamarineRingCritChance", 0.05, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue TOPAZ_RING_ARMOR = BUILDER
            .comment("黄水晶戒指的额外盔甲值（6.0 = +6 点）")
            .defineInRange("topazRingArmor", 6.0, 0.0, 1024.0);

    public static final ModConfigSpec.DoubleValue AMETHYST_RING_KNOCKBACK = BUILDER
            .comment("紫水晶戒指的额外攻击击退点数（1.0 ≈ 击退附魔 I）")
            .defineInRange("amethystRingKnockback", 1.0, 0.0, 1024.0);

    public static final ModConfigSpec.IntValue SMALL_GLOW_RING_LIGHT = BUILDER
            .comment("小型光辉戒指的动态光照强度（0~15）。需要安装 LambDynamicLights 才有效果；"
                    + "LambDynamicLights 会把 15 级光压缩到约 7.75 格，因此 8 级约照亮 4 格半径")
            .defineInRange("smallGlowRingLight", 8, 0, 15);

    public static final ModConfigSpec.IntValue GLOW_RING_LIGHT = BUILDER
            .comment("光辉戒指的动态光照强度（0~15）。需要安装 LambDynamicLights 才有效果；"
                    + "LambDynamicLights 会把 15 级光压缩到约 7.75 格")
            .defineInRange("glowRingLight", 15, 0, 15);

    public static final ModConfigSpec.DoubleValue SMALL_MAGNET_RING_RANGE = BUILDER
            .comment("小型磁铁戒指的吸取半径（格）：把周围这个范围内的掉落物吸向玩家")
            .defineInRange("smallMagnetRingRange", 3.0, 0.0, 64.0);

    public static final ModConfigSpec.DoubleValue SMALL_MAGNET_RING_PULL_SPEED = BUILDER
            .comment("小型磁铁戒指吸物品的最大速度（格/tick，1.0 约等于每秒 20 格）")
            .defineInRange("smallMagnetRingPullSpeed", 1.0, 0.0, 16.0);

    public static final ModConfigSpec.DoubleValue MAGNET_RING_RANGE = BUILDER
            .comment("磁铁戒指的吸取半径（格）：把周围这个范围内的掉落物吸向玩家")
            .defineInRange("magnetRingRange", 5.0, 0.0, 64.0);

    public static final ModConfigSpec.DoubleValue WARRIOR_RING_PROC_CHANCE = BUILDER
            .comment("战士戒指击杀敌对生物时获得战士能量的概率（0.10 = 10%；戴多枚各 roll 一次）")
            .defineInRange("warriorRingProcChance", 0.10, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue WARRIOR_ENERGY_ATTACK_DAMAGE = BUILDER
            .comment("战士能量的额外攻击伤害点数（原版星露谷为 +10，直接加到攻击力上）")
            .defineInRange("warriorEnergyAttackDamage", 10.0, 0.0, 1024.0);

    public static final ModConfigSpec.IntValue WARRIOR_ENERGY_DURATION = BUILDER
            .comment("战士能量的持续时间（tick，20 tick = 1 秒；原版星露谷为 5 秒）")
            .defineInRange("warriorEnergyDuration", 100, 0, 72000);

    public static final ModConfigSpec.DoubleValue VAMPIRE_RING_HEAL_AMOUNT = BUILDER
            .comment("吸血戒指每击杀一个敌对生物恢复的生命值（MC 2.0 = 1 颗心；戴多枚叠加）")
            .defineInRange("vampireRingHealAmount", 2.0, 0.0, 1024.0);

    public static final ModConfigSpec.IntValue SAVAGE_RING_SPEED_DURATION = BUILDER
            .comment("野蛮人戒指击杀怪物后速度提升的持续时间（tick，20 tick = 1 秒）")
            .defineInRange("savageRingSpeedDuration", 40, 0, 72000);

    public static final ModConfigSpec.IntValue SAVAGE_RING_SPEED_AMPLIFIER = BUILDER
            .comment("野蛮人戒指速度提升的等级（0 = 速度 I，1 = 速度 II，以此类推）")
            .defineInRange("savageRingSpeedAmplifier", 0, 0, 9);

    public static final ModConfigSpec.DoubleValue RING_OF_YOBA_PROC_CHANCE = BUILDER
            .comment("由巴的戒指受到伤害后获得由巴祝福的概率（0.05 = 5%；戴多枚各 roll 一次）")
            .defineInRange("ringOfYobaProcChance", 0.05, 0.0, 1.0);

    public static final ModConfigSpec.IntValue YOBAS_BLESSING_DURATION = BUILDER
            .comment("由巴祝福的持续时间（tick，20 tick = 1 秒；原版星露谷约 5 秒）")
            .defineInRange("yobasBlessingDuration", 100, 0, 72000);

    public static final ModConfigSpec.DoubleValue BURGLARS_RING_EXTRA_DROP_CHANCE = BUILDER
            .comment("窃贼戒指给每个可堆叠掉落的额外 +1 概率（0.5 = 50%，近似抢夺I；不可堆叠的装备不受影响）")
            .defineInRange("burglarsRingExtraDropChance", 0.5, 0.0, 1.0);

    // ===== 材料掉落概率 =====

    public static final ModConfigSpec.DoubleValue LUMEN_DUST_GLOWSTONE_CHANCE = BUILDER
            .comment("挖掘萤石时掉落流明尘的概率（0.05 = 5%）")
            .defineInRange("lumenDustGlowstoneChance", 0.05, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue MAGNET_FRAGMENTS_CHEST_CHANCE = BUILDER
            .comment("废弃矿井/地牢箱子中出现磁石碎块的概率（0.10 = 10%，一次 1~2 个）")
            .defineInRange("magnetFragmentsChestChance", 0.10, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue SLIME_CRYSTAL_SLIME_CHANCE = BUILDER
            .comment("玩家击杀史莱姆/岩浆怪时掉落史莱姆结晶的概率（0.01 = 1%）")
            .defineInRange("slimeCrystalSlimeChance", 0.01, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue BLOOD_ESSENCE_BAT_CHANCE = BUILDER
            .comment("玩家击杀蝙蝠时掉落血之精华的概率（0.05 = 5%）")
            .defineInRange("bloodEssenceBatChance", 0.05, 0.0, 1.0);

    public static final ModConfigSpec.DoubleValue BLOOD_ESSENCE_PHANTOM_CHANCE = BUILDER
            .comment("玩家击杀幻翼时掉落血之精华的概率（0.10 = 10%）")
            .defineInRange("bloodEssencePhantomChance", 0.10, 0.0, 1.0);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
