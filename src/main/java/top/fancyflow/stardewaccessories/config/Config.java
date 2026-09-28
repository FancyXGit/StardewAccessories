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

    public static final ModConfigSpec SPEC = BUILDER.build();
}
