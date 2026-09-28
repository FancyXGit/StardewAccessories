package top.fancyflow.stardewaccessories.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.PercentageAttribute;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 自定义属性的注册集中在这里；新增属性时在这里加字段
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, StardewAccessories.MODID);

    // 暴击率：基础 0，范围 0~10；PercentageAttribute 会按百分比显示（0.05 = +5%）
    public static final DeferredHolder<Attribute, Attribute> CRIT_CHANCE =
            ATTRIBUTES.register("crit_chance",
                    () -> new PercentageAttribute("attribute.name.stardewaccessories.crit_chance",
                            0.0D, 0.0D, 10.0D).setSyncable(true));

    // 暴击伤害：加到暴击倍率上的额外值（0.10 = 暴击倍率 1.5 -> 1.6）
    public static final DeferredHolder<Attribute, Attribute> CRIT_DAMAGE =
            ATTRIBUTES.register("crit_damage",
                    () -> new PercentageAttribute("attribute.name.stardewaccessories.crit_damage",
                            0.0D, 0.0D, 10.0D).setSyncable(true));

    // EntityAttributeModificationEvent 实现 IModBusEvent，会自动挂到 mod 事件总线
    @SubscribeEvent
    private static void addPlayerAttributes(EntityAttributeModificationEvent event) {
        // 不加这一行玩家身上没有这些属性，戒指的属性修饰符就不会生效
        event.add(EntityType.PLAYER, CRIT_CHANCE);
        event.add(EntityType.PLAYER, CRIT_DAMAGE);
    }

    public static void register(IEventBus modEventBus) {
        ATTRIBUTES.register(modEventBus);
    }
}
