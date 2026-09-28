package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 小型光辉戒指：佩戴后由客户端动态光照点亮周围（需要额外安装 LambDynamicLights 才有效果）
public class SmallGlowRing extends Item implements ICurioItem {

    public SmallGlowRing(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        // 前置提示（物品备注）
        tooltip.add(Component.translatable("tooltip.stardewaccessories.small_glow_ring.requires_lambdynlights")
                .withStyle(ChatFormatting.DARK_GRAY));
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.small_glow_ring")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
