package codenamed.flavored.registry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FlavoredItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(codenamed.flavored.Flavored.MOD_ID, name), item);

    }




    public static void registerItems() {
        codenamed.flavored.Flavored.LOGGER.info("Registering items for " + codenamed.flavored.Flavored.MOD_ID);
    }
}
