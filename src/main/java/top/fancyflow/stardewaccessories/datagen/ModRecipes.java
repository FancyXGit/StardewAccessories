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
                .unlockedBy("has_ring_blank", has(ModItems.RING_BLANK.get()))
                .save(recipeOutput);
    }
}
