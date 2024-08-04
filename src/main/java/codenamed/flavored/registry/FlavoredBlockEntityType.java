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



    public static final BlockEntityType<FermenterBlockEntity> FERMENTER = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Flavored.MOD_ID, "fermenter"),
            BlockEntityType.Builder.create(FermenterBlockEntity::new, FlavoredBlocks.FERMENTER).build());

    public static final BlockEntityType<OvenBlockEntity> OVEN = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Flavored.MOD_ID, "oven"),
            BlockEntityType.Builder.create(OvenBlockEntity::new, FlavoredBlocks.OVEN).build()
    );

    public static final BlockEntityType<BoilerBlockEntity> BOILER = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Flavored.MOD_ID, "oven"),
            BlockEntityType.Builder.create(BoilerBlockEntity::new, FlavoredBlocks.BOILER).build()
    );
    public static final BlockEntityType<PanBlockEntity> PAN = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Flavored.MOD_ID, "oven"),
            BlockEntityType.Builder.create(PanBlockEntity::new, FlavoredBlocks.PAN).build()
    );


    public static void registerModBlocks() {
        Flavored.LOGGER.info("Registering Block Entity Types for " + Flavored.MOD_ID);
    }





}
