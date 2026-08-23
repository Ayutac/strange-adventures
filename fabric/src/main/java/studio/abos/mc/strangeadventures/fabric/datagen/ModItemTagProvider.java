package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockItemTags;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        builder(BlockItemTags.LOGS_THAT_BURN.item()).addTag(ModItemTags.WEIR_LOGS);
        builder(BlockItemTags.PLANKS.item()).add(ModBlocks.WEIR_PLANKS.asBlockItemId());
        builder(BlockItemTags.WOODEN_BUTTONS.item()).add(ModBlocks.WEIR_BUTTON.asBlockItemId());
        builder(BlockItemTags.WOODEN_DOORS.item()).add(ModBlocks.WEIR_DOOR.asBlockItemId());
        builder(BlockItemTags.WOODEN_FENCES.item()).add(ModBlocks.WEIR_FENCE.asBlockItemId());
        builder(BlockItemTags.WOODEN_PRESSURE_PLATES.item()).add(ModBlocks.WEIR_PRESSURE_PLATE.asBlockItemId());
        builder(BlockItemTags.WOODEN_SHELVES.item()).add(ModBlocks.WEIR_SHELF.asBlockItemId());
        builder(BlockItemTags.WOODEN_SLABS.item()).add(ModBlocks.WEIR_SLAB.asBlockItemId());
        builder(BlockItemTags.WOODEN_STAIRS.item()).add(ModBlocks.WEIR_STAIRS.asBlockItemId());
        builder(BlockItemTags.WOODEN_TRAPDOORS.item()).add(ModBlocks.WEIR_TRAPDOOR.asBlockItemId());

        builder(ModItemTags.WEIR_LOGS).add(
                ModBlocks.WEIR_LOG.asBlockItemId(), ModBlocks.STRIPPED_WEIR_LOG.asBlockItemId(),
                ModBlocks.WEIR_WOOD.asBlockItemId(), ModBlocks.STRIPPED_WEIR_LOG.asBlockItemId()
        );
    }

}
