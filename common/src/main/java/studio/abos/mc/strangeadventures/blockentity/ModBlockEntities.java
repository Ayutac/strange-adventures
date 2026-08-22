package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.world.level.block.entity.BalmBlockEntityTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public final class ModBlockEntities {

    public static Holder<BlockEntityType<SapSipperBlockEntity>> SAP_SIPPER;

    public static void initialize(final BalmBlockEntityTypeRegistrar blockEntities) {
        SAP_SIPPER = blockEntities.register("sap_sipper", SapSipperBlockEntity::new, ModBlocks.SAP_SIPPER).asHolder();
    }

}
