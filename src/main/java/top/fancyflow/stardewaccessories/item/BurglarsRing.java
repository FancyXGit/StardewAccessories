package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 窃贼戒指：击杀敌对怪物时有概率获得额外掉落（近似原版抢夺I；效果见 BurglarsRingEvents）
public class BurglarsRing extends DescribedItem implements ICurioItem {

    public BurglarsRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.burglars_ring.desc");
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.burglars_ring")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
