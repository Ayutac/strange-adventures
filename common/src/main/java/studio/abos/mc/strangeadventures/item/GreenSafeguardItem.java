package studio.abos.mc.strangeadventures.item;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
        if (level.getBlockState(target).is(ModBlockTags.GREEN_FARMLAND_CONVERTIBLE)) {
            level.setBlockAndUpdate(target, ModBlocks.GREEN_FARMBLOCK.defaultBlockState());
            final Player player = context.getPlayer();
            if (player == null || !player.getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
