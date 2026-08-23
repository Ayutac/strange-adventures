package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.platform.fluid.BalmFluidTankProvider;
import net.blay09.mods.balm.platform.fluid.DefaultFluidTank;
import net.blay09.mods.balm.platform.fluid.FluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import studio.abos.mc.strangeadventures.recipe.ModRecipeTypes;
import studio.abos.mc.strangeadventures.recipe.SapSipperRecipe;
import studio.abos.mc.strangeadventures.recipe.SingleBlockRecipeInput;

public class SapSipperBlockEntity extends BlockEntity implements BalmFluidTankProvider {

    public static final int MAX_CAPACITY = 1000; // in mB

    protected int extractTicksRemaining;
    protected Tank tank = new Tank();

    protected final RecipeManager.CachedCheck<SingleBlockRecipeInput, SapSipperRecipe> quickCheck;
    protected boolean isDirty;

    public SapSipperBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.SAP_SIPPER.value(), pos, state);
        quickCheck = RecipeManager.createCheck(ModRecipeTypes.SAP_SIPPER.type());
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

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final SapSipperBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }
        final BlockState blockBehind = level.getBlockState(pos.relative(state.getValue(HorizontalDirectionalBlock.FACING).getOpposite()));
        final SingleBlockRecipeInput input = new SingleBlockRecipeInput(blockBehind);
        final RecipeHolder<SapSipperRecipe> recipe = entity.quickCheck.getRecipeFor(input, (ServerLevel)level).orElse(null);
        boolean dirty = false;
        if (recipe != null) {
            final Holder<Fluid> result = recipe.value().getSapResult();
            if (entity.tank.canFill(0, result.value())) {
                if (entity.extractTicksRemaining > 0) {
                    entity.extractTicksRemaining--;
                    // complete a filling round
                    if (entity.extractTicksRemaining <= 0) { // <= makes it thread-safer than ==
                        entity.tank.fill(0, result.value(), recipe.value().getAmountPerSap(), false);
                    }
                }
                // start a new filling round
                if (entity.extractTicksRemaining <= 0) {
                    entity.extractTicksRemaining = recipe.value().getTicksPerSap();
                }
                dirty = true;
            }
            else { // if no matching block behind
                dirty = entity.extractTicksRemaining != 0;
                entity.extractTicksRemaining = 0;
            }
        }
        else { // if no valid block behind
            dirty = entity.extractTicksRemaining != 0;
            entity.extractTicksRemaining = 0;
        }
        if (dirty) {
            entity.setChanged();
        }
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
