package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SaplingBlock.class)
public interface SaplingBlockAccessor {

    @Invoker("<init>")
    static SaplingBlock newSaplingBlock(final TreeGrower treeGrower, final BlockBehaviour.Properties properties) {
        throw new AssertionError("Untransformed @Accessor");
    }

}
