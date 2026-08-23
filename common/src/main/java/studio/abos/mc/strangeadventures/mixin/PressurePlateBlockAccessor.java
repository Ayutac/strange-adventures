package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PressurePlateBlock.class)
public interface PressurePlateBlockAccessor {

    @Invoker("<init>")
    static PressurePlateBlock newPressurePlateBlock(final BlockSetType blockSetType, final BlockBehaviour.Properties properties) {
        throw new AssertionError("Untransformed @Accessor");
    }

}
