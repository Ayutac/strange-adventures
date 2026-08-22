package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import studio.abos.mc.strangeadventures.fluid.ModFluidIds;
import studio.abos.mc.strangeadventures.tag.ModFluidTags;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagsProvider.FluidTagsProvider {
    public ModFluidTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        builder(ModFluidTags.ACACIA_SAP).add(ModFluidIds.ACACIA_SAP_STILL);
        builder(ModFluidTags.ACACIA_SAP).add(ModFluidIds.ACACIA_SAP_FLOWING);
        builder(ModFluidTags.BIRCH_SAP).add(ModFluidIds.BIRCH_SAP_STILL);
        builder(ModFluidTags.BIRCH_SAP).add(ModFluidIds.BIRCH_SAP_FLOWING);
        builder(ModFluidTags.CHERRY_SAP).add(ModFluidIds.CHERRY_SAP_STILL);
        builder(ModFluidTags.CHERRY_SAP).add(ModFluidIds.CHERRY_SAP_FLOWING);
        builder(ModFluidTags.JUNGLE_SAP).add(ModFluidIds.JUNGLE_SAP_STILL);
        builder(ModFluidTags.JUNGLE_SAP).add(ModFluidIds.JUNGLE_SAP_FLOWING);
        builder(ModFluidTags.MANGROVE_SAP).add(ModFluidIds.MANGROVE_SAP_STILL);
        builder(ModFluidTags.MANGROVE_SAP).add(ModFluidIds.MANGROVE_SAP_FLOWING);
        builder(ModFluidTags.OAK_SAP).add(ModFluidIds.OAK_SAP_STILL);
        builder(ModFluidTags.OAK_SAP).add(ModFluidIds.OAK_SAP_FLOWING);
        builder(ModFluidTags.SPRUCE_SAP).add(ModFluidIds.SPRUCE_SAP_STILL);
        builder(ModFluidTags.SPRUCE_SAP).add(ModFluidIds.SPRUCE_SAP_FLOWING);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.ACACIA_SAP);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.BIRCH_SAP);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.CHERRY_SAP);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.JUNGLE_SAP);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.MANGROVE_SAP);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.OAK_SAP);
        builder(ModFluidTags.SAP).addTag(ModFluidTags.SPRUCE_SAP);
    }
}
