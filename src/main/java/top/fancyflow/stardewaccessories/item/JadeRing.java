package top.fancyflow.stardewaccessories.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

// 翡翠戒指：暴击伤害 +10%（效果见 JadeRingEvents）
public class JadeRing extends Item implements ICurioItem {

    public JadeRing(Item.Properties properties) {
        super(properties);
    }

    // 暴击伤害不是原版属性，Curios 不会自动生成提示，这里手动补一行
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips,
                                                Item.TooltipContext context, ItemStack stack) {
        List<Component> list = new ArrayList<>(tooltips);
        if (list.isEmpty()) {
            // 没有属性修饰符时 Curios 不加槽位标题，自己补上，保持和其他戒指一致
            list.add(Component.empty());
            list.add(Component.translatable("curios.modifiers.ring").withStyle(ChatFormatting.GOLD));
        }
        list.add(Component.translatable("tooltip.stardewaccessories.jade_ring")
                .withStyle(ChatFormatting.BLUE));
        return list;
    }
}
