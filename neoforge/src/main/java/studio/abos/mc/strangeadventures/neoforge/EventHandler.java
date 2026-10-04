package studio.abos.mc.strangeadventures.neoforge;

import net.minecraft.server.level.ServerPlayer;
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
import studio.abos.mc.strangeadventures.network.ServerboundGreenAvatarAttemptSplitPayload;
import studio.abos.mc.strangeadventures.network.ServerboundGreenAvatarUprootPayload;

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
        registrar.playToServer(ServerboundGreenAvatarUprootPayload.TYPE, ServerboundGreenAvatarUprootPayload.CODEC, EventHandler::handleServerboundGreenAvatarUprootPayload);
        registrar.playToServer(ServerboundGreenAvatarAttemptSplitPayload.TYPE, ServerboundGreenAvatarAttemptSplitPayload.CODEC, EventHandler::handleServerboundGreenAvatarAttemptSplitPayload);
    }

    private static void handleServerboundGreenAvatarUprootPayload(final ServerboundGreenAvatarUprootPayload payload, final IPayloadContext context) {
        final Player player = context.player();
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarUproot(player);
        }
    }

    private static void handleServerboundGreenAvatarAttemptSplitPayload(final ServerboundGreenAvatarAttemptSplitPayload payload, final IPayloadContext context) {
        StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarSplit((ServerPlayer)context.player());
    }

}
