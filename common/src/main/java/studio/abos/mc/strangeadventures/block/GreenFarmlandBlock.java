package studio.abos.mc.strangeadventures.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GreenFarmlandBlock extends FarmlandBlock {

    protected GreenFarmlandBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(final Level level, final BlockState state, final BlockPos pos, final Entity entity, final double fallDistance) {
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
    }

}
