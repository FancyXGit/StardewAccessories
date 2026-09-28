package top.fancyflow.stardewaccessories.event;

import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 史莱姆克星戒指：佩戴后，来自史莱姆和岩浆怪的伤害全部免除。
// 岩浆怪 MagmaCube 继承自 Slime，所以一个 instanceof 判断即可覆盖两者。
// 取消 LivingIncomingDamageEvent 会把伤害和击退一并抹掉。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class SlimeCharmerRingEvents {

    @SubscribeEvent
    private static void onIncomingDamage(LivingIncomingDamageEvent event) {
        // 只处理玩家受到的伤害
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // 伤害的直接来源是史莱姆/岩浆怪才免除
        if (!(event.getSource().getDirectEntity() instanceof Slime)) {
            return;
        }

        // 戴着戒指才生效
        boolean wearing = CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.findFirstCurio(ModItems.SLIME_CHARMER_RING.get()).isPresent())
                .orElse(false);
        if (wearing) {
            event.setCanceled(true);
        }
    }
}
