package codenamed.flavored.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FlavoredBlocks {




    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(codenamed.flavored.Flavored.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
        return item;
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(codenamed.flavored.Flavored.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {

        return Registry.register(Registries.BLOCK, Identifier.of(codenamed.flavored.Flavored.MOD_ID, name), block);
    }

    public static void registerBlocks() {
        codenamed.flavored.Flavored.LOGGER.info("Registering Blocks for " + codenamed.flavored.Flavored.MOD_ID);
    }
}
