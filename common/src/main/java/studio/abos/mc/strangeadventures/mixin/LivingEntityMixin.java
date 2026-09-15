package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;getScale()F", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$scaleGreenAvatar(final CallbackInfoReturnable<Float> cir) {
        if ((Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            cir.setReturnValue(cir.getReturnValueF() * StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarGetMass(player));
        }
    }

}
