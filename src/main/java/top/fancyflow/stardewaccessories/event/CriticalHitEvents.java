package top.fancyflow.stardewaccessories.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModAttributes;

// 暴击相关逻辑集中在这里，顺序写死，不再依赖多个监听器的优先级：
//   ① 海蓝宝石戒指的暴击率：原版暴击条件写死（下落中、非冲刺等），
//      非原版暴击时按属性值 roll 一次，命中就设成暴击
//   ② 翡翠戒指的暴击伤害：确认暴击后，把属性值加到暴击倍率上
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class CriticalHitEvents {

    @SubscribeEvent
    private static void onCriticalHit(CriticalHitEvent event) {
        Player player = event.getEntity();

        // ① 判定暴击：已经是原版/其他模组暴击就不重复 roll
        if (!event.isCriticalHit()) {
            double chance = player.getAttributeValue(ModAttributes.CRIT_CHANCE);
            if (chance > 0.0D && player.getRandom().nextDouble() < chance) {
                // 手动补上暴击倍率（非原版暴击默认是 1.0）
                event.setCriticalHit(true);
                event.setDamageMultiplier(1.5F);
            }
        }

        // ② 已暴击则叠加暴击伤害，保证海蓝宝石触发的暴击也能吃到翡翠加成
        if (event.isCriticalHit()) {
            double critDamage = player.getAttributeValue(ModAttributes.CRIT_DAMAGE);
            if (critDamage > 0.0D) {
                event.setDamageMultiplier(event.getDamageMultiplier() + (float) critDamage);
            }
        }
    }
}
