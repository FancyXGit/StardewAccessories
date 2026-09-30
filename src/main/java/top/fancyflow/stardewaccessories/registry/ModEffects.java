package top.fancyflow.stardewaccessories.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.effect.WarriorEnergyEffect;
import top.fancyflow.stardewaccessories.effect.YobasBlessingEffect;

// 自定义状态效果的注册集中在这里；新增效果时在下面加字段
public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, StardewAccessories.MODID);

    // 战士能量：战士戒指击杀敌对生物时触发，短时间内提高攻击力（效果见 WarriorEnergyEffect）
    public static final DeferredHolder<MobEffect, MobEffect> WARRIOR_ENERGY =
            MOB_EFFECTS.register("warrior_energy", WarriorEnergyEffect::new);

    // 由巴的祝福：由巴的戒指在玩家受伤后触发，短时间内免疫伤害（效果见 YobasBlessingEffect）
    public static final DeferredHolder<MobEffect, MobEffect> YOBAS_BLESSING =
            MOB_EFFECTS.register("yobas_blessing", YobasBlessingEffect::new);

    public static void register(IEventBus modEventBus) {
        MOB_EFFECTS.register(modEventBus);
    }
}
