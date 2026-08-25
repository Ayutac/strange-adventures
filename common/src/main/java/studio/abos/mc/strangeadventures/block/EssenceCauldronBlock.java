package studio.abos.mc.strangeadventures.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Util;
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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;

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
    public BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        return new EssenceCauldronBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.ESSENCE_CAULDRON.value(), EssenceCauldronBlockEntity::tick);
    }

}
