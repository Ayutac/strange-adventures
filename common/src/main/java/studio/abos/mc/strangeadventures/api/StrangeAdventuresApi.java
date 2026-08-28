package studio.abos.mc.strangeadventures.api;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.targetingmode.TargetingMode;

import java.lang.reflect.InvocationTargetException;

public class StrangeAdventuresApi {

    public static final ResourceKey<Registry<TargetingMode>> TARGETING_MODE_REGISTRY_KEY = ResourceKey.createRegistryKey(StrangeAdventures.id("targeting_mode"));

    public static final ResourceKey<Level> GREEN_DIMENSION = ResourceKey.create(Registries.DIMENSION, StrangeAdventures.id("green"));
    public static final int GREEN_DIMENSION_FACTOR = 8;

    public static final InternalMethods INTERNAL_METHODS;

    static {
        try {
            INTERNAL_METHODS = (InternalMethods) Class.forName("studio.abos.mc.strangeadventures.InternalMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
