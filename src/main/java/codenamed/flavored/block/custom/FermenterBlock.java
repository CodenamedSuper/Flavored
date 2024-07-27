package codenamed.flavored.block.custom;

import codenamed.flavored.block.entity.FermenterBlockEntity;
import codenamed.flavored.helper.Color;
import codenamed.flavored.registry.FlavoredBlockEntityType;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FermenterBlock extends BlockWithEntity implements BlockEntityProvider {

public  static  final  IntProperty LIQUID = IntProperty.of("liquid", 0, 3);

public  static  final EnumProperty<Color> COLOR = EnumProperty.of("color", Color.class);

    public FermenterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIQUID, 0));

    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;




    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {




        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {



        BlockEntity b = world.getBlockEntity(pos);

        if (state.get(LIQUID) == 1 || state.get(LIQUID) == 2) {
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


    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return true;
    }

    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        BlockEntity b = world.getBlockEntity(pos);

        if (fluid == Fluids.WATER && b instanceof FermenterBlockEntity) {

            if (((FermenterBlockEntity) b).getStack(3).getItem() == Items.BUCKET) {

                world.setBlockState(pos, (BlockState) state.with(LIQUID, 1));
            }

        }

    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos blockPos = PointedDripstoneBlock.getDripPos(world, pos);
        if (blockPos != null) {
            Fluid fluid = PointedDripstoneBlock.getDripFluid(world, blockPos);
            if (fluid != Fluids.EMPTY && this.canBeFilledByDripstone(fluid)) {
                this.fillFromDripstone(state, world, pos, fluid);
            }

        }
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FermenterBlockEntity(pos, state);
    }

    @Override
    public  BlockRenderType getRenderType(BlockState state) {
        return  BlockRenderType.MODEL;
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
        builder.add(new Property[]{LIQUID, COLOR, FACING});
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FermenterBlockEntity) {
                ItemScatterer.spawn(world, pos, (FermenterBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((FermenterBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {

        return state.get(LIQUID);
    }


}