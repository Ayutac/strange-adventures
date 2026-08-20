package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import studio.abos.mc.strangeadventures.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        builder(BlockTags.SMALL_FLOWERS).add(ModBlocks.GREEN_FLOWER.asResourceKey());
        //builder(ModBlockTags.YOUR_TAG).add(ModBlocks.GREEN_FLOWER.asResourceKey());
    }
}
