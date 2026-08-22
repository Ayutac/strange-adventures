package studio.abos.mc.strangeadventures.fluid;

import lombok.NonNull;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModFluidIds {

    public static final ResourceKey<@NonNull Fluid> BIRCH_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("birch_sap"));
    public static final ResourceKey<@NonNull Fluid> BIRCH_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_birch_sap"));

}
