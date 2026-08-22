package studio.abos.mc.strangeadventures.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.mixin.LiquidBlockAccessor;

public final class ModBlocks {

    public static DeferredBlock GREEN_FLOWER;
    public static DeferredBlock POTTED_GREEN_FLOWER;
    public static DeferredBlock GREEN_FARMBLOCK;
    public static DeferredBlock BIRCH_SAP;
    public static DeferredBlock OAK_SAP;

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
        GREEN_FARMBLOCK = blocks.register("green_farmblock", GreenFarmlandBlock::new, props -> props
                .strength(0.6F)
                .randomTicks()
                .sound(SoundType.GRAVEL)
                .isViewBlocking((_, _, _) -> true)
                .isSuffocating((_, _, _) -> true)
                .mapColor(Blocks.FARMLAND.defaultMapColor())).asDeferredBlock();
        BIRCH_SAP = blocks.register("birch_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.BIRCH_SAP_STILL.value(), props), props ->
                sap(props)
                .mapColor(MapColor.COLOR_ORANGE)).asDeferredBlock();
        OAK_SAP = blocks.register("oak_sap", props -> LiquidBlockAccessor.newLiquidBlock((FlowingFluid)ModFluids.OAK_SAP_STILL.value(), props), props ->
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
