package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Inject(method = "tick()V", at = @At("RETURN"))
    public void strangeadventures$tick(final CallbackInfo ci) {
        final ServerPlayer player = (ServerPlayer)(Object)this;
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarTick(player);
        }
    }

}
