package codenamed.flavored.block.entity;

import codenamed.flavored.registry.FlavoredBlockEntityType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class PanBlockEntity extends BlockEntity {
    public PanBlockEntity(BlockPos pos, BlockState state) {
        super(FlavoredBlockEntityType.PAN, pos, state);
    }
}
