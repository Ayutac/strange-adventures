package studio.abos.mc.strangeadventures.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.mixin.ButtonBlockAccessor;
import studio.abos.mc.strangeadventures.mixin.DoorBlockAccessor;
import studio.abos.mc.strangeadventures.mixin.LiquidBlockAccessor;
import studio.abos.mc.strangeadventures.mixin.PressurePlateBlockAccessor;
import studio.abos.mc.strangeadventures.mixin.SaplingBlockAccessor;
import studio.abos.mc.strangeadventures.mixin.StairBlockAccessor;
import studio.abos.mc.strangeadventures.mixin.TrapdoorBlockAccessor;
import studio.abos.mc.strangeadventures.worldgen.ModTreeFeatures;

public final class ModBlocks {

    public static DeferredBlock GREEN_FLOWER;
    public static DeferredBlock POTTED_GREEN_FLOWER;
    public static DeferredBlock GREEN_FARMLAND;
    public static DeferredBlock GREEN_CACTUS;

    public static BlockSetType WEIR_BLOCK_SET_TYPE = new BlockSetType("strangeadventures:weir");
    public static WoodType WEIR_WOOD_TYPE = new WoodType("strangeadventures:weir", WEIR_BLOCK_SET_TYPE);

    public static DeferredBlock WEIR_LOG;
    public static DeferredBlock STRIPPED_WEIR_LOG;
    public static DeferredBlock WEIR_WOOD;
    public static DeferredBlock STRIPPED_WEIR_WOOD;
    public static DeferredBlock WEIR_CORE;
    public static DeferredBlock WEIR_LEAVES;
    public static DeferredBlock WEIR_SAPLING;
    public static DeferredBlock POTTED_WEIR_SAPLING;
    public static DeferredBlock WEIR_PLANKS;
    public static DeferredBlock WEIR_SHELF;
    public static DeferredBlock WEIR_SIGN;
    public static DeferredBlock WEIR_WALL_SIGN;
    public static DeferredBlock WEIR_HANGING_SIGN;
    public static DeferredBlock WEIR_WALL_HANGING_SIGN;
    public static DeferredBlock WEIR_PRESSURE_PLATE;
    public static DeferredBlock WEIR_TRAPDOOR;
    public static DeferredBlock WEIR_BUTTON;
    public static DeferredBlock WEIR_STAIRS;
    public static DeferredBlock WEIR_SLAB;
    public static DeferredBlock WEIR_FENCE_GATE;
    public static DeferredBlock WEIR_FENCE;
    public static DeferredBlock WEIR_DOOR;

    public static DeferredBlock SLEEPING_WOOD;
    public static DeferredBlock LIVING_WOOD;

    public static DeferredBlock SAP_SIPPER;
    public static DeferredBlock ESSENCE_CAULDRON;

    public static DeferredBlock ACACIA_SAP;
    public static DeferredBlock BIRCH_SAP;
    public static DeferredBlock CACTUS_SAP;
    public static DeferredBlock CHERRY_SAP;
    public static DeferredBlock CHORUS_SAP;
    public static DeferredBlock CRIMSON_SAP;
    public static DeferredBlock CRUDE_LIVING_SAP;
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

