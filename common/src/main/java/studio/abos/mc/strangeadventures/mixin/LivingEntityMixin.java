package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.blay09.mods.balm.Balm;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.network.ServerboundGreenAvatarUprootPayload;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(method = "canBreatheUnderwater()Z", at = @At("RETURN"))
    public boolean strangeadventures$greenAvatarCanBreatheUnderwater(final boolean original) {
        if ((LivingEntity)(Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            return true;
        }
        return original;
    }

    @Inject(method = "travel(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"))
    public void strangeadventures$greenAvatarUprootUponMovement(final Vec3 input, final CallbackInfo ci) {
        if ((LivingEntity)(Object)this instanceof final LocalPlayer player && (!input.equals(Vec3.ZERO) || player.isJumping()) &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            Balm.networking().sendToServer(ServerboundGreenAvatarUprootPayload.INSTANCE);
        }
    }

}
