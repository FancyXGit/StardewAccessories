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

    public static final ModConfigSpec SPEC = BUILDER.build();
}
