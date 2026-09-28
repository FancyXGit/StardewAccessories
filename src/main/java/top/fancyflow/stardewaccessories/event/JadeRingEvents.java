package top.fancyflow.stardewaccessories.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 翡翠戒指的暴击伤害逻辑：原版没有"暴击伤害"属性，只能挂 CriticalHitEvent
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class JadeRingEvents {

    @SubscribeEvent
    private static void onCriticalHit(CriticalHitEvent event) {
        if (!event.isCriticalHit()) {
            return;
        }

        // 身上戴了几个翡翠戒指就加几份（每个 +0.10，暴击倍率 1.5 → 1.6）
        int rings = CuriosApi.getCuriosInventory(event.getEntity())
                .map(inv -> inv.findCurios(ModItems.JADE_RING.get()).size())
                .orElse(0);

        if (rings > 0) {
            // 每个翡翠戒指加一份暴击倍率，数值来自配置
            event.setDamageMultiplier(
                    event.getDamageMultiplier() + (float) (Config.JADE_RING_CRIT_DAMAGE.get() * rings));
        }
    }
}
