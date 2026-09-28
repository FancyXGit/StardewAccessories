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
import top.fancyflow.stardewaccessories.config.Config;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 黄水晶戒指：盔甲 +6 点
public class TopazRing extends DescribedItem implements ICurioItem {

    public TopazRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.topaz_ring.desc");
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers =
                ICurioItem.super.getAttributeModifiers(slotContext, id, stack);

        // 用槽位索引生成唯一 id，使多个同类槽位的加成互不覆盖
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(
                StardewAccessories.MODID,
                "topaz_ring_armor_" + slotContext.identifier() + "_" + slotContext.index());

        // 盔甲是原版属性，ADD_VALUE 是加"点数"；两个戒指合计 +12 点
        modifiers.put(Attributes.ARMOR,
                new AttributeModifier(modifierId, Config.TOPAZ_RING_ARMOR.get(),
                        AttributeModifier.Operation.ADD_VALUE));

        return modifiers;
    }
}
