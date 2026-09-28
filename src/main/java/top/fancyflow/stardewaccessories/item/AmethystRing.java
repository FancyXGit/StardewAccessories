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

// 紫水晶戒指：攻击击退 +1 点（约等于击退附魔 I）
public class AmethystRing extends DescribedItem implements ICurioItem {

    public AmethystRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.amethyst_ring.desc");
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers =
                ICurioItem.super.getAttributeModifiers(slotContext, id, stack);

        // 用槽位索引生成唯一 id，使多个同类槽位的加成互不覆盖
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(
                StardewAccessories.MODID,
                "amethyst_ring_knockback_" + slotContext.identifier() + "_" + slotContext.index());

        // 攻击击退是原版属性，基础值 0，只能 ADD_VALUE 加点数；两个戒指合计 +2
        modifiers.put(Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(modifierId, Config.AMETHYST_RING_KNOCKBACK.get(),
                        AttributeModifier.Operation.ADD_VALUE));

        return modifiers;
    }
}