        WEIR_LOG = blocks.register("weir_log", RotatedPillarBlock::new, props -> props
                .strength(2f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        STRIPPED_WEIR_LOG = blocks.register("stripped_weir_log", RotatedPillarBlock::new, props -> props
                .strength(2f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_WOOD = blocks.register("weir_wood", RotatedPillarBlock::new, props -> props
                .strength(2f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        STRIPPED_WEIR_WOOD = blocks.register("stripped_weir_wood", RotatedPillarBlock::new, props -> props
                .strength(2f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_LEAVES = blocks.register("weir_leaves", props -> new TintedParticleLeavesBlock(0.01f, props), props -> props
                .strength(0.2f)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
//                .isValidSpawn(Blocks::ocelotOrParrot)
                .isViewBlocking((_, _, _) -> false)
                .isSuffocating((_, _, _) -> false)
                .isRedstoneConductor((_, _, _) -> false)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)).withDefaultItem().asDeferredBlock();
        WEIR_SAPLING = blocks.register("weir_sapling", props -> SaplingBlockAccessor.newSaplingBlock(ModTreeFeatures.WEIR_GROWER, props), props -> props
                .instabreak()
                .randomTicks()
                .noCollision()
                .sound(SoundType.GRASS)
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)).withDefaultItem().asDeferredBlock();
        POTTED_WEIR_SAPLING = blocks.register("potted_weir_sapling", props -> new FlowerPotBlock(WEIR_SAPLING.asBlock(), props), props -> props
                .instabreak()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)).asDeferredBlock();
        WEIR_CORE = blocks.register("weir_core", Block::new, props -> props
                .strength(10f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.CREAKING_HEART)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_PLANKS = blocks.register("weir_planks", Block::new, props -> props
                .strength(2f, 3f)
                .ignitedByLava()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_SHELF = blocks.register("weir_shelf", ShelfBlock::new, props -> props
                .strength(2f, 3f)
                .ignitedByLava()
                .sound(SoundType.SHELF)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_SIGN = blocks.register("weir_sign", props -> new StandingSignBlock(WEIR_WOOD_TYPE, props), props -> props
                .strength(1f)
                .forceSolidOn()
                .noCollision()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_WALL_SIGN = blocks.register("weir_wall_sign", props -> new WallSignBlock(WEIR_WOOD_TYPE, props), props -> props
                .overrideLootTable(WEIR_SIGN.asBlock().getLootTable())
                .overrideDescription(WEIR_SIGN.asBlock().getDescriptionId())
                .strength(1f)
                .forceSolidOn()
                .noCollision()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).asDeferredBlock();
        WEIR_HANGING_SIGN = blocks.register("weir_hanging_sign", props -> new CeilingHangingSignBlock(WEIR_WOOD_TYPE, props), props -> props
                .strength(1f)
                .forceSolidOn()
                .noCollision()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_WALL_HANGING_SIGN = blocks.register("weir_wall_hanging_sign", props -> new WallHangingSignBlock(WEIR_WOOD_TYPE, props), props -> props
                .overrideLootTable(WEIR_HANGING_SIGN.asBlock().getLootTable())
                .overrideDescription(WEIR_HANGING_SIGN.asBlock().getDescriptionId())
                .strength(1f)
                .forceSolidOn()
                .noCollision()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).asDeferredBlock();
        WEIR_PRESSURE_PLATE = blocks.register("weir_pressure_plate", props -> PressurePlateBlockAccessor.newPressurePlateBlock(WEIR_BLOCK_SET_TYPE, props), props -> props
                .strength(0.5f)
                .forceSolidOn()
                .noCollision()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_TRAPDOOR = blocks.register("weir_trapdoor", props -> TrapdoorBlockAccessor.newTrapdoorBlock(WEIR_BLOCK_SET_TYPE, props), props -> props
                .strength(3f)
                .isValidSpawn((_, _, _, _) -> false)
                .noCollision()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_BUTTON = blocks.register("weir_button", props -> ButtonBlockAccessor.newButtonBlock(WEIR_BLOCK_SET_TYPE, 30, props), props -> props
                .strength(0.5f)
                .noCollision()
                .pushReaction(PushReaction.DESTROY)).withDefaultItem().asDeferredBlock();
        WEIR_STAIRS = blocks.register("weir_stairs", props -> StairBlockAccessor.newStairBlock(WEIR_PLANKS.defaultBlockState(), props), props -> props
                .strength(2f, 3f)
                .ignitedByLava()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_SLAB = blocks.register("weir_slab", SlabBlock::new, props -> props
                .strength(2f, 3f)
                .ignitedByLava()
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_FENCE_GATE = blocks.register("weir_fence_gate", props -> new FenceGateBlock(WEIR_WOOD_TYPE, props), props -> props
                .strength(2f, 3f)
                .forceSolidOn()
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_FENCE = blocks.register("weir_fence", FenceBlock::new, props -> props
                .strength(2f, 3f)
                .sound(SoundType.WOOD)
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        WEIR_DOOR = blocks.register("weir_door", props -> DoorBlockAccessor.newDoorBlock(WEIR_BLOCK_SET_TYPE, props), props -> props
                .strength(2f, 3f)
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)
                .ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();

        SLEEPING_WOOD = blocks.register("sleeping_wood", RotatedPillarBlock::new, props -> props
                .strength(30f, 1000f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.ANCIENT_DEBRIS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem().asDeferredBlock();
        LIVING_WOOD = blocks.register("living_wood", RotatedPillarBlock::new, props -> props
                .strength(30f, 1000f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.ANCIENT_DEBRIS)
                .mapColor(MapColor.COLOR_BROWN)).withDefaultItem(Item.Properties::fireResistant).asDeferredBlock();

        SAP_SIPPER = blocks.register("sap_sipper", SapSipperBlock::new, props -> props
                .strength(3.5f)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .mapColor(MapColor.STONE)).withDefaultItem().asDeferredBlock();
        ESSENCE_CAULDRON = blocks.register("essence_cauldron", EssenceCauldronBlock::new, props -> props
                .strength(2f)
                .noOcclusion()
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
        CRUDE_LIVING_SAP = blocks.register("crude_living_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.CRUDE_LIVING_SAP_STILL.value(), props), props ->
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
