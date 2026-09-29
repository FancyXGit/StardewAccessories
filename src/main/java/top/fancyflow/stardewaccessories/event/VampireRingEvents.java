package top.fancyflow.stardewaccessories.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Enemy;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 吸血戒指：击杀敌对生物时恢复生命值，多枚戒指叠加（每枚各回一次）。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class VampireRingEvents {

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

        // 统计佩戴的吸血戒指数量，多枚叠加
        int ringCount = CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.findCurios(ModItems.VAMPIRE_RING.get()).size())
                .orElse(0);
        if (ringCount <= 0) {
            return;
        }

        // 满血时 heal 会自动封顶，不会溢出
        player.heal((float) (Config.VAMPIRE_RING_HEAL_AMOUNT.get() * ringCount));
    }
}
