package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.tag.ModDamageTypeTags;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @ModifyReturnValue(method = "isInvulnerableTo(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)Z", at = @At("RETURN"))
    public boolean strangeadventures$greenAvatarImmunities(final boolean original, final ServerLevel level, final DamageSource source) {
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive((Player)(Object)this) && source.is(ModDamageTypeTags.GREEN_AVATAR_IMMUNITIES) && !original) {
            return true;
        }
        return original;
    }

    @ModifyReturnValue(method = "hasEnoughFoodToDoExhaustiveManoeuvres()Z", at = @At("RETURN"))
    public boolean strangeadventures$greenAvatarIsAlwaysReady(final boolean original) {
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive((Player)(Object)this) && !original) {
            return true;
        }
        return original;
    }

    @WrapWithCondition(method = "causeFoodExhaustion(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;addExhaustion(F)V"))
    private boolean strangeadventures$greenAvatarDoesntGetFoodExhaustion(final FoodData instance, final float amount) {
        return !StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive((Player)(Object)this);
    }

    /*@Inject(method = "travel(Lnet/minecraft/world/phys/Vec3;)V", at = @At("RETURN"))
    private void strangeadventures$greenAvatarTravellingUproots(final Vec3 input, final CallbackInfo ci) {
        if (input != Vec3.ZERO && (Player)(Object)this instanceof final ServerPlayer player &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarUproot(player);
        }
    }*/

}
