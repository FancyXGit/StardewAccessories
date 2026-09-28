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
import top.fancyflow.stardewaccessories.registry.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

// 每 tick 检查哪些玩家戴着小型光辉戒指，并为他们在 LambDynamicLights 里登记/移除光源。
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

        // 找出当前戴着戒指的玩家
        Set<Player> glowing = new HashSet<>();
        for (Player player : level.players()) {
            if (wearsGlowRing(player)) {
                glowing.add(player);
            }
        }

        // 取下来 / 离开的玩家：移除光源
        LIGHTS.entrySet().removeIf(entry -> {
            if (!glowing.contains(entry.getKey())) {
                GlowRingLightHelper.remove(manager, entry.getValue());
                return true;
            }
            return false;
        });

        // 新戴上戒指的玩家：登记光源
        for (Player player : glowing) {
            LIGHTS.computeIfAbsent(player, p -> {
                Object light = GlowRingLightHelper.create(p);
                GlowRingLightHelper.add(manager, light);
                return light;
            });
        }
    }

    // 客户端检查玩家戒指槽里有没有小型光辉戒指
    private static boolean wearsGlowRing(Player player) {
        return CuriosApi.getCuriosInventory(player)
                .flatMap(inventory -> inventory.findFirstCurio(ModItems.SMALL_GLOW_RING.get()))
                .isPresent();
    }

    private static void clear() {
        if (manager != null) {
            LIGHTS.values().forEach(light -> GlowRingLightHelper.remove(manager, light));
        }
        LIGHTS.clear();
    }
}
