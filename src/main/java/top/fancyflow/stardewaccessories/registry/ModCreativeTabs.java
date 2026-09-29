package top.fancyflow.stardewaccessories.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 创造模式页签的注册集中在这里
public class ModCreativeTabs {
    // 创造页签也是一个注册表对象，用同样的 DeferredRegister 注册
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StardewAccessories.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STARDEW_ACCESSORIES_TAB =
            CREATIVE_MODE_TABS.register("stardewaccessories", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.stardewaccessories"))
                    // 排在原版"材料"页签后面
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    // 页签图标
                    .icon(() -> new ItemStack(ModItems.CHICKEN.get()))
                    // 决定这个页签里显示哪些物品（顺序即显示顺序）
                    .displayItems((params, output) -> {
                        // 戒指
                        output.accept(ModItems.RUBY_RING.get());
                        output.accept(ModItems.EMERALD_RING.get());
                        output.accept(ModItems.JADE_RING.get());
                        output.accept(ModItems.AQUAMARINE_RING.get());
                        output.accept(ModItems.TOPAZ_RING.get());
                        output.accept(ModItems.AMETHYST_RING.get());
                        output.accept(ModItems.SMALL_GLOW_RING.get());
                        output.accept(ModItems.GLOW_RING.get());
                        output.accept(ModItems.SMALL_MAGNET_RING.get());
                        output.accept(ModItems.MAGNET_RING.get());
                        output.accept(ModItems.SLIME_CHARMER_RING.get());
                        output.accept(ModItems.WARRIOR_RING.get());
                        output.accept(ModItems.VAMPIRE_RING.get());
                        output.accept(ModItems.SAVAGE_RING.get());

                        // 标志
                        output.accept(ModItems.CHICKEN.get());

                        // 材料
                        // 戒指底座
                        output.accept(ModItems.RING_BLANK.get());
                        output.accept(ModItems.LUMENITE_RING_BLANK.get());
                        output.accept(ModItems.METAL_RING_BLANK.get());
                        output.accept(ModItems.DARK_ALLOY_RING_BLANK.get());
                        // 矿石材料
                        output.accept(ModItems.RUBY.get());
                        output.accept(ModItems.EMERALD.get());
                        output.accept(ModItems.JADE.get());
                        output.accept(ModItems.AQUAMARINE.get());
                        output.accept(ModItems.TOPAZ.get());
                        output.accept(ModItems.AMETHYST.get());
                        output.accept(ModItems.LUMEN_DUST.get());
                        output.accept(ModItems.LUMENITE.get());
                        output.accept(ModItems.MAGNET_FRAGMENTS.get());
                        output.accept(ModItems.MAGNET.get());
                        output.accept(ModItems.SLIME_CRYSTAL.get());
                        output.accept(ModItems.BLOOD_ESSENCE.get());

                    })
                    .build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
