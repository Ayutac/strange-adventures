package studio.abos.mc.strangeadventures.fluid;

import lombok.NonNull;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class AbstractSapFluid extends FlowingFluid {

    @Override
    protected boolean canConvertToSource(final @NonNull ServerLevel serverLevel) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(final @NonNull LevelAccessor level, final @NonNull BlockPos pos, final BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
    }

    @Override
    protected int getSlopeFindDistance(final @NonNull LevelReader level) {
        return 4;
    }

    @Override
    protected int getDropOff(final @NonNull LevelReader level) {
        return 1;
    }

    @Override
    protected boolean canBeReplacedWith(final @NonNull FluidState fluidState, final @NonNull BlockGetter blockGetter, final @NonNull BlockPos blockPos, final @NonNull Fluid fluid, final @NonNull Direction direction) {
        return false;
    }

    @Override
    public int getTickDelay(final @NonNull LevelReader level) {
        return 35;
    }

    @Override
    protected float getExplosionResistance() {
        return 100f;
    }

}
