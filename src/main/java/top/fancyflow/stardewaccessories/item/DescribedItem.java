package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

// 带风味描述的物品基类：在物品备注里加一行灰色描述文案。
// 文案由子类通过 descriptionKey 传入，对应 lang 里的 tooltip.stardewaccessories.<id>.desc。
public class DescribedItem extends Item {

    private final String descriptionKey;

    public DescribedItem(Item.Properties properties, String descriptionKey) {
        super(properties);
        this.descriptionKey = descriptionKey;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable(this.descriptionKey).withStyle(ChatFormatting.DARK_GRAY));
    }
}
