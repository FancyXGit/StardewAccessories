package top.fancyflow.stardewaccessories.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Enemy;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 野蛮人戒指：每击杀一个敌对生物获得短时间速度提升；连续击杀只刷新时长，效果不叠加。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class SavageRingEvents {

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

        // 戴着野蛮人戒指才生效；第二枚不提供额外收益，所以只判断是否佩戴
        boolean wearing = CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.findFirstCurio(ModItems.SAVAGE_RING.get()).isPresent())
                .orElse(false);
        if (!wearing) {
            return;
        }

        // 必定触发；amplifier 固定，重复触发只刷新时长
        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED,
                Config.SAVAGE_RING_SPEED_DURATION.get(),
                Config.SAVAGE_RING_SPEED_AMPLIFIER.get(),
                false, true, true));
    }
}
