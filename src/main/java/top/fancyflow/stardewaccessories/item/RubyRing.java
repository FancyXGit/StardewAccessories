package top.fancyflow.stardewaccessories.item;

import com.google.common.collect.Multimap;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RubyRing extends Item implements ICurioItem {

    public RubyRing(Item.Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers =
                ICurioItem.super.getAttributeModifiers(slotContext, id, stack);

        // 用槽位索引生成唯一 id，使多个同类槽位的加成互不覆盖
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(
                StardewAccessories.MODID,
                "ruby_ring_damage_" + slotContext.identifier() + "_" + slotContext.index());

        // +10% 攻击伤害；ADD_MULTIPLIED_BASE 之间相加，如果两个戒指合计 +20%
        modifiers.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(modifierId, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifiers;
    }
}
