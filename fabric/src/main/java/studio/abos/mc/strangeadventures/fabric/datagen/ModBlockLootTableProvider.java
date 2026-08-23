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
        dropSelf(ModBlocks.SAP_SIPPER.asBlock());
    }
}
