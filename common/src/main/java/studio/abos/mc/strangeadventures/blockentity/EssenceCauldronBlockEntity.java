package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.platform.fluid.BalmFluidTankProvider;
import net.blay09.mods.balm.platform.fluid.FluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

public class EssenceCauldronBlockEntity extends BlockEntity implements BalmFluidTankProvider {

    protected EssenceCauldronBlockEntity.Tank tank = new EssenceCauldronBlockEntity.Tank();

    public EssenceCauldronBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.ESSENCE_CAULDRON.value(), pos, state);
    }

    @Override
    public Tank getFluidTank() {
        return tank;
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        tank.serialize(output.child("Tank"));
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        input.child("Tank").ifPresent(tank::deserialize);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final EssenceCauldronBlockEntity entity) {

    }

    public class Tank implements FluidTank {

        protected Fluid[] fluids = new Fluid[]{Fluids.EMPTY, Fluids.EMPTY, Fluids.EMPTY};

        @Override
        public int fill(final int slot, final Fluid fluid, final int maxFill, final boolean simulate) {
            if (!canFill(slot, fluid) || maxFill != ModFluids.BOTTLE_AMOUNT) {
                return 0;
            }
            boolean canActuallyFill = fluids[slot].isSame(Fluids.EMPTY);
            if (canActuallyFill && !simulate) {
                fluids[slot] = fluid;
                setChanged();
            }
            return canActuallyFill ? ModFluids.BOTTLE_AMOUNT : 0;
        }

        @Override
        public int drain(final int slot, final Fluid fluid, final int maxDrain, final boolean simulate) {
            if (!canDrain(slot, fluid) || maxDrain != ModFluids.BOTTLE_AMOUNT) {
                return 0;
            }
            boolean canActuallyDrain = fluids[slot].isSame(fluid);
            if (canActuallyDrain && !simulate) {
                fluids[slot] = Fluids.EMPTY;
                setChanged();
            }
            return canActuallyDrain ? ModFluids.BOTTLE_AMOUNT : 0;
        }

        @Override
        public Fluid getFluid(final int slot) {
            if (!validSlot(slot)) {
                return Fluids.EMPTY;
            }
            return fluids[slot];
        }

        @Override
        public void setFluid(final int slot, final Fluid fluid, final int amount) {
            if (!validSlot(slot) || amount != ModFluids.BOTTLE_AMOUNT) {
                return;
            }
            fluids[slot] = fluid;
        }

        @Override
        public int getAmount(final int slot) {
            if (!validSlot(slot)) {
                return 0;
            }
            return fluids[slot].isSame(Fluids.EMPTY) ? 0 : ModFluids.BOTTLE_AMOUNT;
        }

        /**
         * Can only change anything when amount is 0 and the targeted slot isn't empty.
         */
        @Override
        public void setAmount(final int slot, final int amount) {
            if (!validSlot(slot) || fluids[slot].isSame(Fluids.EMPTY)) {
                return;
            }
            if (amount == 0) {
                fluids[slot] = Fluids.EMPTY;
            }
        }

        @Override
        public int getCapacity(final int slot) {
            if (!validSlot(slot)) {
                return 0;
            }
            return ModFluids.BOTTLE_AMOUNT;
        }

        @Override
        public boolean canDrain(final int slot, final Fluid fluid) {
            return validSlot(slot) && !fluids[slot].isSame(Fluids.EMPTY);
        }

        @Override
        public boolean canFill(final int slot, final Fluid fluid) {
            return validSlot(slot) && fluids[slot].isSame(Fluids.EMPTY);
        }

        @Override
        public boolean isEmpty(final int slot) {
            return !validSlot(slot) || fluids[slot].isSame(Fluids.EMPTY);
        }

        @Override
        public int getSlotCount() {
            return fluids.length;
        }

        public boolean validSlot(final int slot) {
            return slot >= 0 && slot < fluids.length;
        }

        public int fill(final Fluid fluid, final boolean simulate, final boolean bucket) {
            if (fluid.isSame(Fluids.EMPTY)) {
                return 0;
            }
            // bottle logic
            if (!bucket) {
                for (int i = 0; i < fluids.length; i++) {
                    if (fill(i, fluid, ModFluids.BOTTLE_AMOUNT, simulate) == ModFluids.BOTTLE_AMOUNT) {
                        return ModFluids.BOTTLE_AMOUNT;
                    }
                }
                return 0;
            }
            // bucket logic
            int freeSpaces = 0;
            for (int i = 0; i < fluids.length; i++) {
                if (fill(i, fluid, ModFluids.BOTTLE_AMOUNT, true) == ModFluids.BOTTLE_AMOUNT) {
                    freeSpaces++;
                }
            }
            if (freeSpaces >= 3) {
                if (!simulate) {
                    freeSpaces = 3;
                    for (int i = 0; i < fluids.length; i++) {
                        if (fill(i, fluid, ModFluids.BOTTLE_AMOUNT, false) == ModFluids.BOTTLE_AMOUNT) {
                            freeSpaces--;
                        }
                        if (freeSpaces <= 0) {
                            break;
                        }
                    }
                }
                return ModFluids.BUCKET_AMOUNT;
            }
            return 0;
        }

        public Fluid drain(final boolean simulate, final boolean bucket) {
            // bottle logic
            if (!bucket) {
                for (int i = 0; i < fluids.length; i++) {
                    final Fluid fluid = getFluid(i);
                    if (!isEmpty(i) && drain(i, fluid, ModFluids.BOTTLE_AMOUNT, simulate) == ModFluids.BOTTLE_AMOUNT) {
                        return fluid;
                    }
                }
                return Fluids.EMPTY;
            }
            // bucket logic
            final Fluid fluid = getFluid(0);
            int filledSpaces = 0;
            for (int i = 0; i < fluids.length; i++) {
                if (drain(i, fluid, ModFluids.BOTTLE_AMOUNT, true) == ModFluids.BOTTLE_AMOUNT) {
                    filledSpaces++;
                }
            }
            if (filledSpaces >= 3) {
                if (!simulate) {
                    filledSpaces = 3;
                    for (int i = 0; i < fluids.length; i++) {
                        if (drain(i, fluid, ModFluids.BOTTLE_AMOUNT, false) == ModFluids.BOTTLE_AMOUNT) {
                            filledSpaces--;
                        }
                        if (filledSpaces <= 0) {
                            break;
                        }
                    }
                }
                return fluid;
            }
            return Fluids.EMPTY;
        }

        public void serialize(ValueOutput output) {
            for (int i = 0; i < fluids.length; i++) {
                if (!fluids[i].isSame(Fluids.EMPTY)) {
                    output.putString(Integer.toString(i), BuiltInRegistries.FLUID.getKey(fluids[i]).toString());
                }
            }
        }

        public void deserialize(ValueInput input) {
            for (int i = 0; i < fluids.length; i++) {
                fluids[i] = input.getString(Integer.toString(i))
                        .map(Identifier::tryParse)
                        .map(BuiltInRegistries.FLUID::getValue)
                        .orElse(Fluids.EMPTY);
            }
        }

        public void setChanged() {
            EssenceCauldronBlockEntity.this.setChanged();
        }

    }

}
