package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.platform.fluid.BalmFluidTankProvider;
import net.blay09.mods.balm.platform.fluid.DefaultFluidTank;
import net.blay09.mods.balm.platform.fluid.FluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.recipe.ModRecipeTypes;
import studio.abos.mc.strangeadventures.recipe.SapSipperRecipe;
import studio.abos.mc.strangeadventures.recipe.SingleBlockRecipeInput;

import java.util.Optional;

public class SapSipperBlockEntity extends BlockEntity implements BalmFluidTankProvider {

    public static final int MAX_CAPACITY = 1000; // in mB

    protected int extractTicksRemaining;
    protected Tank tank = new Tank();

    protected boolean isDirty;

    public SapSipperBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.SAP_SIPPER.value(), pos, state);
    }

    @Override
    public FluidTank getFluidTank() {
        return tank;
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        output.putInt("ExtractTicksRemaining", extractTicksRemaining);
        tank.serialize(output.child("Tank"));
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        input.child("Tank").ifPresent(tank::deserialize);
        extractTicksRemaining = input.getIntOr("ExtractTicksRemaining", 0);
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final SapSipperBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }
        final BlockState blockBehind = level.getBlockState(pos.relative(state.getValue(HorizontalDirectionalBlock.FACING).getOpposite()));
        final Optional<RecipeHolder<SapSipperRecipe>> recipe = ModRecipeTypes.SAP_SIPPER.getRecipeFor(level, new SingleBlockRecipeInput(blockBehind));
        recipe.ifPresent(holder -> StrangeAdventures.logger.info("Sapping: " + holder.value().getSapResult().getRegisteredName()));
    }

    public class Tank extends DefaultFluidTank {

        public Tank() {
            super(MAX_CAPACITY);
        }

        @Override
        public void setChanged() {
            SapSipperBlockEntity.this.setChanged();
            isDirty = true;
        }
    }

}
