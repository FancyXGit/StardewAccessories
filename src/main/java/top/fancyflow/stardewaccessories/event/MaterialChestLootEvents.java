package top.fancyflow.stardewaccessories.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.config.Config;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 磁石碎块：往矿洞/地牢的箱子战利品表里加一个概率池（对应星露谷"翻矿洞箱子"）。
// 用 LootTableLoadEvent 在加载时注入，概率直接读 Config，无需 datagen 的静态 JSON。
// 注意：每次资源重载都会重新反序列化出全新的战利品表，所以这里的注入不会重复叠加。
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class MaterialChestLootEvents {

    private static final ResourceLocation ABANDONED_MINESHAFT =
            ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft");
    private static final ResourceLocation SIMPLE_DUNGEON =
            ResourceLocation.withDefaultNamespace("chests/simple_dungeon");
    private static final String POOL_NAME = "stardewaccessories:magnet_fragments";

    @SubscribeEvent
    private static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (!name.equals(ABANDONED_MINESHAFT) && !name.equals(SIMPLE_DUNGEON)) {
            return;
        }

        LootTable table = event.getTable();
        // 池名唯一，避免已被其它来源注入过
        if (table.getPool(POOL_NAME) != null) {
            return;
        }

        table.addPool(LootPool.lootPool()
                .name(POOL_NAME)
                .setRolls(ConstantValue.exactly(1.0F))
                .when(LootItemRandomChanceCondition.randomChance(
                        Config.MAGNET_FRAGMENTS_CHEST_CHANCE.get().floatValue()))
                .add(LootItem.lootTableItem(ModItems.MAGNET_FRAGMENTS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                .build());
    }
}
