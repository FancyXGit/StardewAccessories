package top.fancyflow.stardewaccessories.client.dynamiclights;

import dev.lambdaurora.lambdynlights.api.behavior.DynamicLightBehavior;
import dev.lambdaurora.lambdynlights.api.behavior.DynamicLightBehaviorManager;
import net.minecraft.world.entity.player.Player;

// 把 LambDynamicLights 的调用隔离在这里：
// 这个类只会在"确实装了 LambDynamicLights"（manager != null）时被加载和执行。
final class GlowRingLightHelper {

    private GlowRingLightHelper() {
    }

    static Object create(Player player, int luminance) {
        return new PlayerGlowLight(player, luminance);
    }

    static void setLuminance(Object light, int luminance) {
        ((PlayerGlowLight) light).setLuminance(luminance);
    }

    static void add(Object manager, Object source) {
        ((DynamicLightBehaviorManager) manager).add((DynamicLightBehavior) source);
    }

    static boolean remove(Object manager, Object source) {
        return ((DynamicLightBehaviorManager) manager).remove((DynamicLightBehavior) source);
    }
}
