package top.fancyflow.stardewaccessories.client.dynamiclights;

import dev.lambdaurora.lambdynlights.api.DynamicLightsContext;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;

// LambDynamicLights 的入口点：只有安装了 LambDynamicLights 时，它才会来加载这个类。
// 没装时这个类根本不会被加载，也就不会因为缺少依赖而崩溃（软依赖）。
public class StardewDynamicLightsInitializer implements DynamicLightsInitializer {

    @Override
    public void onInitializeDynamicLights(DynamicLightsContext context) {
        // 拿到"自定义动态光源"管理器，交给我们的管理器使用
        GlowRingLightManager.setManager(context.dynamicLightBehaviorManager());
    }

    // 旧版接口，4.8.x 仍然保留为抽象方法，必须实现；我们只用新版接口，这里留空。
    @Override
    @SuppressWarnings("removal")
    public void onInitializeDynamicLights(ItemLightSourceManager itemLightSourceManager) {
        // 不使用旧接口
    }
}
