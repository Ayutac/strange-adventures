package studio.abos.mc.strangeadventures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import studio.abos.mc.strangeadventures.api.InternalMethods;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.block.ModBlocks;

import java.util.EnumSet;
import java.util.Optional;

public class InternalMethodsImpl implements InternalMethods {

    private static final int[] XZ_TP_RANGE = new int[] {0,-1,1,-2,2};
    private static final int[] Y_TP_RANGE = new int[] {0,-1,1,-2,2,-3,3,-4,4};

    private Optional<BlockPos> findBestTpPosition(final LevelAccessor level, final BlockPos idealPos, final int height) {
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

    @Override
    public void tpOverworldToGreen(final LivingEntity toTeleport, final BlockPos startPos) {
        if (!Level.OVERWORLD.equals(toTeleport.level().dimension())) {
            return;
        }
        final BlockPos idealTpPos = new BlockPos(
                startPos.getX() / StrangeAdventuresApi.GREEN_DIMENSION_FACTOR,
                startPos.getY(),
                startPos.getZ() / StrangeAdventuresApi.GREEN_DIMENSION_FACTOR);
        final int entityHeight = (int)Math.ceil(toTeleport.getBbHeight());
        final ServerLevel green = toTeleport.level().getServer().getLevel(StrangeAdventuresApi.GREEN_DIMENSION);
        if (green == null) {
            return;
        }
        Optional<BlockPos> bestTpPos = findBestTpPosition(green, idealTpPos, entityHeight);
        if (bestTpPos.isEmpty()) {
            green.setBlockAndUpdate(idealTpPos.below(), Blocks.MOSS_BLOCK.defaultBlockState());
            green.setBlockAndUpdate(idealTpPos, ModBlocks.GREEN_FLOWER.defaultBlockState());
            for (int i = 2; i <= entityHeight; i++) {
                green.setBlockAndUpdate(idealTpPos.above(i), Blocks.AIR.defaultBlockState());
            }
            bestTpPos = Optional.of(idealTpPos);
        }
        bestTpPos.ifPresent(pos -> toTeleport.teleportTo(green, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                EnumSet.noneOf(Relative.class), toTeleport.getYRot(), toTeleport.getXRot(), false));
    }

    @Override
    public void tpGreenToOverworld(final LivingEntity toTeleport, final BlockPos startPos) {
        if (!StrangeAdventuresApi.GREEN_DIMENSION.equals(toTeleport.level().dimension())) {
            return;
        }
        final BlockPos idealTpPos = new BlockPos(
                startPos.getX() * StrangeAdventuresApi.GREEN_DIMENSION_FACTOR,
                startPos.getY(),
                startPos.getZ() * StrangeAdventuresApi.GREEN_DIMENSION_FACTOR);
        final int entityHeight = (int)Math.ceil(toTeleport.getBbHeight());
        final ServerLevel overworld = toTeleport.level().getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) {
            return;
        }
        Optional<BlockPos> bestTpPos = findBestTpPosition(overworld, idealTpPos, entityHeight);
        if (bestTpPos.isEmpty()) {
            overworld.setBlockAndUpdate(idealTpPos.below(), Blocks.MOSS_BLOCK.defaultBlockState());
            overworld.setBlockAndUpdate(idealTpPos, ModBlocks.GREEN_FLOWER.defaultBlockState());
            for (int i = 2; i <= entityHeight; i++) {
                overworld.setBlockAndUpdate(idealTpPos.above(i), Blocks.AIR.defaultBlockState());
            }
            bestTpPos = Optional.of(idealTpPos);
        }
        bestTpPos.ifPresent(pos -> toTeleport.teleportTo(overworld, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                EnumSet.noneOf(Relative.class), toTeleport.getYRot(), toTeleport.getXRot(), false));
    }

}
