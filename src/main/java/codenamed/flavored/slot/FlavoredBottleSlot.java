package codenamed.flavored.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class FlavoredBottleSlot extends Slot {
    public FlavoredBottleSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return FlavoredBottleSlot.isBottle(stack);
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return FlavoredBottleSlot.isBottle(stack) ? 1 : super.getMaxItemCount(stack);
    }

    public static boolean isBottle(ItemStack stack) {
        return stack.isOf(Items.GLASS_BOTTLE);
    }
}