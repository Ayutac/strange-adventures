package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LiquidBlock.class)
public interface LiquidBlockAccessor {

    @Invoker("<init>")
    static LiquidBlock newLiquidBlock(final FlowingFluid fluid, final BlockBehaviour.Properties properties) {
        throw new AssertionError("Untransformed @Accessor");
    }

}
