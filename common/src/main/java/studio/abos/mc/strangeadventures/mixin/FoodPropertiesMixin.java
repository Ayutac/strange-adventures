package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(FoodProperties.class)
public abstract class FoodPropertiesMixin {

    @WrapWithCondition(method = "onConsume(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/component/Consumable;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(Lnet/minecraft/world/food/FoodProperties;)V"))
    public boolean strangeadventures$greenAvatarDoestGainFromRegularFood(final FoodData instance, final FoodProperties foodProperties, final Level level, final LivingEntity user, final ItemStack stack, final Consumable consumable) {
        return user instanceof final Player player && !StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player);
    }

}
