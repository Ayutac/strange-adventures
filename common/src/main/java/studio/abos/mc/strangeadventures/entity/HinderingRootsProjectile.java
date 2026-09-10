package studio.abos.mc.strangeadventures.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.item.ModItems;

public class HinderingRootsProjectile extends ThrowableItemProjectile {

    public HinderingRootsProjectile(final EntityType<HinderingRootsProjectile> type, final Level level) {
        super(type, level);
    }

    @Nullable
    public static HinderingRootsProjectile create(final ServerLevel serverLevel, final LivingEntity living, final ItemStack itemStack) {
        final var projectile = ModEntityTypes.HINDERING_ROOTS_PROJECTILE.value().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (projectile != null) {
            projectile.setOwner(living);
            projectile.setPos(living.getEyePosition());
        }
        return projectile;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TREE_TRANSFORMATOR.asItem();
    }

    @Override
    protected void onHitEntity(final EntityHitResult hitResult) {
        if (level().isClientSide()) {
            return;
        }
        final Entity entity = hitResult.getEntity();
        if (!(entity instanceof final LivingEntity living) || living.isDeadOrDying() || living == getOwner()) {
            return;
        }
        if (living instanceof final Player player && (player.isCreative() || player.isSpectator())) {
            return;
        }
        final var roots = ModEntityTypes.HINDERING_ROOTS.value().create(level(), EntitySpawnReason.CONVERSION);
        if (roots != null) {
            final BlockPos spawnPosition = living.blockPosition();
            roots.setPos(spawnPosition.getX() + 0.5, spawnPosition.getY(), spawnPosition.getZ() + 0.5);
            level().addFreshEntity(roots);
        }
        discard();
    }

    @Override
    protected void onHitBlock(final BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        discard();
    }

}
