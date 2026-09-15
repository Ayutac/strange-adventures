package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(Entity.class)
public class EntityMixin {

    @Shadow
    private Vec3 position;

    @Inject(method = "getEyeY()D", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$greenAvatarEyeY(final CallbackInfoReturnable<Double> cir) {
        if ((Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            cir.setReturnValue((cir.getReturnValueD() - position.y) * StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarGetMass(player) + position.y);
        }
    }

    @Inject(method = "getEyeHeight()F", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$greenAvatarEyeHeight(final CallbackInfoReturnable<Float> cir) {
        if ((Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            cir.setReturnValue(cir.getReturnValueF() * StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarGetMass(player));
        }
    }

    @Inject(method = "getEyeHeight(Lnet/minecraft/world/entity/Pose;)F", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$greenAvatarEyeHeightPose(final Pose pose, final CallbackInfoReturnable<Float> cir) {
        if ((Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            if (pose == Pose.STANDING || pose == Pose.CROUCHING) {
                cir.setReturnValue(cir.getReturnValueF() * StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarGetMass(player));
            }
        }
    }

    @Inject(method = "makeBoundingBox(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/AABB;", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$greenAvatarBoundingBox(final Vec3 position, final CallbackInfoReturnable<AABB> cir) {
        if ((Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            final float mass = StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarGetMass(player);
            final AABB oldDim = cir.getReturnValue();
            cir.setReturnValue(oldDim
                    .inflate(oldDim.getXsize() * (mass - 1f)/2, oldDim.getYsize() * (mass - 1f)/2, oldDim.getZsize() * (mass - 1f)/2)
                    .move(0d, oldDim.getYsize() * (mass - 1f)/2, 0d));
        }
    }

}
