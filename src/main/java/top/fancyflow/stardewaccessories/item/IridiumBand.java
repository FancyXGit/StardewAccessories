package top.fancyflow.stardewaccessories.item;

import java.util.List;

import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 铱环：同时拥有光辉戒指、磁铁戒指、红宝石戒指的效果。
// 红宝石的攻击加成在这里挂属性；磁铁与光辉分别由 MagnetRingEvents / GlowRingLightManager 识别本物品实现，
// 数值都沿用三种原戒指的配置，不额外新增配置项。
public class IridiumBand extends DescribedItem implements ICurioItem {

    public IridiumBand(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.iridium_band.desc");
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers =
                ICurioItem.super.getAttributeModifiers(slotContext, id, stack);

        // 红宝石戒指的效果：攻击伤害加成，数值沿用红宝石的配置
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(
                StardewAccessories.MODID,
                "iridium_band_damage_" + slotContext.identifier() + "_" + slotContext.index());
        modifiers.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(modifierId, Config.RUBY_RING_ATTACK_DAMAGE.get(),
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifiers;
    }

    // Curios 会依照上面的属性加成写入"佩戴戒指时"标题与攻击力行，这里追加光辉、磁铁两条作用行
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        // 未佩戴预览时属性列表可能为空，自己补齐标题，和磁铁/光辉戒指的表现保持一致
        if (tooltips.isEmpty()) {
            tooltips.add(Component.empty());
            tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        }
        tooltips.add(Component.translatable("tooltip.stardewaccessories.iridium_band.glow")
                .withStyle(ChatFormatting.BLUE));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.iridium_band.magnet")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        // 含光辉效果：未安装 LambDynamicLights 时不发光，照光辉戒指给出提示
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.stardewaccessories.requires_lambdynlights")
                .withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable("tooltip.stardewaccessories.not_installed")
                .withStyle(ChatFormatting.DARK_GRAY));
    }
}
