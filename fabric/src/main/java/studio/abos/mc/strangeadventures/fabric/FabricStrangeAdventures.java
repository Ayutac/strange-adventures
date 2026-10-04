package studio.abos.mc.strangeadventures.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.block.ModBlockFamilies;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.network.ServerboundGreenAvatarAttemptSplitPayload;
import studio.abos.mc.strangeadventures.network.ServerboundGreenAvatarUprootPayload;

public final class FabricStrangeAdventures implements ModInitializer {

    @Override
    public void onInitialize() {
        Balm.initializeMod(StrangeAdventures.MOD_ID, FabricLoadContext.INSTANCE, StrangeAdventures::initialize);
        ModBlockFamilies.initialize();
        BlockEntityTypes.SIGN.addValidBlock(ModBlocks.WEIR_SIGN.asBlock());
        BlockEntityTypes.SIGN.addValidBlock(ModBlocks.WEIR_WALL_SIGN.asBlock());
        BlockEntityTypes.HANGING_SIGN.addValidBlock(ModBlocks.WEIR_HANGING_SIGN.asBlock());
        BlockEntityTypes.HANGING_SIGN.addValidBlock(ModBlocks.WEIR_WALL_HANGING_SIGN.asBlock());

        PayloadTypeRegistry.serverboundPlay().register(ServerboundGreenAvatarUprootPayload.TYPE, ServerboundGreenAvatarUprootPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ServerboundGreenAvatarUprootPayload.TYPE, (_, context) -> {
            final ServerPlayer player = context.player();
            if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarUproot(player);
            }
        });
        PayloadTypeRegistry.serverboundPlay().register(ServerboundGreenAvatarAttemptSplitPayload.TYPE, ServerboundGreenAvatarAttemptSplitPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ServerboundGreenAvatarAttemptSplitPayload.TYPE, (_, context) ->
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarSplit(context.player())
        );
    }

}
