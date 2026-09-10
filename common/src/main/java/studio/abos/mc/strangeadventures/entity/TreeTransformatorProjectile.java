package studio.abos.mc.strangeadventures.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import studio.abos.mc.strangeadventures.api.BiomeTree;
import studio.abos.mc.strangeadventures.item.ModItems;
import studio.abos.mc.strangeadventures.tag.ModEntityTags;

public class TreeTransformatorProjectile extends ThrowableItemProjectile {

    public TreeTransformatorProjectile(final EntityType<TreeTransformatorProjectile> type, final Level level) {
        super(type, level);
    }

    public static TreeTransformatorProjectile create(final ServerLevel serverLevel, final LivingEntity living, final ItemStack itemStack) {
        return ModEntityTypes.TREE_TRANSFORMATOR_PROJECTILE.value().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
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
        if (!(entity instanceof final LivingEntity living) || living.isDeadOrDying()) {
            return;
        }
        if (living.isBlocking() || entity.is(ModEntityTags.TREE_TRANSFORMATOR_IMMUNE)) {
            discard();
            return;
        }
        final float healthTreshold = living.getMaxHealth() / 2;
        if (living.getHealth() <= healthTreshold) {
            // FIXME custom damage type
            living.hurtServer((ServerLevel)level(), level().damageSources().mobProjectile(this, null), healthTreshold);
            BiomeTree.plant((ServerLevel)level(), living.blockPosition());
            discard();
        }
    }

    @Override
    protected void onHitBlock(final BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        discard();
    }

}
