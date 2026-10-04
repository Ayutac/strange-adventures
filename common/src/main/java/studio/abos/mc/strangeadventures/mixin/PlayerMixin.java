package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.player.LocalPlayer;
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

    @Definition(id = "canPlayerFitWithinBlocksAndEntitiesWhen", method = "Lnet/minecraft/world/entity/player/Player;canPlayerFitWithinBlocksAndEntitiesWhen(Lnet/minecraft/world/entity/Pose;)Z")
    @Definition(id = "SWIMMING", field = "Lnet/minecraft/world/entity/Pose;SWIMMING:Lnet/minecraft/world/entity/Pose;")
    @Expression("this.canPlayerFitWithinBlocksAndEntitiesWhen(SWIMMING)")
    @ModifyExpressionValue(method = "updatePlayerPose()V", at = @At("MIXINEXTRAS:EXPRESSION"))
    protected boolean strangeadventures$greenAvatarCanCrouchWhenRooted(final boolean original) {
        if (!original && (Object)this instanceof final LocalPlayer player &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            return true;
        }
        return original;
    }

    @Definition(id = "canPlayerFitWithinBlocksAndEntitiesWhen", method = "Lnet/minecraft/world/entity/player/Player;canPlayerFitWithinBlocksAndEntitiesWhen(Lnet/minecraft/world/entity/Pose;)Z")
    @Definition(id = "CROUCHING", field = "Lnet/minecraft/world/entity/Pose;CROUCHING:Lnet/minecraft/world/entity/Pose;")
    @Expression("this.canPlayerFitWithinBlocksAndEntitiesWhen(CROUCHING)")
    @ModifyExpressionValue(method = "updatePlayerPose()V", at = @At("MIXINEXTRAS:EXPRESSION"))
    protected boolean strangeadventures$greenAvatarCanCrouchWhenRooted2(final boolean original) {
        if (!original && (Object)this instanceof final LocalPlayer player &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            return true;
        }
        return original;
    }

}
