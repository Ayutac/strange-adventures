package studio.abos.mc.strangeadventures.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import studio.abos.mc.strangeadventures.mixin.StemBlockAccessor;

import java.util.Optional;

public class LivingSapBottleItem extends Item {

    public LivingSapBottleItem(final Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {
        final Level level = context.getLevel();
        final BlockPos pos = context.getClickedPos();
        final BlockState state = level.getBlockState(pos);
        if (state.is(BlockTags.CROPS)) {
            // grow pitcher plant
            if (state.getBlock() instanceof PitcherCropBlock && state.hasProperty(PitcherCropBlock.AGE) && state.getValue(PitcherCropBlock.AGE) < PitcherCropBlock.MAX_AGE) {
                if (level.isClientSide()) {
                    return InteractionResult.SUCCESS;
                }
                final Player player = context.getPlayer();
                context.getItemInHand().consume(1, player);
                level.setBlockAndUpdate(pos, state.setValue(PitcherCropBlock.AGE, PitcherCropBlock.MAX_AGE));
                if (player != null) {
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }
                level.levelEvent(1505, pos, 15); // show some bonemeal particles and play the sound
                return InteractionResult.SUCCESS;
            }
            // grow torchflower
            else if (state.getBlock() instanceof TorchflowerCropBlock && state.hasProperty(TorchflowerCropBlock.AGE) && state.getValue(TorchflowerCropBlock.AGE) < TorchflowerCropBlock.MAX_AGE) {
                if (level.isClientSide()) {
                    return InteractionResult.SUCCESS;
                }
                final Player player = context.getPlayer();
                context.getItemInHand().consume(1, player);
                level.setBlockAndUpdate(pos, Blocks.TORCHFLOWER.defaultBlockState());
                if (player != null) {
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }
                level.levelEvent(1505, pos, 15); // show some bonemeal particles and play the sound
                return InteractionResult.SUCCESS;
            }
            // grow beetroot
            else if (state.getBlock() instanceof BeetrootBlock && state.hasProperty(BeetrootBlock.AGE) && state.getValue(BeetrootBlock.AGE) < BeetrootBlock.MAX_AGE) {
                if (level.isClientSide()) {
                    return InteractionResult.SUCCESS;
                }
                final Player player = context.getPlayer();
                context.getItemInHand().consume(1, player);
                level.setBlockAndUpdate(pos, state.setValue(BeetrootBlock.AGE, BeetrootBlock.MAX_AGE));
                if (player != null) {
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }
                level.levelEvent(1505, pos, 15); // show some bonemeal particles and play the sound
                return InteractionResult.SUCCESS;
            }
            // grow other stuff
            else if (state.getBlock() instanceof CropBlock && state.hasProperty(CropBlock.AGE) && state.getValue(CropBlock.AGE) < CropBlock.MAX_AGE) {
                if (level.isClientSide()) {
                    return InteractionResult.SUCCESS;
                }
                final Player player = context.getPlayer();
                context.getItemInHand().consume(1, player);
                level.setBlockAndUpdate(pos, state.setValue(CropBlock.AGE, CropBlock.MAX_AGE));
                if (player != null) {
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }
                level.levelEvent(1505, pos, 15); // show some bonemeal particles and play the sound
                return InteractionResult.SUCCESS;
            }
            // grow stems and make fruit
            else if (state.getBlock() instanceof StemBlock stem && state.hasProperty(StemBlock.AGE)) {
                // find place for fruit
                Direction direction = null;
                for (int tries = 0; tries < 12 /*arbitary number*/; tries++) {
                    direction = Direction.Plane.HORIZONTAL.getRandomDirection(level.getRandom());
                    final BlockPos relative = pos.relative(direction);
                    if (!(level.getBlockState(relative).isAir() && level.getBlockState(relative.below()).is(((StemBlockAccessor)stem).getFruitSupportBlocks()))) {
                        direction = null;
                    }
                    else {
                        break;
                    }
                }
                if (state.getValue(StemBlock.AGE) < StemBlock.MAX_AGE || direction != null) {
                    if (level.isClientSide()) {
                        return InteractionResult.SUCCESS;
                    }
                    final Player player = context.getPlayer();
                    context.getItemInHand().consume(1, player);
                    // maybe grow stem
                    level.setBlockAndUpdate(pos, state.setValue(StemBlock.AGE, StemBlock.MAX_AGE));
                    // maybe plant fruit
                    if (direction != null) {
                        Registry<Block> blocks = level.registryAccess().lookupOrThrow(Registries.BLOCK);
                        Optional<Block> fruit = blocks.getOptional(((StemBlockAccessor)stem).getFruit());
                        Optional<Block> attachedStem = blocks.getOptional(((StemBlockAccessor)stem).getAttachedStem());
                        if (fruit.isPresent() && attachedStem.isPresent()) {
                            level.setBlockAndUpdate(pos.relative(direction), fruit.get().defaultBlockState());
                            level.setBlockAndUpdate(pos, attachedStem.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                        }
                    }
                    if (player != null) {
                        player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                    }
                    level.levelEvent(1505, pos, 15); // show some bonemeal particles and play the sound
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(context);
    }
}
