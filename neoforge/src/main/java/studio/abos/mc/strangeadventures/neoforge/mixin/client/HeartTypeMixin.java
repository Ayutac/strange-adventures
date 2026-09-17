package studio.abos.mc.strangeadventures.neoforge.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.Hud;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(Hud.HeartType.class)
public abstract class HeartTypeMixin {

    @Unique
    private static Hud.HeartType strangeadventures$greenAvatar;

    @ModifyReturnValue(method = "forPlayer(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/client/gui/Hud$HeartType;", at = @At("RETURN"))
    private static Hud.HeartType strangeadventures$greenAvatarHearts(final Hud.HeartType original, final Player player) {
        if (original == Hud.HeartType.NORMAL && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            if (strangeadventures$greenAvatar == null) {
                strangeadventures$greenAvatar = Hud.HeartType.valueOf("STRANGEADVENTURES_GREEN_AVATAR");
            }
            return strangeadventures$greenAvatar;
        }
        return original;
    }

}
