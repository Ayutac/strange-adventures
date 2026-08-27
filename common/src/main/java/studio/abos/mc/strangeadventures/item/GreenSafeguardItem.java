package studio.abos.mc.strangeadventures.item;

import net.minecraft.core.BlockPos;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.tag.ModBlockTags;

public class GreenSafeguardItem extends Item {

    public GreenSafeguardItem(final Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {
        final Level level = context.getLevel();
        if (level.isClientSide()) {
            return super.useOn(context);
        }
        BlockPos target = context.getClickedPos();
        if (level.getBlockState(target).is(BlockTags.CROPS)) {
            target = target.below();
        }
        // safeguard farmland
        BlockState oldState = level.getBlockState(target);
        if (oldState.is(ModBlockTags.GREEN_FARMLAND_CONVERTIBLE)) {
            BlockState newState = ModBlocks.GREEN_FARMLAND.defaultBlockState();
            if (oldState.hasProperty(FarmlandBlock.MOISTURE)) {
                newState = newState.setValue(FarmlandBlock.MOISTURE, oldState.getValue(FarmlandBlock.MOISTURE));
            }
            level.setBlockAndUpdate(target, newState);
            context.getItemInHand().consume(1, context.getPlayer());
            return InteractionResult.SUCCESS;
        }
        // safeguard cacti
        if (oldState.is(BlockItemIds.CACTUS.block())) {
            // get the base cactus
            BlockPos below;
            while (level.getBlockState(below = target.below()).is(BlockItemIds.CACTUS.block())) {
                target = below;
            }
            oldState = level.getBlockState(target);
            BlockState newState = ModBlocks.GREEN_CACTUS.defaultBlockState();
            if (oldState.hasProperty(CactusBlock.AGE)) {
                newState = newState.setValue(CactusBlock.AGE, oldState.getValue(CactusBlock.AGE));
            }
            level.setBlockAndUpdate(target, newState);
            context.getItemInHand().consume(1, context.getPlayer());
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
