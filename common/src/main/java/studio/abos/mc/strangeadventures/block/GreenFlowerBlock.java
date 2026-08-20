package studio.abos.mc.strangeadventures.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

public class GreenFlowerBlock extends FlowerBlock {

    public GreenFlowerBlock(final Properties properties) {
        super(MobEffects.NAUSEA, 3, properties);
    }

    @Override
    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }
        if (level.dimension() == Level.OVERWORLD) {
            StrangeAdventuresApi.INTERNAL_METHODS.tpOverworldToGreen(player, pos);
            return InteractionResult.SUCCESS;
        }
        if (level.dimension() == StrangeAdventuresApi.GREEN_DIMENSION) {
            StrangeAdventuresApi.INTERNAL_METHODS.tpGreenToOverworld(player, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        return useWithoutItem(state, level, pos, player, hitResult);
    }
}
