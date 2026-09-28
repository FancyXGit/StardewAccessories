package top.fancyflow.stardewaccessories.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import top.fancyflow.stardewaccessories.StardewAccessories;

// 数据生成总入口：运行 `gradlew runData` 时，这里把各个生成器注册进生成流程
@EventBusSubscriber(modid = StardewAccessories.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // 客户端资源：方块外观、物品模型、语言
        generator.addProvider(event.includeClient(), new ModBlockStates(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModels(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModLanguageEnglish(output));
        generator.addProvider(event.includeClient(), new ModLanguageChinese(output));

        // 服务端数据：配方、掉落表、标签
        generator.addProvider(event.includeServer(), new ModRecipes(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLootTables(output, lookupProvider));
        // 物品标签要用方块标签生成器的 contentsGetter()，所以先建出来
        BlockTagsProvider blockTags = new ModBlockTags(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(),
                new ModItemTags(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

        // 服务端数据：世界生成（矿石自然生成）
        generator.addProvider(event.includeServer(), new ModWorldGen(output, lookupProvider));
    }
}
