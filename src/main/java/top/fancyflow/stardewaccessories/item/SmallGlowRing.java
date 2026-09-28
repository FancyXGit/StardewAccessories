package top.fancyflow.stardewaccessories.item;

import net.minecraft.world.item.Item;

// 小型光辉戒指：佩戴后由客户端动态光照略微照亮周围（需要额外安装 LambDynamicLights 才有效果）
public class SmallGlowRing extends AbstractGlowRing {

    public SmallGlowRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.small_glow_ring");
    }
}
