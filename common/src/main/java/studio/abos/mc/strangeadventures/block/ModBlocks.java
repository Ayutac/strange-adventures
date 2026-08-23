package studio.abos.mc.strangeadventures.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.mixin.LiquidBlockAccessor;

public final class ModBlocks {

    public static DeferredBlock GREEN_FLOWER;
    public static DeferredBlock POTTED_GREEN_FLOWER;
    public static DeferredBlock GREEN_FARMLAND;
    public static DeferredBlock GREEN_CACTUS;

    public static DeferredBlock LIVING_WOOD;

    public static DeferredBlock SAP_SIPPER;

    public static DeferredBlock ACACIA_SAP;
    public static DeferredBlock BIRCH_SAP;
    public static DeferredBlock CACTUS_SAP;
    public static DeferredBlock CHERRY_SAP;
    public static DeferredBlock CHORUS_SAP;
    public static DeferredBlock CRIMSON_SAP;
    public static DeferredBlock JUNGLE_SAP;
    public static DeferredBlock LIVING_SAP;
    public static DeferredBlock MANGROVE_SAP;
    public static DeferredBlock OAK_SAP;
    public static DeferredBlock SPRUCE_SAP;
    public static DeferredBlock WARPED_SAP;

    public static void initialize(final BalmBlockRegistrar blocks) {
        GREEN_FLOWER = blocks.register("green_flower", GreenFlowerBlock::new, props -> props
                .instabreak()
                .noCollision()
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)).withDefaultItem().asDeferredBlock();
        POTTED_GREEN_FLOWER = blocks.register("potted_green_flower", props -> new FlowerPotBlock(GREEN_FLOWER.asBlock(), props), props -> props
                .instabreak()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)).asDeferredBlock();
        GREEN_FARMLAND = blocks.register("green_farmland", GreenFarmlandBlock::new, props -> props
                .strength(0.6F)
                .randomTicks()
                .sound(SoundType.GRAVEL)
                .isViewBlocking((_, _, _) -> true)
                .isSuffocating((_, _, _) -> true)
                .mapColor(Blocks.FARMLAND.defaultMapColor())).withDefaultItem().asDeferredBlock();
        GREEN_CACTUS = blocks.register("green_cactus", GreenCactusBlock::new, props -> props
                .strength(0.4F)
                .randomTicks()
                .sound(SoundType.WOOL)
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)).withDefaultItem().asDeferredBlock();

        LIVING_WOOD = blocks.register("living_wood", RotatedPillarBlock::new, props -> props
                .strength(30f, 1000f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.ANCIENT_DEBRIS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem(Item.Properties::fireResistant).asDeferredBlock();

        SAP_SIPPER = blocks.register("sap_sipper", SapSipperBlock::new, props -> props
                .strength(3.5f)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .mapColor(MapColor.STONE)).withDefaultItem().asDeferredBlock();

        ACACIA_SAP = blocks.register("acacia_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.ACACIA_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        BIRCH_SAP = blocks.register("birch_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.BIRCH_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        CACTUS_SAP = blocks.register("cactus_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.CACTUS_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        CHERRY_SAP = blocks.register("cherry_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.CHERRY_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        CHORUS_SAP = blocks.register("chorus_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.CHORUS_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        CRIMSON_SAP = blocks.register("crimson_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.CRIMSON_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        JUNGLE_SAP = blocks.register("jungle_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.JUNGLE_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        LIVING_SAP = blocks.register("living_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.LIVING_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        MANGROVE_SAP = blocks.register("mangrove_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.MANGROVE_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        OAK_SAP = blocks.register("oak_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.OAK_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        SPRUCE_SAP = blocks.register("spruce_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.SPRUCE_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        WARPED_SAP = blocks.register("warped_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.WARPED_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
    }

    public static BlockBehaviour.Properties sap(BlockBehaviour.Properties properties) {
        return properties
                .replaceable()
                .noCollision()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY);
    }

}
