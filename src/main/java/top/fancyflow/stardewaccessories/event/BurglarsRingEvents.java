package top.fancyflow.stardewaccessories.event;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 窃贼戒指：击杀敌对怪物时按概率获得额外掉落（用 LivingDropsEvent 近似原版抢夺I）。
// 抢夺在 1.21 没有可用的等级钩子，这里对已生成的掉落做"再roll一次加一个"的近似。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class BurglarsRingEvents {

    @SubscribeEvent
    private static void onLivingDrops(LivingDropsEvent event) {
        // 只对敌对生物生效（与 MonsterKillEvents 的判定一致）
        if (!(event.getEntity() instanceof Enemy)) {
            return;
        }

        // 击杀者必须是玩家
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        // 戴着戒指才生效
        boolean wearing = CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.findFirstCurio(ModItems.BURGLARS_RING.get()).isPresent())
                .orElse(false);
        if (!wearing) {
            return;
        }

        double chance = Config.BURGLARS_RING_EXTRA_DROP_CHANCE.get();
        for (ItemEntity drop : event.getDrops()) {
            ItemStack stack = drop.getItem();
            // 跳过不可堆叠的装备（盔甲/武器/工具），避免把装备爆率也一起翻倍；已满堆叠的也不再增加
            if (stack.getMaxStackSize() <= 1 || stack.getCount() >= stack.getMaxStackSize()) {
                continue;
            }
            if (player.getRandom().nextDouble() < chance) {
                stack.grow(1);
            }
        }
    }
}
