package top.fancyflow.stardewaccessories.item;

import net.minecraft.world.item.Item;

// 磁铁戒指：佩戴后把附近更大范围内的掉落物吸向玩家
public class MagnetRing extends AbstractMagnetRing {

    public MagnetRing(Item.Properties properties) {
        super(properties, "tooltip.stardewaccessories.magnet_ring",
                "tooltip.stardewaccessories.magnet_ring.desc");
    }
}
