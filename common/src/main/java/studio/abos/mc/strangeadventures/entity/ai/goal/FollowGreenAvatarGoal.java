package studio.abos.mc.strangeadventures.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.PathType;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.entity.GreenAvatarCloneEntity;

import java.util.EnumSet;

public class FollowGreenAvatarGoal extends Goal {

    private final GreenAvatarCloneEntity tamable;
    private @Nullable LivingEntity owner;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;

    public FollowGreenAvatarGoal(GreenAvatarCloneEntity tamable, double speedModifier, float startDistance, float stopDistance) {
        this.tamable = tamable;
        this.speedModifier = speedModifier;
        this.navigation = tamable.getNavigation();
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(tamable.getNavigation() instanceof GroundPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowGreenAvatarGoal");
        }
    }

    public boolean canUse() {
        final LivingEntity owner = tamable.getOwner();
        if (owner == null) {
            return false;
        } else if (tamable.unableToMoveToOwner()) {
            return false;
        } else if (tamable.distanceToSqr(owner) < (double)(startDistance * startDistance)) {
            return false;
        } else {
            this.owner = owner;
            return true;
        }
    }

    public boolean canContinueToUse() {
        if (navigation.isDone()) {
            return false;
        } else {
            return !tamable.unableToMoveToOwner() && !(tamable.distanceToSqr(owner) <= stopDistance * stopDistance);
        }
    }

    public void start() {
        timeToRecalcPath = 0;
        oldWaterCost = tamable.getPathfindingMalus(PathType.WATER);
        tamable.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    public void stop() {
        owner = null;
        navigation.stop();
        tamable.setPathfindingMalus(PathType.WATER, oldWaterCost);
    }

    public void tick() {
        final boolean isOwnerFarAway = tamable.shouldTryTeleportToOwner();
        if (!isOwnerFarAway) {
            tamable.getLookControl().setLookAt(owner, 10.0F, (float)tamable.getMaxHeadXRot());
        }
        if (--timeToRecalcPath <= 0) {
            timeToRecalcPath = adjustedTickDelay(10);
            if (isOwnerFarAway) {
                tamable.tryToTeleportToOwner();
            } else {
                navigation.moveTo(owner, speedModifier);
            }
        }
    }

}
