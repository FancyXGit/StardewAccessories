package top.fancyflow.stardewaccessories.event;

import java.util.List;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 小型磁铁戒指：每 tick 服务端把玩家附近的掉落物吸向玩家。
// 做法参考 Artifacts 的 Universal Attractor：给物品一个指向玩家的速度，靠近后由原版拾取判定收走，
// 而不是直接调用拾取；同时跳过刚丢出和自己手里丢出的物品。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class MagnetRingEvents {

    // 每 tick 最多拉取的物品数，避免物品过多时卡顿
    private static final int MAX_PULLED_PER_TICK = 50;

    @SubscribeEvent
    private static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        // 物品吸取只在服务端处理
        if (player.level().isClientSide) {
            return;
        }

        double range = magnetRange(player);
        if (range <= 0.0D) {
            return;
        }

        // 以玩家身上方 0.75 格为中心，range 为半径的立方体
        Vec3 center = player.position().add(0.0D, 0.75D, 0.0D);
        AABB area = new AABB(
                center.x - range, center.y - range, center.z - range,
                center.x + range, center.y + range, center.z + range);

        List<ItemEntity> items = player.level().getEntitiesOfClass(ItemEntity.class, area);
        int pulled = 0;
        for (ItemEntity item : items) {
            // 刚生成/刚丢出的不吸；自己从手里丢出的也不吸（原版 Player.drop 会记录 thrower）
            if (!item.isAlive() || item.hasPickUpDelay() || isThrownBy(item, player)) {
                continue;
            }
            if (pulled++ >= MAX_PULLED_PER_TICK) {
                break;
            }

            Vec3 motion = center.subtract(item.position().add(0.0D, item.getBbHeight() / 2.0D, 0.0D));
            // 距离超过 1 格才归一化，靠近时保持原有大小，吸力更柔和
            if (motion.length() > 1.0D) {
                motion = motion.normalize();
            }
            item.setDeltaMovement(motion.scale(Config.SMALL_MAGNET_RING_PULL_SPEED.get()));
        }
    }

    // 判断物品是不是该玩家从手里丢出来的
    private static boolean isThrownBy(ItemEntity item, Player player) {
        return item.getOwner() != null && player.getUUID().equals(item.getOwner().getUUID());
    }

    // 佩戴戒指时返回配置的吸取半径，没戴返回 0
    private static double magnetRange(Player player) {
        return CuriosApi.getCuriosInventory(player)
                .map(inventory -> inventory.findFirstCurio(ModItems.SMALL_MAGNET_RING.get()).isPresent()
                        ? Config.SMALL_MAGNET_RING_RANGE.get()
                        : 0.0D)
                .orElse(0.0D);
    }
}
