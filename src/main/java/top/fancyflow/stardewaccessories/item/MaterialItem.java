package top.fancyflow.stardewaccessories.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

// 材料物品基类：在物品备注里加一行灰色的"获取方式"说明。
// 文案由 sourceKey 传入，对应 lang 里的 tooltip.stardewaccessories.<id>.source。
// 可选再传 descriptionKey（风味描述），对应 tooltip.stardewaccessories.<id>.desc。
public class MaterialItem extends Item {

    private final String sourceKey;
    private final String descriptionKey;

    public MaterialItem(Item.Properties properties, String sourceKey) {
        this(properties, sourceKey, null);
    }

    public MaterialItem(Item.Properties properties, String sourceKey, String descriptionKey) {
        super(properties);
        this.sourceKey = sourceKey;
        this.descriptionKey = descriptionKey;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        // 获取方式用稍浅的灰色，风味描述沿用物品备注的深灰色
        tooltip.add(Component.translatable(this.sourceKey).withStyle(ChatFormatting.GRAY));
        if (this.descriptionKey != null) {
            tooltip.add(Component.translatable(this.descriptionKey).withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
