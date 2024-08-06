package codenamed.flavored.block.custom;

import codenamed.flavored.block.entity.BoilerBlockEntity;
import codenamed.flavored.registry.FlavoredBlockEntityType;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class BoilerBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final BooleanProperty LIT = BooleanProperty.of("lit");


    public static final IntProperty WATER = IntProperty.of("water", 0, 3);

    public  static  final  int MAX_WATER = 3;


    public BoilerBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(LIT, false));

    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    private static VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 6, 3),
            Block.createCuboidShape(3, 0, 3, 13, 1, 13),
            Block.createCuboidShape(2, 0, 13, 14, 6, 14),
            Block.createCuboidShape(2, 0, 3, 3, 6, 13),
            Block.createCuboidShape(13, 0, 3, 14, 6, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, WATER, LIT});

    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BoilerBlockEntity) {
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {
        BlockEntity b = world.getBlockEntity(pos);




        return ActionResult.SUCCESS;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return super.getComparatorOutput(state, world, pos);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.getBlockState(pos.down()).getBlock() == Blocks.MAGMA_BLOCK || world.getBlockState(pos.down()).getBlock() == Blocks.FIRE || world.getBlockState(pos.down()).getBlock() == Blocks.CAMPFIRE || world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_CAMPFIRE || world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_FIRE || world.getBlockState(pos.down()).getBlock() == Blocks.LAVA || world.getBlockState(pos.down()).getBlock() == Blocks.LAVA_CAULDRON) {
            world.setBlockState(pos, state.with(LIT, true));
        }
        else {
            world.setBlockState(pos, state.with(LIT, false));

        }
        BlockEntity b = world.getBlockEntity(pos);

        if ((Boolean)state.get(LIT) && (int)state.get(WATER) > 0) {
            double d = (double)pos.getX() + 0.5;
            double e = (double)pos.getY() + 1;
            double f = (double)pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.1F, 1.5F, false);
            }

            Direction direction = (Direction)state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : h;
            double j = random.nextDouble() * 6.0 / 16.0;
            double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : h;
            world.addParticle(ParticleTypes.BUBBLE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoilerBlockEntity(pos, state);
    }




}