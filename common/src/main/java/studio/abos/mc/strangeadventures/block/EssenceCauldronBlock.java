package studio.abos.mc.strangeadventures.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.fluid.FluidWithBottle;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.item.BottleItem;

public class EssenceCauldronBlock extends BaseEntityBlock {

    public static final MapCodec<EssenceCauldronBlock> CODEC = simpleCodec(EssenceCauldronBlock::new);
    private static final VoxelShape SHAPE_INSIDE = Block.column(12d, 4d, 16d);
    protected static final VoxelShape SHAPE = Util.make(() -> Shapes.join(Shapes.block(), Shapes.or(Block.column(16d, 8d, 0d, 3d), Block.column(8d, 16d, 0d, 3d), Block.column(12d, 0d, 3d), SHAPE_INSIDE), BooleanOp.ONLY_FIRST));

    public EssenceCauldronBlock(final Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
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
        if (!(entity instanceof EssenceCauldronBlockEntity cauldron)) {
            return InteractionResult.FAIL;
        }
        cauldron.retrieveItem().ifPresent(stack -> player.getInventory().add(stack));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        final BlockEntity entity = level.getBlockEntity(pos);
        if (!(entity instanceof final EssenceCauldronBlockEntity cauldron)) {
            return InteractionResult.FAIL;
        }
        final EssenceCauldronBlockEntity.Tank tank = cauldron.getFluidTank();
        // fill or empty a bucket
        if (itemStack.getItem() instanceof final BucketItem bucket) {
            if (Fluids.EMPTY.isSame(bucket.getContent())) {
                final Fluid fluid = tank.drain(false, true);
                if (!fluid.isSame(Fluids.EMPTY)) {
                    itemStack.consume(1, player);
                    player.getInventory().add(new ItemStack(fluid.getBucket()));
                    return InteractionResult.SUCCESS;
                }
            }
            else {
                if (tank.fill(bucket.getContent(), false, true) == ModFluids.BUCKET_AMOUNT) {
                    itemStack.consume(1, player);
                    player.getInventory().add(new ItemStack(Items.BUCKET));
                }
            }
        }
        // fill or empty a bottle
        if (itemStack.getItem() instanceof final BottleItem bottle) {
            // this is unlikely to happen because *I* don't register an empty fluid bottle
            if (Fluids.EMPTY.isSame(bottle.getContent())) {
                final Fluid fluid = tank.drain(true, false);
                if (fluid instanceof final FluidWithBottle fluidWithBottle) {
                    tank.drain(false, false);
                    itemStack.consume(1, player);
                    player.getInventory().add(new ItemStack(fluidWithBottle.getBottle()));
                    return InteractionResult.SUCCESS;
                }
            }
            // this is the regular case
            else {
                if (tank.fill(bottle.getContent(), false, false) == ModFluids.BOTTLE_AMOUNT) {
                    itemStack.consume(1, player);
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                    return InteractionResult.SUCCESS;
                }
            }
        }
        // fill an empty glass bottle
        if (itemStack.getItem() == Items.GLASS_BOTTLE) {
            final Fluid fluid = tank.drain(true, false);
            // regular case
            if (fluid instanceof final FluidWithBottle fluidWithBottle) {
                tank.drain(false, false);
                itemStack.consume(1, player);
                player.getInventory().add(new ItemStack(fluidWithBottle.getBottle()));
                return InteractionResult.SUCCESS;
            }
            // special case of water
            if (fluid.isSame(Fluids.WATER)) {
                tank.drain(false, false);
                itemStack.consume(1, player);
                player.getInventory().add(PotionContents.createItemStack(Items.POTION, Potions.WATER));
                return InteractionResult.SUCCESS;
            }
        }
        // empty a water bottle
        if (itemStack.getItem() == Items.POTION && itemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).is(Potions.WATER)) {
            if (tank.fill(Fluids.WATER, false, false) == ModFluids.BOTTLE_AMOUNT) {
                itemStack.consume(1, player);
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                return InteractionResult.SUCCESS;
            }
        }
        if (itemStack.getItem() != Items.STICK) {
            if (!itemStack.isEmpty() && cauldron.storeItem(itemStack.copyWithCount(1))) {
                itemStack.consume(1, player);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
        // TODO stick logic
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        return new EssenceCauldronBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.ESSENCE_CAULDRON.value(), EssenceCauldronBlockEntity::tick);
    }

}
