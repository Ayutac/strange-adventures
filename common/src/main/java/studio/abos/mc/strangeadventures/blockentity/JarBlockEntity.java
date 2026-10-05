package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.platform.fluid.BalmFluidTankProvider;
import net.blay09.mods.balm.platform.fluid.DefaultFluidTank;
import net.blay09.mods.balm.platform.fluid.FluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class JarBlockEntity extends BlockEntity implements BalmFluidTankProvider {

    protected Tank tank = new Tank();

    public JarBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.JAR.value(), pos, state);
    }

    @Override
    public FluidTank getFluidTank() {
        return tank;
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final JarBlockEntity entity) {

    }

    public class Tank extends DefaultFluidTank {

        public Tank() {
            super(1000); // in millibuckets
        }

        @Override
        public void setChanged() {
            JarBlockEntity.this.setChanged();
        }

    }

}
