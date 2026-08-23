package studio.abos.mc.strangeadventures.item;

import net.minecraft.world.item.BucketItem;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

public class LivingSapBucketItem extends BucketItem {

    public LivingSapBucketItem(final Properties properties) {
        super(ModFluids.LIVING_SAP_STILL.value(), properties);
    }

}
