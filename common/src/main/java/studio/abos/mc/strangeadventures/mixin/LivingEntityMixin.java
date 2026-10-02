package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(method = "canBreatheUnderwater()Z", at = @At("RETURN"))
    public boolean strangeadventures$greenAvatarCanBreatheUnderwater(final boolean original) {
        if ((LivingEntity)(Object)this instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            return true;
        }
        return original;
    }

}
