package codenamed.flavored;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flavored implements ModInitializer {

	public static final String MOD_ID = "flavored";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {


		codenamed.flavored.registry.FlavoredBlocks.registerBlocks();
		codenamed.flavored.registry.FlavoredItems.registerItems();
		codenamed.flavored.registry.FlavoredItemGroup.registerItemGroups();

		LOGGER.info(MOD_ID);
	}
}