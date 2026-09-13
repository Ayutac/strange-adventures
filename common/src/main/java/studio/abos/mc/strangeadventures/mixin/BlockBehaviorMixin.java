package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.tag.ModBlockTags;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviorMixin {

    @Inject(method = "getCollisionShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;", at = @At("RETURN"), cancellable = true)
    protected void strangeadventures$walkThroughTrees(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context, final CallbackInfoReturnable<VoxelShape> cir) {
        if (context instanceof final EntityCollisionContext ecc &&
                ecc.getEntity() instanceof final ServerPlayer player &&
                (!player.getOnPos().equals(pos) || player.isCrouching()) && // do only let player fall through blocks if crouching
                cir.getReturnValue() != Shapes.empty()) {
            final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
            if (lookup.has(player) && lookup.get(player).isActive() && state.is(ModBlockTags.IGNORED_BY_GREEN_AVATAR)) {
                cir.setReturnValue(Shapes.empty());
            }
        }
    }

}
