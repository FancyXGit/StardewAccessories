package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 发光戒指的公共部分：物品备注里的前置提示 + Curios 作用区里的作用行。
// 作用行文案由子类通过 effectKey 传入。
public abstract class AbstractGlowRing extends Item implements ICurioItem {

    private final String effectKey;

    protected AbstractGlowRing(Item.Properties properties, String effectKey) {
        super(properties);
        this.effectKey = effectKey;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        // 前置提示（物品备注）
        // 注意：不要用 \n —— NeoForge 只在文本需要自动换行时才拆分换行符，短文本里的 \n 会原样显示。
        tooltip.add(Component.translatable("tooltip.stardewaccessories.requires_lambdynlights")
                .withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable("tooltip.stardewaccessories.not_installed")
                .withStyle(ChatFormatting.DARK_GRAY));
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable(this.effectKey).withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
