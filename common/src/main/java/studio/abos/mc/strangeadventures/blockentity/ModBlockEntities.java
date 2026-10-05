package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.world.level.block.entity.BalmBlockEntityTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public final class ModBlockEntities {

    public static Holder<BlockEntityType<EssenceCauldronBlockEntity>> ESSENCE_CAULDRON;
    public static Holder<BlockEntityType<JarBlockEntity>> JAR;
    public static Holder<BlockEntityType<SapSipperBlockEntity>> SAP_SIPPER;

    public static void initialize(final BalmBlockEntityTypeRegistrar blockEntities) {
        ESSENCE_CAULDRON = blockEntities.register("essence_cauldron", EssenceCauldronBlockEntity::new, ModBlocks.ESSENCE_CAULDRON).asHolder();
        JAR = blockEntities.register("jar", JarBlockEntity::new, ModBlocks.JAR).asHolder();
        SAP_SIPPER = blockEntities.register("sap_sipper", SapSipperBlockEntity::new, ModBlocks.SAP_SIPPER).asHolder();
    }

}
