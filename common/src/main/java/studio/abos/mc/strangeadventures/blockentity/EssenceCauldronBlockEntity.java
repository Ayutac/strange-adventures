package studio.abos.mc.strangeadventures.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceCauldronBlockEntity extends BlockEntity {

    public EssenceCauldronBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.ESSENCE_CAULDRON.value(), pos, state);
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final EssenceCauldronBlockEntity entity) {

    }

}
