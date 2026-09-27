package top.fancyflow.stardewaccessories;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(StardewAccessories.MODID)
public class StardewAccessories {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "stardewaccessories";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // 创造页签也是一个注册表对象，用同样的 DeferredRegister 注册
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

/*
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STARDEW_ACCESSORIES_TAB =
            CREATIVE_MODE_TABS.register("stardewaccessories", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.stardewaccessories"))
                    // 排在原版"材料"页签后面
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    // 页签图标
                    .icon(() -> new ItemStack()) // TODO: 添加图标
                    // 决定这个页签里显示哪些物品（顺序即显示顺序）
                    .displayItems((params, output) -> {
                        // TODO: 添加物品
                    })
                    .build());

 */

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public StardewAccessories(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
