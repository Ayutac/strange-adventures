package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(CakeBlock.class)
public abstract class CakeBlockMixin {

    @WrapWithCondition(method = "eat(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    private static boolean strangeadventures$greenAvatarDoesntGainFromCake(final FoodData instance, final int food, final float saturationModifier, final LevelAccessor level, final BlockPos pos, final BlockState state, final Player player) {
        return !StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player);
    }

}
