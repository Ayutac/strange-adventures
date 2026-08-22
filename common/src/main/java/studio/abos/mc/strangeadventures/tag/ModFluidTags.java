package studio.abos.mc.strangeadventures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModFluidTags {

    public static final TagKey<Fluid> BIRCH_SAP = TagKey.create(Registries.FLUID, StrangeAdventures.id("birch_sap"));
    public static final TagKey<Fluid> SAP = TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("c", "sap"));

}
