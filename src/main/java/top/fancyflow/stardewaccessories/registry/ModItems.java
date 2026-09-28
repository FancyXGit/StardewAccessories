package top.fancyflow.stardewaccessories.registry;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.item.RubyRing;

// 所有物品的注册集中在这里；新增物品时在对应分组下加一行
public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(StardewAccessories.MODID);

    // 鸡图标
    public static final DeferredItem<Item> CHICKEN = ITEMS.registerSimpleItem("chicken", new Item.Properties());

    // 材料
    // 空白戒指
    public static final DeferredItem<Item> RING_BLANK = ITEMS.registerSimpleItem("ring_blank", new Item.Properties());
    // 红宝石
    public static final DeferredItem<Item> RUBY = ITEMS.registerSimpleItem("ruby", new Item.Properties());

    // 戒指
    // 红宝石戒指：加10%伤害
    public static final DeferredItem<RubyRing> RUBY_RING = ITEMS.registerItem("ruby_ring", RubyRing::new, new Item.Properties());

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
