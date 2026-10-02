package studio.abos.mc.strangeadventures.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@Mixin(Hud.class)
public abstract class HudMixin {

    @WrapWithCondition(method = "extractPlayerHealth(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Hud;extractFood(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/world/entity/player/Player;II)V"))
    private boolean strangeadventures$greenAvatarDoesntRenderFood(final Hud instance, final GuiGraphicsExtractor graphics, final Player player, final int yLineBase, final int xRight) {
        return !StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player);
    }

    @Shadow
    @Nullable
    protected abstract Player getCameraPlayer();

    // > You cannot Wrap(WithCondition|Operation) anything with a local variable assignment, due to the potential to create invalid bytecode
    @Definition(id = "yLineAir", local = @Local(type = int.class, name = "yLineAir"))
    @Expression("yLineAir = yLineAir + @(-10)")
    @ModifyExpressionValue(method = "extractPlayerHealth(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private int strangeadventures$greenAvatarDoesntRenderFoodLine(final int original) {
        if (getCameraPlayer() instanceof final Player player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
            return 0; // remove -10 of the hunger bar
        }
        return original;
    }

}
