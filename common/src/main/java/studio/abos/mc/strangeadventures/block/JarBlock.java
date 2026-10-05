package studio.abos.mc.strangeadventures.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.JarBlockEntity;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

public class JarBlock extends BaseEntityBlock {

    public JarBlock(final Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        final BlockEntity entity = level.getBlockEntity(pos);
        if (!(entity instanceof final JarBlockEntity jar)) {
            return InteractionResult.FAIL;
        }
        // sealed forbids interaction
        if (jar.isSealed(level, pos)) {
            return InteractionResult.SUCCESS;
        }
        // get items out
        jar.retrieveItem().ifPresent(stack -> player.getInventory().add(stack));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        final BlockEntity entity = level.getBlockEntity(pos);
        if (!(entity instanceof final JarBlockEntity jar)) {
            return InteractionResult.FAIL;
        }
        // sealed forbids interaction
        if (jar.isSealed(level, pos)) {
            return InteractionResult.SUCCESS;
        }
        final JarBlockEntity.Tank tank = jar.getFluidTank();
        // fill or empty a bucket
        if (itemStack.getItem() instanceof final BucketItem bucket) {
            if (Fluids.EMPTY.isSame(bucket.getContent())) {
                final Fluid fluid = tank.getFluid(0);
                if (!fluid.isSame(Fluids.EMPTY) && tank.getAmount(0) == ModFluids.BUCKET_AMOUNT) {
                    itemStack.consume(1, player);
                    tank.drain(0, fluid, ModFluids.BUCKET_AMOUNT, false);
                    player.getInventory().add(new ItemStack(fluid.getBucket()));
                    return InteractionResult.SUCCESS;
                }
            }
            else {
                if (tank.fill(0, bucket.getContent(), ModFluids.BUCKET_AMOUNT, true) == ModFluids.BUCKET_AMOUNT) {
                    itemStack.consume(1, player);
                    tank.fill(0, bucket.getContent(), ModFluids.BUCKET_AMOUNT, false);
                    player.getInventory().add(new ItemStack(Items.BUCKET));
                    return InteractionResult.SUCCESS;
                }
            }
        }
        // put items in
        if (!itemStack.isEmpty() && jar.storeItem(itemStack.copyWithCount(1))) {
            itemStack.consume(1, player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    public BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        return new JarBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.JAR.value(), JarBlockEntity::tick);
    }

}
