package top.fancyflow.stardewaccessories.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.monster.Enemy;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModEffects;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 战士戒指：击杀敌对生物时按概率获得"战士能量"效果（提高攻击力）。
// 戴多枚戒指时各 roll 一次，任意一枚命中即触发（与原版双戒一致）；重复触发只刷新时长。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class WarriorRingEvents {

    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent event) {
        // 只对敌对生物生效（史莱姆/岩浆怪等实现了 Enemy，非敌对生物不触发）
        if (!(event.getEntity() instanceof Enemy)) {
            return;
        }

        // 击杀者必须是玩家；用弹射物击杀时 getEntity() 返回发射者
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }

        // 统计佩戴的战士戒指数量，逐枚 roll 概率
        int ringCount = CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.findCurios(ModItems.WARRIOR_RING.get()).size())
                .orElse(0);
        if (ringCount <= 0) {
            return;
        }

        double chance = Config.WARRIOR_RING_PROC_CHANCE.get();
        boolean triggered = false;
        for (int i = 0; i < ringCount; i++) {
            if (player.getRandom().nextDouble() < chance) {
                triggered = true;
                break;
            }
        }
        if (!triggered) {
            return;
        }

        // amplifier 固定 0，效果不自叠加
        player.addEffect(new MobEffectInstance(
                ModEffects.WARRIOR_ENERGY, Config.WARRIOR_ENERGY_DURATION.get(), 0, false, true, true));
    }
}
