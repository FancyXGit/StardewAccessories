package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 小型磁铁戒指：佩戴后把附近的掉落物吸向玩家（效果见 MagnetRingEvents）
public class SmallMagnetRing extends DescribedItem implements ICurioItem {

    public SmallMagnetRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.small_magnet_ring.desc");
    }

    // 作用行放进 Curios 的"佩戴戒指时："作用区，和其它戒指的属性行并排
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("tooltip.stardewaccessories.small_magnet_ring")
                .withStyle(ChatFormatting.BLUE));
        return tooltips;
    }
}
