package studio.abos.mc.strangeadventures.fluid;

import lombok.NonNull;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModFluidIds {

    public static final ResourceKey<@NonNull Fluid> ACACIA_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("acacia_sap"));
    public static final ResourceKey<@NonNull Fluid> ACACIA_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_acacia_sap"));
    public static final ResourceKey<@NonNull Fluid> BIRCH_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("birch_sap"));
    public static final ResourceKey<@NonNull Fluid> BIRCH_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_birch_sap"));
    public static final ResourceKey<@NonNull Fluid> CHERRY_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("cherry_sap"));
    public static final ResourceKey<@NonNull Fluid> CHERRY_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_cherry_sap"));
    public static final ResourceKey<@NonNull Fluid> JUNGLE_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("jungle_sap"));
    public static final ResourceKey<@NonNull Fluid> JUNGLE_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_jungle_sap"));
    public static final ResourceKey<@NonNull Fluid> MANGROVE_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("mangrove_sap"));
    public static final ResourceKey<@NonNull Fluid> MANGROVE_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_mangrove_sap"));
    public static final ResourceKey<@NonNull Fluid> OAK_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("oak_sap"));
    public static final ResourceKey<@NonNull Fluid> OAK_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_oak_sap"));
    public static final ResourceKey<@NonNull Fluid> SPRUCE_SAP_STILL = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("spruce_sap"));
    public static final ResourceKey<@NonNull Fluid> SPRUCE_SAP_FLOWING = ResourceKey.create(Registries.FLUID, StrangeAdventures.id("flowing_spruce_sap"));

}
