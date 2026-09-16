package studio.abos.mc.strangeadventures.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.tag.ModBlockTags;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviorMixin {

    @ModifyReturnValue(method = "getCollisionShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;", at = @At("RETURN"))
    protected VoxelShape strangeadventures$walkThroughTrees(final VoxelShape original, final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        if (context instanceof final EntityCollisionContext ecc &&
                ecc.getEntity() instanceof final Player player &&
                original != Shapes.empty()) {
            if (state.is(ModBlockTags.IGNORED_BY_GREEN_AVATAR)) {
                if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
                    if ((!player.getOnPos().equals(pos) || player.isCrouching())) { // do only let player fall through blocks if crouching
                        return Shapes.empty();
                    }
                    else if (player.getOnPos().equals(pos) && player.getY() - player.getOnPos().getY() > 0.9375) {
                        player.setPos(player.getX(), Math.ceil(player.getY()), player.getZ());
                    }
                }
            }
        }
        return original;
    }

}
