package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.tag.ModDamageTypeTags;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @ModifyReturnValue(method = "isInvulnerableTo(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)Z", at = @At("RETURN"))
    public boolean strangeadventures$dontHurtGreenAvatar(final boolean original, final ServerLevel level, final DamageSource source) {
        if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive((Player)(Object)this) && source.is(ModDamageTypeTags.GREEN_AVATAR_IMMUNITIES) && !original) {
            return true;
        }
        return original;
    }

}
