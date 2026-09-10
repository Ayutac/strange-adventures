package studio.abos.mc.strangeadventures.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import studio.abos.mc.strangeadventures.api.BiomeTree;

public class TreeTransformatorItem extends Item {

    public TreeTransformatorItem(final Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {
        final Level level = context.getLevel();
        if (level.isClientSide()) {
            return super.useOn(context);
        }
        // FIXME just experimental
        final BlockPos target = context.getClickedPos();
        BiomeTree.plant((ServerLevel)level, target);
        return InteractionResult.SUCCESS;
    }
}
