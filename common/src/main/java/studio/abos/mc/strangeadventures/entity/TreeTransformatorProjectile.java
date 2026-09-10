package studio.abos.mc.strangeadventures.entity;

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
import studio.abos.mc.strangeadventures.api.BiomeTree;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.item.ModItems;
import studio.abos.mc.strangeadventures.tag.ModEntityTags;

public class TreeTransformatorProjectile extends ThrowableItemProjectile {

    public TreeTransformatorProjectile(final EntityType<TreeTransformatorProjectile> type, final Level level) {
        super(type, level);
    }

    @Nullable
    public static TreeTransformatorProjectile create(final ServerLevel serverLevel, final LivingEntity living, final ItemStack itemStack) {
        final var projectile = ModEntityTypes.TREE_TRANSFORMATOR_PROJECTILE.value().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
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
        if (living.isBlocking() || entity.is(ModEntityTags.TREE_TRANSFORMATOR_IMMUNE)) {
            discard();
            return;
        }
        final float healthThreshold = living.getMaxHealth() / 2;
        if (living.getHealth() <= healthThreshold) {
            living.hurtServer((ServerLevel)level(), StrangeAdventuresApi.INTERNAL_METHODS.createTreeTransformatorDamageSource(level(), getOwner()), healthThreshold);
            BiomeTree.plant((ServerLevel)level(), living.blockPosition());
        }
        discard();
    }

    @Override
    protected void onHitBlock(final BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        discard();
    }

}
