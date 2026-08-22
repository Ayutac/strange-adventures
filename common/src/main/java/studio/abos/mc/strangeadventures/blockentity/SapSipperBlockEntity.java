package studio.abos.mc.strangeadventures.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SapSipperBlockEntity extends BlockEntity {

    public SapSipperBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.SAP_SIPPER.value(), pos, state);
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final SapSipperBlockEntity sapSipper) {
        final BlockState blockBehind = level.getBlockState(pos.relative(state.getValue(HorizontalDirectionalBlock.FACING).getOpposite(), 1));
        //StrangeAdventures.logger.info("Facing: " + blockBehind.getBlock().getDescriptionId());
    }

}
