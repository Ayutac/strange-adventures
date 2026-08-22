package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.ItemTags;
import studio.abos.mc.strangeadventures.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        builder(ModItemTags.MAKES_ACACIA_SAP).forceAddTag(ItemTags.ACACIA_LOGS);
        builder(ModItemTags.MAKES_BIRCH_SAP).forceAddTag(ItemTags.BIRCH_LOGS);
        builder(ModItemTags.MAKES_CACTUS_SAP).add(BlockItemIds.CACTUS);
        builder(ModItemTags.MAKES_CHERRY_SAP).forceAddTag(ItemTags.CHERRY_LOGS);
        builder(ModItemTags.MAKES_CHORUS_SAP).add(BlockItemIds.CHORUS_FLOWER);
        builder(ModItemTags.MAKES_CHORUS_SAP).add(BlockItemIds.CHORUS_PLANT);
        builder(ModItemTags.MAKES_CRIMSON_SAP).forceAddTag(ItemTags.CRIMSON_STEMS);
        builder(ModItemTags.MAKES_JUNGLE_SAP).forceAddTag(ItemTags.JUNGLE_LOGS);
        builder(ModItemTags.MAKES_MANGROVE_SAP).forceAddTag(ItemTags.MANGROVE_LOGS);
        builder(ModItemTags.MAKES_OAK_SAP).forceAddTag(ItemTags.OAK_LOGS);
        builder(ModItemTags.MAKES_OAK_SAP).forceAddTag(ItemTags.DARK_OAK_LOGS);
        builder(ModItemTags.MAKES_OAK_SAP).forceAddTag(ItemTags.PALE_OAK_LOGS);
        builder(ModItemTags.MAKES_SPRUCE_SAP).forceAddTag(ItemTags.SPRUCE_LOGS);
        builder(ModItemTags.MAKES_WARPED_SAP).forceAddTag(ItemTags.WARPED_STEMS);
        // builder(ModItemTags.YOUR_TAG).add(ModItems.GREEN_FLOWER.asResourceKey());
    }
}
