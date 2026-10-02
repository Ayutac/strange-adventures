package studio.abos.mc.strangeadventures.neoforge;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.network.ServerboundUprootPayload;

@EventBusSubscriber(modid = StrangeAdventures.MOD_ID)
public final class EventHandler {

    @SubscribeEvent
    public static void registerAdditionalBlockEntities(final BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.SIGN, ModBlocks.WEIR_SIGN.asBlock(), ModBlocks.WEIR_WALL_SIGN.asBlock());
        event.modify(BlockEntityTypes.HANGING_SIGN, ModBlocks.WEIR_HANGING_SIGN.asBlock(), ModBlocks.WEIR_WALL_HANGING_SIGN.asBlock());
    }

    @SubscribeEvent
    public static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(ServerboundUprootPayload.TYPE, ServerboundUprootPayload.CODEC, EventHandler::handleServerboundUprootPayload);
    }

    private static void handleServerboundUprootPayload(final ServerboundUprootPayload payload, final IPayloadContext context) {
        final Player player = context.player();
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarUproot(player);
        }
    }

}
