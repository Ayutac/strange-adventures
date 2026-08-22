package studio.abos.mc.strangeadventures.fluid;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.item.ModItems;

public abstract class SpruceSapFluid extends AbstractSapFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.SPRUCE_SAP_FLOWING.value();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.SPRUCE_SAP_STILL.value();
    }

   @Override
    protected BlockState createLegacyBlock(final FluidState state) {
        return ModBlocks.SPRUCE_SAP.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public Item getBucket() {
        return ModItems.SPRUCE_SAP_BUCKET.asItem();
    }

    @Override
    public Item getBottle() {
        return ModItems.SPRUCE_SAP_BOTTLE.asItem();
    }

    @Override
    public boolean isSame(final Fluid fluid) {
        return fluid == ModFluids.SPRUCE_SAP_STILL.value() || fluid == ModFluids.SPRUCE_SAP_FLOWING.value();
    }

    public static class Source extends SpruceSapFluid {

        @Override
        public boolean isSource(final FluidState state) {
            return true;
        }

        @Override
        public int getAmount(final FluidState state) {
            return 8;
        }

    }

    public static class Flowing extends SpruceSapFluid {

        @Override
        public boolean isSource(final FluidState state) {
            return false;
        }

        @Override
        protected void createFluidStateDefinition(final StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(final FluidState state) {
            return state.getValue(LEVEL);
        }

    }
}
