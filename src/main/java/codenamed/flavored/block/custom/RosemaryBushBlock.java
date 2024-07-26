package codenamed.flavored.block.custom;

import codenamed.flavored.registry.FlavoredItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;


public class RosemaryBushBlock extends SweetBerryBushBlock implements Fertilizable {

    public RosemaryBushBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {

    }


    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(FlavoredItems.ROSEMARY);
    }


    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int i = state.get(AGE);
        boolean bl = i == 3;
        if (!bl) {
            return ActionResult.PASS;
        } else {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(FlavoredItems.ROSEMARY, j + (bl ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(AGE, 1);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        int i = state.get(AGE);
        boolean bl = i == 3;
        if (i > 1 && itemStack.isOf(Items.SHEARS)) {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(FlavoredItems.ROSEMARY, j + (bl ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(AGE, 1);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));

            // Add shear durability decrease

            if(!player.isInCreativeMode()) stack.setDamage(stack.getDamage() + 1);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;

    }
}