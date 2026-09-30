package top.fancyflow.stardewaccessories.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModEffects;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 由巴的戒指：受到伤害后按概率获得"由巴的祝福"，祝福期间免疫伤害。
// 触发用 LivingDamageEvent.Post（此时生命值已扣除），免疫用取消 LivingIncomingDamageEvent。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class RingOfYobaEvents {

    @SubscribeEvent
    private static void onDamagePost(LivingDamageEvent.Post event) {
        // 只处理玩家受到的伤害
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        // 只有真的掉血才算"受到伤害"（被完全格挡/免疫的伤害不触发）
        if (event.getNewDamage() <= 0.0F) {
            return;
        }

        // 戴着戒指才 roll 触发
        CuriosApi.getCuriosInventory(player).ifPresent(inventory -> {
            int rings = inventory.findCurios(ModItems.RING_OF_YOBA.get()).size();
            if (rings > 0) {
                tryBless(player, rings);
            }
        });
    }

    @SubscribeEvent
    private static void onIncomingDamage(LivingIncomingDamageEvent event) {
        // 只处理玩家受到的伤害
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        // 放过 /kill、虚空等"无视无敌"的伤害来源，避免变成不死的漏洞
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return;
        }

        // 祝福期间取消伤害事件，伤害与击退一并抹掉
        if (player.hasEffect(ModEffects.YOBAS_BLESSING)) {
            event.setCanceled(true);
        }
    }

    // 每枚戒指独立 roll，命中即施加祝福；重复触发时 addEffect 只刷新时长
    private static void tryBless(ServerPlayer player, int ringCount) {
        double chance = Config.RING_OF_YOBA_PROC_CHANCE.get();
        for (int i = 0; i < ringCount; i++) {
            if (player.getRandom().nextDouble() < chance) {
                player.addEffect(new MobEffectInstance(
                        ModEffects.YOBAS_BLESSING,
                        Config.YOBAS_BLESSING_DURATION.get(),
                        0, false, true, true));
                return;
            }
        }
    }
}
