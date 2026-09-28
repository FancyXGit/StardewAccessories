package top.fancyflow.stardewaccessories.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModAttributes;

// 海蓝宝石戒指的暴击率逻辑：原版暴击条件写死（下落中、非冲刺等），
// 这里拦截 CriticalHitEvent，非原版暴击时按属性值 roll 一次，命中就设成暴击
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class CritChanceEvents {

    // 优先级 HIGH，保证先于 JadeRingEvents(默认 NORMAL) 判定暴击，翡翠的暴击伤害才能叠加上去
    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void onCriticalHit(CriticalHitEvent event) {
        // 已经是原版暴击就不重复处理
        if (event.isVanillaCritical() || event.isCriticalHit()) {
            return;
        }

        Player player = event.getEntity();
        double chance = player.getAttributeValue(ModAttributes.CRIT_CHANCE);

        if (chance > 0.0D && player.getRandom().nextDouble() < chance) {
            // 手动补上暴击倍率（非原版暴击默认是 1.0）
            event.setCriticalHit(true);
            event.setDamageMultiplier(1.5F);
        }
    }
}
