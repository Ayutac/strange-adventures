package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ArmorSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(ArmorSlot.class)
public abstract class ArmorSlotMixin {

    @Shadow
    @Final
    private LivingEntity owner;

    @ModifyReturnValue(method = "isActive()Z", at = @At("RETURN"))
    public boolean strangeadventures$greenAvatarCannotEquipArmor(final boolean original) {
        if (owner instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player) && original) {
            return false;
        }
        return original;
    }

}
