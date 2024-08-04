package codenamed.flavored.block.entity;


import codenamed.flavored.Flavored;
import codenamed.flavored.block.custom.FermenterBlock;
import codenamed.flavored.helper.Color;
import codenamed.flavored.item.custom.DrinkItem;
import codenamed.flavored.recipe.FermentingRecipe;
import codenamed.flavored.registry.FlavoredBlockEntityType;
import codenamed.flavored.registry.FlavoredRecipeType;
import codenamed.flavored.registry.FlavoredScreenHandlerType;
import codenamed.flavored.screen.FermenterScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FermenterBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<FermenterScreenHandler>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private static final int FERMENTING_SLOT = 2;

    private static final int LIQUID_SLOT = 3;

    private static final int BOTTLE_SLOT = 4;



    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 70;

    public FermenterBlockEntity(BlockPos pos, BlockState state) {
        super(FlavoredBlockEntityType.FERMENTER, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FermenterBlockEntity.this.progress;
                    case 1 -> FermenterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FermenterBlockEntity.this.progress = value;
                    case 1 -> FermenterBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 5;
            }
        };
    }


    @Override
    public Text getDisplayName() {
        return Text.literal("Fermenter");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public boolean hasWater() {

        return  this.getStack(LIQUID_SLOT).getItem() == Items.WATER_BUCKET;
    }

    public boolean hasMilk() {

        return  this.getStack(LIQUID_SLOT).getItem() == Items.MILK_BUCKET;
    }


    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FermenterScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        Item i = getStack(OUTPUT_SLOT).getItem();

        if(getStack(LIQUID_SLOT).getItem() == Items.WATER_BUCKET) {
            state = (BlockState)state.with(FermenterBlock.LIQUID, 1).with(FermenterBlock.COLOR, Color.none);

        }
        else if (getStack(LIQUID_SLOT).getItem() == Items.MILK_BUCKET) {
            state = (BlockState)state.with(FermenterBlock.LIQUID, 2).with(FermenterBlock.COLOR, Color.none);
        }
       else if(i instanceof DrinkItem && !getStack(OUTPUT_SLOT).isEmpty()) {
            state = (BlockState)state.with(FermenterBlock.LIQUID, 0).with(FermenterBlock.COLOR, Color.valueOf(((DrinkItem) i).COLOR));

        }
        else {
            state = (BlockState)state.with(FermenterBlock.LIQUID, 0).with(FermenterBlock.COLOR, Color.none);

        }

        world.setBlockState(pos, state);

        if(isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<FermentingRecipe>> recipe = getCurrentRecipe();



        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(FERMENTING_SLOT, 1);
        if (!getStack(LIQUID_SLOT).isEmpty()) {
            this.removeStack(LIQUID_SLOT, 1);
            this.setStack(LIQUID_SLOT, Items.BUCKET.getDefaultStack());
        }
        else {
            this.removeStack(LIQUID_SLOT, 1);
        }
        this.removeStack(BOTTLE_SLOT, 1);






        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress += 1;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<FermentingRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<FermentingRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return null;

    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    public FermenterScreenHandler getScreenOpeningData(ServerPlayerEntity player) {
        return null;
    }
}
