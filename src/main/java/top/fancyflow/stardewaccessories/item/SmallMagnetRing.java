package top.fancyflow.stardewaccessories.item;

import net.minecraft.world.item.Item;

// 小型磁铁戒指：佩戴后把附近的掉落物吸向玩家（效果见 MagnetRingEvents）
public class SmallMagnetRing extends AbstractMagnetRing {

    public SmallMagnetRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.small_magnet_ring",
                "tooltip.stardewaccessories.small_magnet_ring.desc");
    }
}
