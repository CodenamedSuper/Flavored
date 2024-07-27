package codenamed.flavored.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.Slot;

public class FlavoredFermentingSlot extends Slot {
    public FlavoredFermentingSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return FlavoredFermentingSlot.isFermenting(stack);
    }

    public static boolean isFermenting(ItemStack stack) {
        return stack.isOf(Items.SUGAR) || stack.isOf(Items.GLOW_LICHEN) || stack.isOf(Items.BROWN_MUSHROOM) || stack.isOf(Items.RED_MUSHROOM) || stack.isOf(Items.WARPED_FUNGUS) ||stack.isOf(Items.CRIMSON_FUNGUS);
    }
}