package top.fancyflow.stardewaccessories;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import top.fancyflow.stardewaccessories.registry.ModBlocks;
import top.fancyflow.stardewaccessories.registry.ModCreativeTabs;
import top.fancyflow.stardewaccessories.registry.ModItems;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(StardewAccessories.MODID)
public class StardewAccessories {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "stardewaccessories";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public StardewAccessories(IEventBus modEventBus, ModContainer modContainer) {
        // 各注册表统一在这里接入模组事件总线
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
    }
}
