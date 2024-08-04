package codenamed.flavored.registry;

import codenamed.flavored.Flavored;
import codenamed.flavored.recipe.FermentingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface FlavoredRecipeType<T extends Recipe<?>> {
    RecipeType<FermentingRecipe> FERMENTING = register("fermenting");


    static <T extends Recipe<?>> RecipeType register(final String id) {
        return (RecipeType)Registry.register(Registries.RECIPE_TYPE, Identifier.ofVanilla(id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }


    public static void registerScreenHanlerTypes() {
        Flavored.LOGGER.info("Registering screen handler types for " + Flavored.MOD_ID);
    }

}