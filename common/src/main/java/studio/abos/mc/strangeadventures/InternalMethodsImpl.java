package studio.abos.mc.strangeadventures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriConsumer;
import studio.abos.mc.strangeadventures.api.InternalMethods;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.block.ModBlocks;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class InternalMethodsImpl implements InternalMethods {

    private static final int[] XZ_TP_RANGE = new int[] {0,-1,1,-2,2};
    private static final int[] Y_TP_RANGE = new int[] {0,-1,1,-2,2,-3,3,-4,4};

    private static Optional<BlockPos> findBestTpPosition(final LevelAccessor level, final BlockPos idealPos, final int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive!");
        }
        final var mutablePos = new BlockPos.MutableBlockPos();
        for (final int dy : Y_TP_RANGE) {
            for (final int dx : XZ_TP_RANGE) {
                coordLoop: for (final int dz : XZ_TP_RANGE) {
                    mutablePos.set(idealPos);
                    mutablePos.move(dx, dy, dz);
                    for (int i = 0; i < height; i++) {
                        final BlockState blockState = level.getBlockState(mutablePos);
                        if (!(blockState.isAir() || blockState.getBlock() == ModBlocks.GREEN_FLOWER.asBlock())) {
                            continue coordLoop;
                        }
                        mutablePos.move(Direction.UP, 1);
                    }
                    mutablePos.move(Direction.DOWN, height+1);
                    if (!level.getBlockState(mutablePos).isAir()) {
                        mutablePos.move(Direction.UP, 1);
                        return Optional.of(mutablePos.immutable());
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static void makeGreenSpace(final Level level, final BlockPos pos, final int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive!");
        }
        level.setBlockAndUpdate(pos.below(), Blocks.MOSS_BLOCK.defaultBlockState());
        level.setBlockAndUpdate(pos, ModBlocks.GREEN_FLOWER.defaultBlockState());
        for (int i = 2; i <= height; i++) {
            level.setBlockAndUpdate(pos.above(i), Blocks.AIR.defaultBlockState());
        }
    }

    private static void tpFromTo(final ResourceKey<Level> from, final ResourceKey<Level> to, final LivingEntity toTeleport,
                          final BlockPos startPos, final UnaryOperator<BlockPos> targetPosOperator, final TriConsumer<Level, BlockPos, Integer> makeSpaceIfNeeded) {
        if (!from.equals(toTeleport.level().dimension())) {
            return;
        }
        final BlockPos idealTpPos = targetPosOperator.apply(startPos);
        final int entityHeight = (int)Math.ceil(toTeleport.getBbHeight());
        final ServerLevel level = toTeleport.level().getServer().getLevel(to);
        if (level == null) {
            return;
        }
        Optional<BlockPos> bestTpPos = findBestTpPosition(level, idealTpPos, entityHeight);
        if (bestTpPos.isEmpty()) {
            makeSpaceIfNeeded.accept(level, idealTpPos, entityHeight);
            bestTpPos = Optional.of(idealTpPos);
        }
        bestTpPos.ifPresent(pos -> toTeleport.teleportTo(level, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                EnumSet.noneOf(Relative.class), toTeleport.getYRot(), toTeleport.getXRot(), false));
    }

    @Override
    public void tpOverworldToGreen(final LivingEntity toTeleport, final BlockPos startPos) {
        tpFromTo(Level.OVERWORLD, StrangeAdventuresApi.GREEN_DIMENSION, toTeleport, startPos, pos -> new BlockPos(
                pos.getX() / StrangeAdventuresApi.GREEN_DIMENSION_FACTOR, pos.getY(),
                pos.getZ() / StrangeAdventuresApi.GREEN_DIMENSION_FACTOR), InternalMethodsImpl::makeGreenSpace);
    }

    @Override
    public void tpGreenToOverworld(final LivingEntity toTeleport, final BlockPos startPos) {
        tpFromTo(StrangeAdventuresApi.GREEN_DIMENSION, Level.OVERWORLD, toTeleport, startPos, pos -> new BlockPos(
                pos.getX() * StrangeAdventuresApi.GREEN_DIMENSION_FACTOR, pos.getY(),
                pos.getZ() * StrangeAdventuresApi.GREEN_DIMENSION_FACTOR), InternalMethodsImpl::makeGreenSpace);
    }

}
