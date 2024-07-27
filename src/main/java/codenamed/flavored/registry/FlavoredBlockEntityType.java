package codenamed.flavored.registry;

import codenamed.flavored.Flavored;
import codenamed.flavored.block.entity.FermenterBlockEntity;
import com.mojang.datafixers.types.Type;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class FlavoredBlockEntityType {

    public static final BlockEntityType<FermenterBlockEntity> FERMENTER;



    @Nullable
    public static Identifier getId(BlockEntityType<?> type) {
        return Registries.BLOCK_ENTITY_TYPE.getId(type);
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {

        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Flavored.MOD_ID, id), builder.build(type));
    }

    static {
        FERMENTER = create("fermenter", BlockEntityType.Builder.create(FermenterBlockEntity::new, FlavoredBlocks.FERMENTER));
    }

    public static final class Builder<T extends BlockEntity> implements FabricBlockEntityType.Builder<T> {
        private final BlockEntityType.BlockEntityFactory<? extends T> factory;
        final Set<Block> blocks;

        private Builder(BlockEntityType.BlockEntityFactory<? extends T> factory, Set<Block> blocks) {
            this.factory = factory;
            this.blocks = blocks;
        }




        public BlockEntityType<T> build(Type<?> type) {
            return new BlockEntityType(this.factory, this.blocks, type);
        }
    }

    @FunctionalInterface
    private interface BlockEntityFactory<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }


}
