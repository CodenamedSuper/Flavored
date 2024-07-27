package codenamed.flavored.recipe;

import codenamed.flavored.block.entity.ImplementedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public abstract class FermentingRecipe implements Recipe<SingleStackRecipeInput> {
    protected final RecipeType<?> type;
    protected final CookingRecipeCategory category;
    protected final String group;
    protected final List<Ingredient> ingredients;
    protected final ItemStack result;
    protected final float experience;
    protected final int cookingTime;

    public FermentingRecipe(RecipeType<?> type, String group, CookingRecipeCategory category, List<Ingredient> ingredients, ItemStack result, float experience, int cookingTime, ImplementedInventory recipeItems) {
        this.type = type;
        this.category = category;
        this.group = group;
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        // Only fermenting and liquid slot

        if (ingredients.size() == 2) {
            if(ingredients.get(0).test(inventory.getStack(2)) && ingredients.get(1).test(inventory.getStack(3))) {
                return  true;
            }
        }
        // Only liquid slot
        else if (ingredients.size() == 1) {
            if(ingredients.get(0).test(inventory.getStack(0))) {
                return  true;
            }

        }
        // Ingredient, liquid and fermenting slot
        else if (ingredients.size() == 3) {
            if (ingredients.get(0).test(inventory.getStack(0))
                    && ingredients.get(1).test(inventory.getStack(2))
                    && ingredients.get(2).test(inventory.getStack(3))) {


                return true;

            }
        }
        // Every slot
        else if (ingredients.size() == 4) {
            if (ingredients.get(0).test(inventory.getStack(0))
                    && ingredients.get(1).test(inventory.getStack(2))
                    && ingredients.get(2).test(inventory.getStack(3))
                    && ingredients.get(3).test(inventory.getStack(4))) {


                return true;
            }
        }

        return  false;
    }

    public ItemStack craft(SingleStackRecipeInput singleStackRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        return this.result.copy();
    }

    public boolean fits(int width, int height) {
        return true;
    }

    public DefaultedList<Ingredient> getIngredients() {
        return (DefaultedList<Ingredient>) ingredients;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public RecipeType<?> getType() {
        return this.type;
    }

    public CookingRecipeCategory getCategory() {
        return this.category;
    }

    public interface RecipeFactory<T extends AbstractCookingRecipe> {
        T create(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime);
    }
}





