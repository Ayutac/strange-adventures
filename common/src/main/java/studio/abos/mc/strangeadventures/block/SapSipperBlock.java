package studio.abos.mc.strangeadventures.block;

import com.mojang.serialization.MapCodec;
import net.blay09.mods.balm.platform.fluid.FluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.blockentity.SapSipperBlockEntity;
import studio.abos.mc.strangeadventures.fluid.AbstractSapFluid;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

public class SapSipperBlock extends BaseEntityBlock {

    public static final MapCodec<SapSipperBlock> CODEC = simpleCodec(SapSipperBlock::new);

    protected SapSipperBlock(final Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        final BlockEntity entity = level.getBlockEntity(pos);
        if (!(entity instanceof SapSipperBlockEntity sipper)) {
            return InteractionResult.FAIL;
        }
        final FluidTank tank = sipper.getFluidTank();
        if (tank.getAmount(0) > 0) {
            player.sendSystemMessage(Component.translatable("gui.strangeadventures.sap_sipper", tank.getAmount(0)).append(
                    Component.translatable("fluid." + tank.getFluid(0).builtInRegistryHolder().getRegisteredName().replace(':','.')).append(".")));
        }
        else {
            player.sendSystemMessage(Component.translatable("gui.strangeadventures.sap_sipper.empty"));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        final BlockEntity entity = level.getBlockEntity(pos);
        if (!(entity instanceof SapSipperBlockEntity sipper)) {
            return InteractionResult.FAIL;
        }
        final FluidTank tank = sipper.getFluidTank();
        if (tank.getFluid(0) instanceof AbstractSapFluid sap) {
            if (itemStack.getItem() == Items.GLASS_BOTTLE && tank.drain(0, sap, ModFluids.BOTTLE_AMOUNT, true) == ModFluids.BOTTLE_AMOUNT) {
                itemStack.consume(1, player);
                tank.drain(0, sap, ModFluids.BOTTLE_AMOUNT, false);
                player.getInventory().add(new ItemStack(sap.getBottle()));
                return InteractionResult.SUCCESS;
            }
            if (itemStack.getItem() == Items.BUCKET && tank.drain(0, sap, ModFluids.BUCKET_AMOUNT, true) == ModFluids.BUCKET_AMOUNT) {
                itemStack.consume(1, player);
                tank.drain(0, sap, ModFluids.BUCKET_AMOUNT, false);
                player.getInventory().add(new ItemStack(sap.getBucket()));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        return new SapSipperBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.SAP_SIPPER.value(), SapSipperBlockEntity::tick);
    }
}
