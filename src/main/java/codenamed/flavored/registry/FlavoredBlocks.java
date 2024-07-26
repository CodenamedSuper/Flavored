package codenamed.flavored.registry;

import codenamed.flavored.Flavored;
import codenamed.flavored.block.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemKeys;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class FlavoredBlocks {
    public static final Block TOMATO_BUSH = registerBlockWithoutItem("tomato_bush",
            new TomatoBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));

    public static final Block CUCUMBER_BUSH = registerBlockWithoutItem("cucumber_bush",
            new CucumberBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
    public static final Block PEPPER_BUSH = registerBlockWithoutItem("pepper_bush",
            new PepperBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
    public static final Block CORN_BUSH = registerBlockWithoutItem("corn_bush",
            new CornBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));

    public static final Block ROSEMARY_BUSH = registerBlockWithoutItem("rosemary_bush",
            new RosemaryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));

    public static final Block GARLICS = registerBlock("garlics",
            new CropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS)));

    public static final Block CAULIFLOWER = registerBlock("cauliflower",
            new CauliflowerBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block CARVED_CAULIFLOWER = registerBlock("carved_cauliflower",
            new WearableCarvedCauliflowerBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block ATTACHED_CAULIFLOWER_STEM =
            registerBlock ("attached_cauliflower_stem", new AttachedStemBlock(FlavoredBlockKeys.CAULIFLOWER_STEM, FlavoredBlockKeys.CAULIFLOWER, FlavoredItemKeys.CAULIFLOWER_SEEDS, AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block CAULIFLOWER_STEM =
            registerBlock("cauliflower_stem", new StemBlock(FlavoredBlockKeys.CAULIFLOWER, FlavoredBlockKeys.ATTACHED_CAULIFLOWER_STEM, FlavoredItemKeys.CAULIFLOWER_SEEDS, AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.STEM).pistonBehavior(PistonBehavior.DESTROY)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Flavored.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(Flavored.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
        return item;
    }
    private static Block registerBlockWithoutItem(String name, Block block) {

        return Registry.register(Registries.BLOCK, Identifier.of(Flavored.MOD_ID, name), block);
    }


    public static void registerModBlocks() {
        Flavored.LOGGER.info("Registering ModBlocks for " + Flavored.MOD_ID);
    }
}
