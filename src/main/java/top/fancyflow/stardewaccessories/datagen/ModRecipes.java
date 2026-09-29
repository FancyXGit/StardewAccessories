package top.fancyflow.stardewaccessories.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import top.fancyflow.stardewaccessories.StardewAccessories;
import top.fancyflow.stardewaccessories.registry.ModItems;

// 生成 data/stardewaccessories/recipe/*.json 以及配方书解锁进度
public class ModRecipes extends RecipeProvider {
    public ModRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // 无序合成： 空白戒指 + 红宝石 = 红宝石戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY_RING.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 绿宝石 = 绿宝石戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.EMERALD_RING.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(ModItems.EMERALD.get())
                .unlockedBy("has_emerald", has(ModItems.EMERALD.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 翡翠 = 翡翠戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.JADE_RING.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(ModItems.JADE.get())
                .unlockedBy("has_jade", has(ModItems.JADE.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 海蓝宝石 = 海蓝宝石戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AQUAMARINE_RING.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(ModItems.AQUAMARINE.get())
                .unlockedBy("has_aquamarine", has(ModItems.AQUAMARINE.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 黄水晶 = 黄水晶戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOPAZ_RING.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(ModItems.TOPAZ.get())
                .unlockedBy("has_topaz", has(ModItems.TOPAZ.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 紫水晶 = 紫水晶戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AMETHYST_RING.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(ModItems.AMETHYST.get())
                .unlockedBy("has_amethyst", has(ModItems.AMETHYST.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 萤石粉 + 金锭 = 流明空白戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LUMENITE_RING_BLANK.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(Items.GLOWSTONE_DUST)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_ring_blank", has(ModItems.RING_BLANK.get()))
                .save(recipeOutput);

        // 无序合成： 流明空白戒指 + 流明尘 = 小型光辉戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SMALL_GLOW_RING.get())
                .requires(ModItems.LUMENITE_RING_BLANK.get())
                .requires(ModItems.LUMEN_DUST.get())
                .unlockedBy("has_lumen_dust", has(ModItems.LUMEN_DUST.get()))
                .save(recipeOutput);

        // 无序合成： 空白戒指 + 2 个铁锭 = 金属空白戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.METAL_RING_BLANK.get())
                .requires(ModItems.RING_BLANK.get())
                .requires(Items.IRON_INGOT, 2)
                .unlockedBy("has_ring_blank", has(ModItems.RING_BLANK.get()))
                .save(recipeOutput);

        // 无序合成： 金属空白戒指 + 磁石碎块 = 小型磁铁戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SMALL_MAGNET_RING.get())
                .requires(ModItems.METAL_RING_BLANK.get())
                .requires(ModItems.MAGNET_FRAGMENTS.get())
                .unlockedBy("has_magnet_fragments", has(ModItems.MAGNET_FRAGMENTS.get()))
                .save(recipeOutput);

        // 无序合成： 4 个流明尘 = 流明晶
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LUMENITE.get())
                .requires(ModItems.LUMEN_DUST.get(), 4)
                .unlockedBy("has_lumen_dust", has(ModItems.LUMEN_DUST.get()))
                .save(recipeOutput);

        // 无序合成： 4 个磁石碎块 = 磁石
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGNET.get())
                .requires(ModItems.MAGNET_FRAGMENTS.get(), 4)
                .unlockedBy("has_magnet_fragments", has(ModItems.MAGNET_FRAGMENTS.get()))
                .save(recipeOutput);

        // 无序合成： 流明空白戒指 + 史莱姆结晶 = 史莱姆克星戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SLIME_CHARMER_RING.get())
                .requires(ModItems.LUMENITE_RING_BLANK.get())
                .requires(ModItems.SLIME_CRYSTAL.get())
                .unlockedBy("has_slime_crystal", has(ModItems.SLIME_CRYSTAL.get()))
                .save(recipeOutput);

        // 有序合成： 战士戒指（金属空白戒指居中，上下下界合金锭，左右红石）
        //     K
        //   L J L
        //     K
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WARRIOR_RING.get())
                .pattern(" K ")
                .pattern("LJL")
                .pattern(" K ")
                .define('K', Items.NETHERITE_INGOT)
                .define('L', Items.REDSTONE)
                .define('J', ModItems.METAL_RING_BLANK.get())
                .unlockedBy("has_metal_ring_blank", has(ModItems.METAL_RING_BLANK.get()))
                .save(recipeOutput);

        // 无序合成： 流明空白戒指 + 流明晶 = 光辉戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLOW_RING.get())
                .requires(ModItems.LUMENITE_RING_BLANK.get())
                .requires(ModItems.LUMENITE.get())
                .unlockedBy("has_lumenite", has(ModItems.LUMENITE.get()))
                .save(recipeOutput);

        // 无序合成： 小型光辉戒指 + 流明晶 = 光辉戒指（升级路线；产物与上一条相同，用单独 id 避免覆盖）
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLOW_RING.get())
                .requires(ModItems.SMALL_GLOW_RING.get())
                .requires(ModItems.LUMENITE.get())
                .unlockedBy("has_small_glow_ring", has(ModItems.SMALL_GLOW_RING.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(
                        StardewAccessories.MODID, "glow_ring_from_small_glow_ring"));

        // 无序合成： 金属空白戒指 + 磁石 = 磁铁戒指
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGNET_RING.get())
                .requires(ModItems.METAL_RING_BLANK.get())
                .requires(ModItems.MAGNET.get())
                .unlockedBy("has_magnet", has(ModItems.MAGNET.get()))
                .save(recipeOutput);

        // 无序合成： 小型磁铁戒指 + 磁石 = 磁铁戒指（升级路线；产物与上一条相同，用单独 id 避免覆盖）
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGNET_RING.get())
                .requires(ModItems.SMALL_MAGNET_RING.get())
                .requires(ModItems.MAGNET.get())
                .unlockedBy("has_small_magnet_ring", has(ModItems.SMALL_MAGNET_RING.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(
                        StardewAccessories.MODID, "magnet_ring_from_small_magnet_ring"));
    }
}
