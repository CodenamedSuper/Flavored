package codenamed.flavored.registry;

import codenamed.flavored.Flavored;
import codenamed.flavored.screen.FermenterScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class FlavoredScreenHandlerType<T extends ScreenHandler> implements ToggleableFeature {

    public static final ScreenHandlerType<FermenterScreenHandler> FERMENTER = register("fermenter", FermenterScreenHandler::new);

    private final FeatureSet requiredFeatures;
    private final ScreenHandlerType.Factory<T> factory;

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Flavored.MOD_ID, id), new ScreenHandlerType<T>(factory, FeatureFlags.VANILLA_FEATURES));
    }

    public FlavoredScreenHandlerType(ScreenHandlerType.Factory<T> factory, FeatureSet requiredFeatures) {
        this.factory = factory;
        this.requiredFeatures = requiredFeatures;
    }

    public T create(int syncId, PlayerInventory playerInventory) {
        return this.factory.create(syncId, playerInventory);
    }

    public FeatureSet getRequiredFeatures() {
        return this.requiredFeatures;
    }

    public interface Factory<T extends ScreenHandler> {
        T create(int syncId, PlayerInventory playerInventory);
    }

    public static void registerScreenHandlerTypes() {
        Flavored.LOGGER.info("Registering screen handler types for " + Flavored.MOD_ID);
    }
}