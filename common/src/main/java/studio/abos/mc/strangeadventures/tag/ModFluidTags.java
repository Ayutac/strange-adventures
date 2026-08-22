package studio.abos.mc.strangeadventures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModFluidTags {

    public static final TagKey<Fluid> ACACIA_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("acacia_sap"));
    public static final TagKey<Fluid> BIRCH_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("birch_sap"));
    public static final TagKey<Fluid> CACTUS_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("cactus_sap"));
    public static final TagKey<Fluid> CHERRY_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("cherry_sap"));
    public static final TagKey<Fluid> CHORUS_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("chorus_sap"));
    public static final TagKey<Fluid> CRIMSON_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("crimson_sap"));
    public static final TagKey<Fluid> JUNGLE_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("jungle_sap"));
    public static final TagKey<Fluid> MANGROVE_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("mangrove_sap"));
    public static final TagKey<Fluid> OAK_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("oak_sap"));
    public static final TagKey<Fluid> SPRUCE_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("spruce_sap"));
    public static final TagKey<Fluid> WARPED_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("warped_sap"));
    public static final TagKey<Fluid> SAP = TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("c", "sap"));

}
