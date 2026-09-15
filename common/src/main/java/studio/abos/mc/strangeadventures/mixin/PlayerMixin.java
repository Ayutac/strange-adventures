package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.tag.ModDamageTypeTags;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "isInvulnerableTo(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)Z", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$dontHurtGreenAvatar(final ServerLevel level, final DamageSource source, final CallbackInfoReturnable<Boolean> cir) {
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive((Player)(Object)this) && source.is(ModDamageTypeTags.GREEN_AVATAR_IMMUNITIES) && !cir.getReturnValueZ()) {
            cir.setReturnValue(true);
        }
    }

}
