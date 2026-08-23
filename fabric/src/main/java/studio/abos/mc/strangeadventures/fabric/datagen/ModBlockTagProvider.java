package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
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
        builder(BlockTags.LOGS).add(ModBlocks.LIVING_WOOD.asResourceKey());
        builder(BlockTags.SUPPORTS_CROPS).add(ModBlocks.GREEN_FARMLAND.asResourceKey());
        builder(BlockTags.SUPPORTS_CACTUS).add(ModBlocks.GREEN_CACTUS.asResourceKey());
        builder(BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER).add(ModBlocks.GREEN_CACTUS.asResourceKey());
        builder(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.GREEN_FARMLAND.asResourceKey());
        builder(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.LIVING_WOOD.asResourceKey());
        builder(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.SAP_SIPPER.asResourceKey());
        builder(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.LIVING_WOOD.asResourceKey());

        builder(ModBlockTags.GREEN_FARMLAND_CONVERTIBLE).add(Blocks.FARMLAND.properties().blockId());

        builder(ModBlockTags.MAKES_ACACIA_SAP).add(
                BlockItemIds.ACACIA_LOG, BlockItemIds.STRIPPED_ACACIA_LOG,
                BlockItemIds.ACACIA_WOOD, BlockItemIds.STRIPPED_ACACIA_WOOD);
        builder(ModBlockTags.MAKES_BIRCH_SAP).add(
                BlockItemIds.BIRCH_LOG, BlockItemIds.STRIPPED_BIRCH_LOG,
                BlockItemIds.BIRCH_WOOD, BlockItemIds.STRIPPED_BIRCH_WOOD);
        builder(ModBlockTags.MAKES_CACTUS_SAP).add(
                BlockItemIds.CACTUS, ModBlocks.GREEN_CACTUS.asBlockItemId());
        builder(ModBlockTags.MAKES_CHERRY_SAP).add(
                BlockItemIds.CHERRY_LOG, BlockItemIds.STRIPPED_CHERRY_LOG,
                BlockItemIds.CHERRY_WOOD, BlockItemIds.STRIPPED_CHERRY_WOOD);
        builder(ModBlockTags.MAKES_CHORUS_SAP).add(
                BlockItemIds.CHORUS_FLOWER, BlockItemIds.CHORUS_PLANT);
        builder(ModBlockTags.MAKES_CRIMSON_SAP).add(
                BlockItemIds.CRIMSON_STEM, BlockItemIds.STRIPPED_CRIMSON_STEM,
                BlockItemIds.CRIMSON_HYPHAE, BlockItemIds.STRIPPED_CRIMSON_HYPHAE);
        builder(ModBlockTags.MAKES_JUNGLE_SAP).add(
                BlockItemIds.JUNGLE_LOG, BlockItemIds.STRIPPED_JUNGLE_LOG,
                BlockItemIds.JUNGLE_WOOD, BlockItemIds.STRIPPED_JUNGLE_WOOD);
        builder(ModBlockTags.MAKES_LIVING_SAP).add(ModBlocks.LIVING_WOOD.asResourceKey());
        builder(ModBlockTags.MAKES_MANGROVE_SAP).add(
                BlockItemIds.MANGROVE_LOG, BlockItemIds.STRIPPED_MANGROVE_LOG,
                BlockItemIds.MANGROVE_WOOD, BlockItemIds.STRIPPED_MANGROVE_WOOD);
        builder(ModBlockTags.MAKES_OAK_SAP).add(
                BlockItemIds.OAK_LOG, BlockItemIds.STRIPPED_OAK_LOG,
                BlockItemIds.OAK_WOOD, BlockItemIds.STRIPPED_OAK_WOOD,
                BlockItemIds.DARK_OAK_LOG, BlockItemIds.STRIPPED_DARK_OAK_LOG,
                BlockItemIds.DARK_OAK_WOOD, BlockItemIds.STRIPPED_DARK_OAK_WOOD,
                BlockItemIds.PALE_OAK_LOG, BlockItemIds.STRIPPED_PALE_OAK_LOG,
                BlockItemIds.PALE_OAK_WOOD, BlockItemIds.STRIPPED_PALE_OAK_WOOD);
        builder(ModBlockTags.MAKES_SPRUCE_SAP).add(
                BlockItemIds.SPRUCE_LOG, BlockItemIds.STRIPPED_SPRUCE_LOG,
                BlockItemIds.SPRUCE_WOOD, BlockItemIds.STRIPPED_SPRUCE_WOOD);
        builder(ModBlockTags.MAKES_WARPED_SAP).add(
                BlockItemIds.WARPED_STEM, BlockItemIds.STRIPPED_WARPED_STEM,
                BlockItemIds.WARPED_HYPHAE, BlockItemIds.STRIPPED_WARPED_HYPHAE);
    }
}
