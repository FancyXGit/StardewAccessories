package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 野蛮人戒指：每击杀一个敌对生物获得短时间速度提升（效果见 MonsterKillEvents）
public class SavageRing extends DescribedItem implements ICurioItem {

    public SavageRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.savage_ring.desc");
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.savage_ring")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
