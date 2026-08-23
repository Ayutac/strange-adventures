package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(final BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createPlant(ModBlocks.GREEN_FLOWER.asBlock(), ModBlocks.POTTED_GREEN_FLOWER.asBlock(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleFlatItemModel(ModBlocks.GREEN_FLOWER.asBlock());
        createGreenFarmland(blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(ModBlocks.GREEN_CACTUS.asBlock());
        blockStateModelGenerator.createHorizontallyRotatedBlock(ModBlocks.SAP_SIPPER.asBlock(), TexturedModel.ORIENTABLE);
    }

    protected void createGreenFarmland(final BlockModelGenerators blockStateModelGenerator) {
        TextureMapping dryTextures = new TextureMapping()
                .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(Blocks.DIRT))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.GREEN_FARMLAND.asBlock()));
        TextureMapping moistTextures = new TextureMapping()
                .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(Blocks.DIRT))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.GREEN_FARMLAND.asBlock(), "_moist"));
        MultiVariant dryModel = BlockModelGenerators.plainVariant(ModelTemplates.FARMLAND.create(ModBlocks.GREEN_FARMLAND.asBlock(), dryTextures, blockStateModelGenerator.modelOutput));
        MultiVariant moistModel = BlockModelGenerators.plainVariant(
                ModelTemplates.FARMLAND.create(ModelLocationUtils.getModelLocation(ModBlocks.GREEN_FARMLAND.asBlock(), "_moist"), moistTextures, blockStateModelGenerator.modelOutput)
        );
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(ModBlocks.GREEN_FARMLAND.asBlock())
                .with(BlockModelGenerators.createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, moistModel, dryModel)));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.GREEN_SAFEGUARD.asItem(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.ACACIA_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ACACIA_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BIRCH_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BIRCH_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CACTUS_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CACTUS_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHERRY_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHERRY_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHORUS_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHORUS_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRIMSON_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRIMSON_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JUNGLE_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JUNGLE_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGROVE_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGROVE_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OAK_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.OAK_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPRUCE_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPRUCE_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WARPED_SAP_BOTTLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WARPED_SAP_BUCKET.asItem(), ModelTemplates.FLAT_ITEM);
    }

}
