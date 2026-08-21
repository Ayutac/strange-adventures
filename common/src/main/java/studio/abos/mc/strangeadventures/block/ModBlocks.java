package studio.abos.mc.strangeadventures.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

    public static DeferredBlock GREEN_FLOWER;
    public static DeferredBlock GREEN_FARMBLOCK;

    public static void initialize(BalmBlockRegistrar blocks) {
        GREEN_FLOWER = blocks.register("green_flower", GreenFlowerBlock::new, props -> props
                .instabreak()
                .noCollision()
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)).withDefaultItem().asDeferredBlock();
        GREEN_FARMBLOCK = blocks.register("green_farmblock", GreenFarmlandBlock::new, props -> props
                .strength(0.6F)
                .randomTicks()
                .sound(SoundType.GRAVEL)
                .isViewBlocking((_, _, _) -> true)
                .isSuffocating((_, _, _) -> true)
                .mapColor(Blocks.FARMLAND.defaultMapColor())).asDeferredBlock();
    }

}
