package codenamed.flavored;

import codenamed.flavored.registry.FlavoredBlocks;
import codenamed.flavored.registry.FlavoredScreenHandlerType;
import codenamed.flavored.screen.FermenterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class FlavoredClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.TOMATO_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.CUCUMBER_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.PEPPER_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.CORN_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ROSEMARY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.GARLICS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.CAULIFLOWER_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ATTACHED_CAULIFLOWER_STEM, RenderLayer.getCutout());

        HandledScreens.register(FlavoredScreenHandlerType.FERMENTER, FermenterScreen::new);

    }
}
