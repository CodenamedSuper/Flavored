package codenamed.flavored.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DefaultSeedItem extends AliasedBlockItem {

    private final Block block;

    public DefaultSeedItem(Block block, Settings settings) {
        super(block, settings);
        this.block = block;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if (player == null) {
            return ActionResult.FAIL;
        }

        if (state.isOf(Blocks.FARMLAND)) {
            world.setBlockState(pos.up(), block.getDefaultState());
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.3f);

            if (!player.isInCreativeMode()) {
                stack.decrement(1);
            }

            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }
}
