package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import studio.abos.mc.strangeadventures.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    protected ModBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataOutput, provider);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.GREEN_FLOWER.asBlock());
        dropPottedContents(ModBlocks.POTTED_GREEN_FLOWER.asBlock());
        dropOther(ModBlocks.GREEN_FARMLAND.asBlock(), Items.DIRT);
        dropOther(ModBlocks.GREEN_CACTUS.asBlock(), Items.CACTUS);
        dropSelf(ModBlocks.WEIR_LOG.asBlock());
        dropSelf(ModBlocks.STRIPPED_WEIR_LOG.asBlock());
        dropSelf(ModBlocks.WEIR_WOOD.asBlock());
        dropSelf(ModBlocks.STRIPPED_WEIR_WOOD.asBlock());
        dropSelf(ModBlocks.WEIR_CORE.asBlock());
        dropSelf(ModBlocks.WEIR_PLANKS.asBlock());
        dropSelf(ModBlocks.WEIR_SIGN.asBlock());
        dropSelf(ModBlocks.WEIR_HANGING_SIGN.asBlock());
        dropSelf(ModBlocks.WEIR_PRESSURE_PLATE.asBlock());
        dropSelf(ModBlocks.WEIR_TRAPDOOR.asBlock());
        dropSelf(ModBlocks.WEIR_BUTTON.asBlock());
        dropSelf(ModBlocks.WEIR_STAIRS.asBlock());
        dropSelf(ModBlocks.WEIR_FENCE_GATE.asBlock());
        dropSelf(ModBlocks.WEIR_FENCE.asBlock());
        add(ModBlocks.WEIR_SLAB.asBlock(), this::createSlabItemTable);
        add(ModBlocks.WEIR_DOOR.asBlock(), this::createDoorTable);
        dropSelf(ModBlocks.WEIR_SHELF.asBlock());
        add(ModBlocks.WEIR_LEAVES.asBlock(), block -> createLeavesDrops(block, ModBlocks.WEIR_SAPLING.asBlock(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.WEIR_SAPLING.asBlock());
        dropPottedContents(ModBlocks.POTTED_WEIR_SAPLING.asBlock());
        dropSelf(ModBlocks.SLEEPING_WOOD.asBlock());
        dropSelf(ModBlocks.LIVING_WOOD.asBlock());
        dropSelf(ModBlocks.SAP_SIPPER.asBlock());
        dropSelf(ModBlocks.ESSENCE_CAULDRON.asBlock());
    }
}
