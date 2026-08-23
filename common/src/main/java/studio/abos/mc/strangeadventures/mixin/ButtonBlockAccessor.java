package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ButtonBlock.class)
public interface ButtonBlockAccessor {

    @Invoker("<init>")
    static ButtonBlock newButtonBlock(final BlockSetType blockSetType, final int ticksToStayPressed, final BlockBehaviour.Properties properties) {
        throw new AssertionError("Untransformed @Accessor");
    }

}
