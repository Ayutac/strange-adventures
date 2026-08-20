package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(ModBlocks.GREEN_FLOWER.asBlock(), BlockModelGenerators.PlantType.NOT_TINTED);
        //blockStateModelGenerator.registerSimpleItemModel(ModBlocks.GREEN_FLOWER.asBlock(), ModelLocationUtils.getModelLocation(ModBlocks.GREEN_FLOWER.asItem()));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.GREEN_FLOWER.asItem(), ModelTemplates.FLAT_ITEM);
    }

}
