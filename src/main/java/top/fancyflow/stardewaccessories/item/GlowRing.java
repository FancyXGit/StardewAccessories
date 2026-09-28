package top.fancyflow.stardewaccessories.item;

import net.minecraft.world.item.Item;

// 光辉戒指：佩戴后由客户端动态光照明亮地照亮周围（需要额外安装 LambDynamicLights 才有效果）
public class GlowRing extends AbstractGlowRing {

    public GlowRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.glow_ring",
                "tooltip.stardewaccessories.glow_ring.desc");
    }
}
