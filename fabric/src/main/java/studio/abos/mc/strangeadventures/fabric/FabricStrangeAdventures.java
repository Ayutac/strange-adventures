package studio.abos.mc.strangeadventures.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlockFamilies;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public final class FabricStrangeAdventures implements ModInitializer {

    @Override
    public void onInitialize() {
        Balm.initializeMod(StrangeAdventures.MOD_ID, FabricLoadContext.INSTANCE, StrangeAdventures::initialize);
        ModBlockFamilies.initialize();
        BlockEntityTypes.SIGN.addValidBlock(ModBlocks.WEIR_SIGN.asBlock());
        BlockEntityTypes.SIGN.addValidBlock(ModBlocks.WEIR_WALL_SIGN.asBlock());
        BlockEntityTypes.HANGING_SIGN.addValidBlock(ModBlocks.WEIR_HANGING_SIGN.asBlock());
        BlockEntityTypes.HANGING_SIGN.addValidBlock(ModBlocks.WEIR_WALL_HANGING_SIGN.asBlock());
    }

}
