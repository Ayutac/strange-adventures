package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ArmorSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(ArmorSlot.class)
public abstract class ArmorSlotMixin {

    @Shadow
    @Final
    private LivingEntity owner;

    @Inject(method = "isActive()Z", at = @At("RETURN"), cancellable = true)
    public void strangeadventures$greenAvatarCannotEquipArmor(final CallbackInfoReturnable<Boolean> cir) {
        if (owner instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) && cir.getReturnValueZ()) {
            cir.setReturnValue(false);
        }
    }

}
