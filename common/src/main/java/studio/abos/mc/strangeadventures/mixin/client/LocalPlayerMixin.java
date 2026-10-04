package studio.abos.mc.strangeadventures.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Definition(id = "canPlayerFitWithinBlocksAndEntitiesWhen", method = "Lnet/minecraft/client/player/LocalPlayer;canPlayerFitWithinBlocksAndEntitiesWhen(Lnet/minecraft/world/entity/Pose;)Z")
    @Definition(id = "CROUCHING", field = "Lnet/minecraft/world/entity/Pose;CROUCHING:Lnet/minecraft/world/entity/Pose;")
    @Expression("this.canPlayerFitWithinBlocksAndEntitiesWhen(CROUCHING)")
    @ModifyExpressionValue(method = "aiStep()V", at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean strangeadventures$greenAvatarCanCrouchWhenRooted(final boolean original) {
        if (!original && (Object)this instanceof final LocalPlayer player &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            return true;
        }
        return original;
    }

    @Definition(id = "canPlayerFitWithinBlocksAndEntitiesWhen", method = "Lnet/minecraft/client/player/LocalPlayer;canPlayerFitWithinBlocksAndEntitiesWhen(Lnet/minecraft/world/entity/Pose;)Z")
    @Definition(id = "STANDING", field = "Lnet/minecraft/world/entity/Pose;STANDING:Lnet/minecraft/world/entity/Pose;")
    @Expression("this.canPlayerFitWithinBlocksAndEntitiesWhen(STANDING)")
    @ModifyExpressionValue(method = "aiStep()V", at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean strangeadventures$greenAvatarCanUncrouchWhenRooted(final boolean original) {
        if (!original && (Object)this instanceof final LocalPlayer player &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) &&
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRooted(player)) {
            return true;
        }
        return original;
    }

}
