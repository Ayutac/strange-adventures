package studio.abos.mc.strangeadventures.neoforge;

import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;

@EventBusSubscriber(modid = StrangeAdventures.MOD_ID)
public final class EventHandler {

    @SubscribeEvent
    public static void registerAdditionalBlockEntities(final BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.SIGN, ModBlocks.WEIR_SIGN.asBlock(), ModBlocks.WEIR_WALL_SIGN.asBlock());
        event.modify(BlockEntityTypes.HANGING_SIGN, ModBlocks.WEIR_HANGING_SIGN.asBlock(), ModBlocks.WEIR_WALL_HANGING_SIGN.asBlock());
    }

}
