package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.tag.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        builder(BlockTags.SMALL_FLOWERS).add(ModBlocks.GREEN_FLOWER.asResourceKey());
        builder(BlockTags.SUPPORTS_CROPS).add(ModBlocks.GREEN_FARMLAND_BLOCK.asResourceKey());
        builder(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.GREEN_FARMLAND_BLOCK.asResourceKey());
        builder(ModBlockTags.GREEN_FARMLAND_CONVERTIBLE).add(Blocks.FARMLAND.properties().blockId());
    }
}
