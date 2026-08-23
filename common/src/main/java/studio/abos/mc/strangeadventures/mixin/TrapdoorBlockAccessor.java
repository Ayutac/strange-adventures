package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TrapDoorBlock.class)
public interface TrapdoorBlockAccessor {

    @Invoker("<init>")
    static TrapDoorBlock newTrapdoorBlock(final BlockSetType blockSetType, final BlockBehaviour.Properties properties) {
        throw new AssertionError("Untransformed @Accessor");
    }

}
