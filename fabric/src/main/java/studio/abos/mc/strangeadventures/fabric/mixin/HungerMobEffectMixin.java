package studio.abos.mc.strangeadventures.fabric.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.effect.HungerMobEffect;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(HungerMobEffect.class)
public abstract class HungerMobEffectMixin {

    @WrapWithCondition(method = "applyEffectTick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;I)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
    public boolean strangeadventures$greenAvatarImmuneToHunger(final Player instance, final float amount) {
        return !StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(instance);
    }

}
