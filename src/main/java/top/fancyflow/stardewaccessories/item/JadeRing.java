package top.fancyflow.stardewaccessories.item;

import com.google.common.collect.Multimap;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModAttributes;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 翡翠戒指：暴击伤害 +10%（效果见 CriticalHitEvents）
public class JadeRing extends DescribedItem implements ICurioItem {

    public JadeRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.jade_ring.desc");
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers =
                ICurioItem.super.getAttributeModifiers(slotContext, id, stack);

        // 用槽位索引生成唯一 id，使多个同类槽位的加成互不覆盖
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(
                StardewAccessories.MODID,
                "jade_ring_crit_damage_" + slotContext.identifier() + "_" + slotContext.index());

        // 暴击伤害加成，数值来自配置；ADD_VALUE 之间相加，如果两个戒指合计 +20%
        modifiers.put(ModAttributes.CRIT_DAMAGE,
                new AttributeModifier(modifierId, Config.JADE_RING_CRIT_DAMAGE.get(),
                        AttributeModifier.Operation.ADD_VALUE));

        return modifiers;
    }
}
