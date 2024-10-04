package codenamed.flavored.registry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FlavoredItemGroup {
    public static ItemGroup TEMPLATE = Registry.register(Registries.ITEM_GROUP, Identifier.of(codenamed.flavored.Flavored.MOD_ID, "flavored"),
            FabricItemGroup.builder().displayName(Text.translatable("Flavored"))
                    .icon(() -> new ItemStack(Blocks.GRASS_BLOCK)).entries((displayContext, entries) -> {



                    }).build());

    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
    }
}
