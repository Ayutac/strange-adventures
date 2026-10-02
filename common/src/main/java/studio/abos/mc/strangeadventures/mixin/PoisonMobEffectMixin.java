package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.PoisonMobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(PoisonMobEffect.class)
public abstract class PoisonMobEffectMixin {

    @WrapOperation(method = "applyEffectTick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;I)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    public boolean strangeadventures$greenAvatarImmuneToPoison(final LivingEntity instance, final ServerLevel level, final DamageSource source, final float damage, final Operation<Boolean> original) {
        if (!(instance instanceof final Player player) || !StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            return original.call(instance, level, source, damage);
        }
        return false;
    }

}
