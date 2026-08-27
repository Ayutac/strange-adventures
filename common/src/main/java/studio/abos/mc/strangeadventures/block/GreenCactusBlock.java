package studio.abos.mc.strangeadventures.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GreenCactusBlock extends CactusBlock {

    protected static final VoxelShape SHAPE_COLLISION = Block.column(8d, 0d, 15d);
    protected static final VoxelShape SHAPE = Block.column(8d, 0d, 16d);

    protected GreenCactusBlock(final Properties properties) {
        super(properties);
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (level.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) {
                return false;
            }
        }
        BlockState belowState = level.getBlockState(pos.below());
        return (belowState.is(this) || belowState.is(BlockTags.SUPPORTS_CACTUS)) && !level.getBlockState(pos.above()).liquid();

    }

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_COLLISION;
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

}
