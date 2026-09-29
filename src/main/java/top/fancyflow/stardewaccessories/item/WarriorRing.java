package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 战士戒指：击杀敌对生物时按概率获得战士能量（效果见 MonsterKillEvents / WarriorEnergyEffect）
public class WarriorRing extends DescribedItem implements ICurioItem {

    public WarriorRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.warrior_ring.desc");
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.warrior_ring")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
