package studio.abos.mc.strangeadventures.fluid;

import lombok.NonNull;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.item.ModItems;

public abstract class ChorusSapFluid extends AbstractSapFluid {

    @NonNull
    @Override
    public Fluid getFlowing() {
        return ModFluids.CHORUS_SAP_FLOWING.value();
    }

    @NonNull
    @Override
    public Fluid getSource() {
        return ModFluids.CHORUS_SAP_STILL.value();
    }

    @NonNull
    @Override
    protected BlockState createLegacyBlock(final @NonNull FluidState state) {
        return ModBlocks.CHORUS_SAP.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @NonNull
    @Override
    public Item getBucket() {
        return ModItems.CHORUS_SAP_BUCKET.asItem();
    }

    @Override
    public boolean isSame(final @NonNull Fluid fluid) {
        return fluid == ModFluids.CHORUS_SAP_STILL.value() || fluid == ModFluids.CHORUS_SAP_FLOWING.value();
    }

    public static class Source extends ChorusSapFluid {

        @Override
        public boolean isSource(final @NonNull FluidState state) {
            return true;
        }

        @Override
        public int getAmount(final @NonNull FluidState state) {
            return 8;
        }

    }

    public static class Flowing extends ChorusSapFluid {

        @Override
        public boolean isSource(final @NonNull FluidState state) {
            return false;
        }

        @Override
        protected void createFluidStateDefinition(final @NonNull StateDefinition.Builder<@NonNull Fluid, @NonNull FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(final @NonNull FluidState state) {
            return state.getValue(LEVEL);
        }

    }
}
