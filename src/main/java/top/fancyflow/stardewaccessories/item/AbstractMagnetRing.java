package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 磁铁戒指的公共部分：物品备注里的风味描述 + Curios 作用区里的作用行。
// 作用行文案由子类通过 effectKey 传入。
public abstract class AbstractMagnetRing extends DescribedItem implements ICurioItem {

    private final String effectKey;

    protected AbstractMagnetRing(Item.Properties properties, String effectKey, String descriptionKey) {
        super(properties, descriptionKey);
        this.effectKey = effectKey;
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
