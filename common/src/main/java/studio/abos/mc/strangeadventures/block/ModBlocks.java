package studio.abos.mc.strangeadventures.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.world.item.DyeColor;

public class ModBlocks {

    public static DeferredBlock GREEN_FLOWER;

    public static void initialize(BalmBlockRegistrar blocks) {
        GREEN_FLOWER = blocks.register("green_flower", GreenFlowerBlock::new, props -> props
                .instabreak()
                .noCollision()
                .mapColor(DyeColor.LIME)).asDeferredBlock();
    }

}
