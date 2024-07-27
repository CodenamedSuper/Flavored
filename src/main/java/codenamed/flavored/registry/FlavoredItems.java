package codenamed.flavored.registry;

import codenamed.flavored.Flavored;
import codenamed.flavored.item.custom.DefaultSeedItem;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class FlavoredItems {

    public static final Item TOMATO = registerItem("tomato",
            new Item(new Item.Settings().food(FlavoredFoodComponents.TOMATO)));

    public static final Item CUCUMBER = registerItem("cucumber",
            new Item(new Item.Settings().food(FlavoredFoodComponents.CUCUMBER)));

    public static final Item PEPPER = registerItem("pepper",
            new Item(new Item.Settings().food(FlavoredFoodComponents.PEPPER)));

    public static final Item CORN = registerItem("corn",
            new Item(new Item.Settings().food(FlavoredFoodComponents.CORN)));

    //
    public static final Item ROSEMARY = registerItem("rosemary",
            new DefaultSeedItem(FlavoredBlocks.ROSEMARY_BUSH, new Item.Settings()));

    public static final Item GARLIC = registerItem("garlic",
            new AliasedBlockItem(FlavoredBlocks.GARLICS, new Item.Settings().food(FlavoredFoodComponents.GARLIC)));

    public static final Item CAULIFLOWER_CURD = registerItem("cauliflower_curd",
            new Item(new Item.Settings().food(FlavoredFoodComponents.CAULIFLOWER_CURD)));

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new DefaultSeedItem(FlavoredBlocks.TOMATO_BUSH, new Item.Settings()));

    public static final Item CUCUMBER_SEEDS = registerItem("cucumber_seeds",
            new DefaultSeedItem(FlavoredBlocks.CUCUMBER_BUSH, new Item.Settings()));

    public static final Item PEPPER_SEEDS = registerItem("pepper_seeds",
            new DefaultSeedItem(FlavoredBlocks.PEPPER_BUSH, new Item.Settings()));

    public static final Item CORN_SEEDS = registerItem("corn_seeds",
            new DefaultSeedItem(FlavoredBlocks.CORN_BUSH, new Item.Settings()));


    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            new AliasedBlockItem(FlavoredBlocks.CAULIFLOWER_STEM, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Flavored.MOD_ID, name), item);

    }


    public static void registerModItems() {
        Flavored.LOGGER.info("Registering Mod Items for " + Flavored.MOD_ID);
    }

}
