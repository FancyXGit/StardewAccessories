package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 由巴的戒指：受到伤害时有概率获得由巴的祝福（短时间内免疫伤害；效果见 RingOfYobaEvents / YobasBlessingEffect）
public class RingOfYoba extends DescribedItem implements ICurioItem {

    public RingOfYoba(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.ring_of_yoba.desc");
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.ring_of_yoba")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
