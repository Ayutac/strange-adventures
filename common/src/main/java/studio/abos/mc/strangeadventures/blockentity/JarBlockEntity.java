package studio.abos.mc.strangeadventures.blockentity;

import net.blay09.mods.balm.platform.fluid.BalmFluidTankProvider;
import net.blay09.mods.balm.platform.fluid.DefaultFluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

import java.util.Optional;

public class JarBlockEntity extends BlockEntity implements BalmFluidTankProvider {

    public static final int MAX_ITEMS = 4;

    protected final NonNullList<ItemStack> items = NonNullList.withSize(MAX_ITEMS, ItemStack.EMPTY);
    protected Tank tank = new Tank();

    public JarBlockEntity(final BlockPos pos, final BlockState state) {
        super(ModBlockEntities.JAR.value(), pos, state);
    }

    @Override
    public Tank getFluidTank() {
        return tank;
    }

    public boolean isSealed(final LevelAccessor level, final BlockPos pos) {
        final BlockState above = level.getBlockState(pos.above());
        return above.is(BlockItemTags.SLABS.block()) && above.getValue(SlabBlock.TYPE) == SlabType.BOTTOM;
    }

    public boolean storeItem(final ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isEmpty()) {
                items.set(i, stack);
                setChanged();
                return true;
            }
        }
        return false;
    }

    public Optional<ItemStack> retrieveItem() {
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                final ItemStack item = items.get(i);
                items.set(i, ItemStack.EMPTY);
                setChanged();
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        tank.serialize(output.child("Tank"));
        ContainerHelper.saveAllItems(output, items); // identifier: "Items"
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items); // identifier: "Items"
        input.child("Tank").ifPresent(tank::deserialize);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level == null) {
            return;
        }
        final BlockState blockState = getBlockState();
        level.sendBlockUpdated(getBlockPos(), blockState, blockState, Block.UPDATE_ALL);
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final JarBlockEntity entity) {

    }

    public class Tank extends DefaultFluidTank {

        public Tank() {
            super(ModFluids.BUCKET_AMOUNT); // in millibuckets
        }

        @Override
        public void setChanged() {
            JarBlockEntity.this.setChanged();
        }

    }

}
