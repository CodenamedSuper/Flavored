package codenamed.flavored.block.entity;

import codenamed.flavored.registry.FlavoredBlockEntityType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class BoilerBlockEntity extends BlockEntity {
    public BoilerBlockEntity( BlockPos pos, BlockState state) {
        super(FlavoredBlockEntityType.BOILER, pos, state);
    }
}
