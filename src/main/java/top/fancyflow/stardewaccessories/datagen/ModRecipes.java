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
    }
}
