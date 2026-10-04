package studio.abos.mc.strangeadventures.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.entity.ai.goal.FollowGreenAvatarGoal;
import studio.abos.mc.strangeadventures.entity.ai.goal.target.GreenAvatarOwnerHurtByTargetGoal;
import studio.abos.mc.strangeadventures.entity.ai.goal.target.GreenAvatarOwnerHurtTargetGoal;

import java.util.Optional;

public class GreenAvatarCloneEntity extends PathfinderMob implements OwnableEntity {

    protected static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> DATA_OWNERUUID_ID =
            SynchedEntityData.defineId(GreenAvatarCloneEntity.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);

    public GreenAvatarCloneEntity(final EntityType<GreenAvatarCloneEntity> type, final Level level) {
        super(type, level);
    }

    @Override
    public @Nullable EntityReference<LivingEntity> getOwnerReference() {
        return (EntityReference)((Optional)this.entityData.get(DATA_OWNERUUID_ID)).orElse(null);
    }

    public void setOwnerReference(final @Nullable EntityReference<LivingEntity> owner) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(owner));
    }

    public void setOwner(final @Nullable LivingEntity owner) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(owner).map(EntityReference::of));
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_OWNERUUID_ID, Optional.empty());
    }

    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        EntityReference<LivingEntity> owner = this.getOwnerReference();
        EntityReference.store(owner, output, "Owner");
    }

    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        EntityReference<LivingEntity> owner = EntityReference.readWithOldOwnerConversion(input, "Owner", this.level());
        if (owner != null) {
            this.entityData.set(DATA_OWNERUUID_ID, Optional.of(owner));
        } else {
            this.entityData.set(DATA_OWNERUUID_ID, Optional.empty());
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4f));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1d, true));
        this.goalSelector.addGoal(6, new FollowGreenAvatarGoal(this, 1d, 10f, 2f));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1d));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8f));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new GreenAvatarOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new GreenAvatarOwnerHurtTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.MAX_HEALTH, 20d).add(Attributes.ATTACK_DAMAGE, 4d);
    }

    public boolean wantsToAttack(final LivingEntity target, final LivingEntity owner) {
        return true;
    }

    public final boolean unableToMoveToOwner() {
        return this.isPassenger() || this.mayBeLeashed() || this.getOwner() != null && this.getOwner().isSpectator();
    }

    public void tryToTeleportToOwner() {
        final LivingEntity owner = this.getOwner();
        if (owner != null) {
            this.teleportToAroundBlockPos(owner.blockPosition());
        }
    }

    public boolean shouldTryTeleportToOwner() {
        final LivingEntity owner = this.getOwner();
        return owner != null && this.distanceToSqr(this.getOwner()) >= 144d;
    }

    private void teleportToAroundBlockPos(final BlockPos targetPos) {
        for (int attempt = 0; attempt < 10; ++attempt) {
            int xd = this.random.nextIntBetweenInclusive(-3, 3);
            int zd = this.random.nextIntBetweenInclusive(-3, 3);
            if (Math.abs(xd) >= 2 || Math.abs(zd) >= 2) {
                int yd = this.random.nextIntBetweenInclusive(-1, 1);
                if (this.maybeTeleportTo(targetPos.getX() + xd, targetPos.getY() + yd, targetPos.getZ() + zd)) {
                    return;
                }
            }
        }

    }

    private boolean maybeTeleportTo(final int x, final int y, final int z) {
        if (!this.canTeleportTo(new BlockPos(x, y, z))) {
            return false;
        } else {
            this.snapTo(x + 0.5d, y, z + 0.5d, this.getYRot(), this.getXRot());
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(final BlockPos pos) {
        final PathType pathType = WalkNodeEvaluator.getPathTypeStatic(this, pos);
        if (pathType != PathType.WALKABLE) {
            return false;
        } else {
            BlockState blockStateBelow = this.level().getBlockState(pos.below());
            if (blockStateBelow.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos delta = pos.subtract(this.blockPosition());
                return this.level().noCollision(this, this.getBoundingBox().move(delta));
            }
        }
    }

}
