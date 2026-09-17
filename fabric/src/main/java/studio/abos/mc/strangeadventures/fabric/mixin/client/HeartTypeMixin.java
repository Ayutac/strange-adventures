package studio.abos.mc.strangeadventures.fabric.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.Hud;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(Hud.HeartType.class)
public enum HeartTypeMixin {

    STRANGE_ADVENTURES_GREEN_AVATAR(
            StrangeAdventures.id("hud/heart/green_avatar_full"),
            StrangeAdventures.id("hud/heart/green_avatar_full_blinking"),
            StrangeAdventures.id("hud/heart/green_avatar_half"),
            StrangeAdventures.id("hud/heart/green_avatar_half_blinking"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_full"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_full_blinking"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_half"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_half_blinking"));

    @Shadow
    HeartTypeMixin(final Identifier full, final Identifier fullBlinking, final Identifier half, final Identifier halfBlinking, final Identifier hardcoreFull, final Identifier hardcoreFullBlinking, final Identifier hardcoreHalf, final Identifier hardcoreHalfBlinking) {
        throw new AssertionError("Untransformed @Shadow");
    }

    @ModifyReturnValue(method = "forPlayer(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/client/gui/Hud$HeartType;", at = @At("RETURN"))
    private static Hud.HeartType strangeadventures$greenAvatarHearts(final Hud.HeartType original, final Player player) {
        if (original == Hud.HeartType.NORMAL && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            return Hud.HeartType.STRANGE_ADVENTURES_GREEN_AVATAR;
        }
        return original;
    }

}
