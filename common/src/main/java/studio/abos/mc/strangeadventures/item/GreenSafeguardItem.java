package studio.abos.mc.strangeadventures.item;

import net.minecraft.core.BlockPos;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
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
        if (level.getBlockState(target).is(ModBlockTags.GREEN_FARMLAND_CONVERTIBLE)) {
            level.setBlockAndUpdate(target, ModBlocks.GREEN_FARMLAND.defaultBlockState());
            context.getItemInHand().consume(1, context.getPlayer());
            return InteractionResult.SUCCESS;
        }
        // safeguard cacti
        if (level.getBlockState(target).is(BlockItemIds.CACTUS.block())) {
            // get the base cactus
            BlockPos below;
            while (level.getBlockState(below = target.below()).is(BlockItemIds.CACTUS.block())) {
                target = below;
            }
            level.setBlockAndUpdate(target, ModBlocks.GREEN_CACTUS.defaultBlockState());
            context.getItemInHand().consume(1, context.getPlayer());
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
