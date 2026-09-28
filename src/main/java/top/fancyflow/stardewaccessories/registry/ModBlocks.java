package top.fancyflow.stardewaccessories.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 所有方块的注册集中在这里；新增方块时在这里加字段
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(StardewAccessories.MODID);

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
