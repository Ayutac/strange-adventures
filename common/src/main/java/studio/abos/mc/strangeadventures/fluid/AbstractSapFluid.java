package studio.abos.mc.strangeadventures.fluid;

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

public abstract class AbstractSapFluid extends FlowingFluid implements FluidWithBottle {

    @Override
    protected boolean canConvertToSource(final ServerLevel serverLevel) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(final LevelAccessor level, final BlockPos pos, final BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
    }

    @Override
    protected int getSlopeFindDistance(final LevelReader level) {
        return 4;
    }

    @Override
    protected int getDropOff(final LevelReader level) {
        return 2;
    }

    @Override
    protected boolean canBeReplacedWith(final FluidState fluidState, final BlockGetter blockGetter, final BlockPos blockPos, final Fluid fluid, final Direction direction) {
        return false;
    }

    @Override
    public int getTickDelay(final LevelReader level) {
        return 30;
    }

    @Override
    protected float getExplosionResistance() {
        return 100f;
    }

}
