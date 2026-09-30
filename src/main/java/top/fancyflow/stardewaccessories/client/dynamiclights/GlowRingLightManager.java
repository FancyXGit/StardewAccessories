package top.fancyflow.stardewaccessories.client.dynamiclights;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 每 tick 检查哪些玩家戴着发光戒指，并为他们在 LambDynamicLights 里登记/更新/移除光源。
// 注意：本类刻意不直接引用 LambDynamicLights 的任何类型（管理器用 Object 保存），
// 这样即使玩家没装 LambDynamicLights，加载这个类也不会因为缺少类而崩溃。
@EventBusSubscriber(modid = StardewAccessories.MODID, value = Dist.CLIENT)
public final class GlowRingLightManager {

    // 由 LambDynamicLights 在初始化时传入（DynamicLightBehaviorManager）；没装时始终为 null
    private static Object manager;
    // 已登记的光源，按玩家索引（PlayerGlowLight，用 Object 保存）
    private static final Map<Player, Object> LIGHTS = new HashMap<>();
    // 记录当前世界，换维度/重进世界时清空登记
    private static ClientLevel trackedLevel;

    private GlowRingLightManager() {
    }

    public static void setManager(Object behaviorManager) {
        manager = behaviorManager;
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (manager == null) {
            return;
        }

        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) {
            clear();
            return;
        }
        if (level != trackedLevel) {
            // 进入新世界：先移除旧世界里登记的光源，再清空登记
            clear();
            trackedLevel = level;
        }

        Set<Player> present = new HashSet<>(level.players());

        // 已离开的玩家：移除光源
        LIGHTS.entrySet().removeIf(entry -> {
            if (!present.contains(entry.getKey())) {
                GlowRingLightHelper.remove(manager, entry.getValue());
                return true;
            }
            return false;
        });

        // 当前玩家：按所戴戒指的最大亮度登记 / 更新光源
        for (Player player : present) {
            int luminance = luminanceOf(player);
            Object existing = LIGHTS.get(player);
            if (luminance <= 0) {
                if (existing != null) {
                    GlowRingLightHelper.remove(manager, existing);
                    LIGHTS.remove(player);
                }
            } else if (existing == null) {
                Object light = GlowRingLightHelper.create(player, luminance);
                GlowRingLightHelper.add(manager, light);
                LIGHTS.put(player, light);
            } else {
                // 亮度没变时是空操作
                GlowRingLightHelper.setLuminance(existing, luminance);
            }
        }
    }

    // 客户端检查玩家戴着的发光戒指，返回其中最大的光照等级（0 表示没戴）
    private static int luminanceOf(Player player) {
        return CuriosApi.getCuriosInventory(player).map(inventory -> {
            int luminance = 0;
            if (inventory.findFirstCurio(ModItems.SMALL_GLOW_RING.get()).isPresent()) {
                luminance = Math.max(luminance, Config.SMALL_GLOW_RING_LIGHT.get());
            }
            if (inventory.findFirstCurio(ModItems.GLOW_RING.get()).isPresent()) {
                luminance = Math.max(luminance, Config.GLOW_RING_LIGHT.get());
            }
            // 铱环含光辉戒指的效果，按光辉戒指的亮度计入
            if (inventory.findFirstCurio(ModItems.IRIDIUM_BAND.get()).isPresent()) {
                luminance = Math.max(luminance, Config.GLOW_RING_LIGHT.get());
            }
            return luminance;
        }).orElse(0);
    }

    private static void clear() {
        if (manager != null) {
            LIGHTS.values().forEach(light -> GlowRingLightHelper.remove(manager, light));
        }
        LIGHTS.clear();
    }
}
