package studio.abos.mc.strangeadventures.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.targetingmode.TargetingMode;
import studio.abos.mc.strangeadventures.targetingspace.TargetingSpace;

import java.util.List;
import java.util.function.Predicate;

public interface AutonomousAttacker {

    float getHorizontalRange();

    float getVerticalRange();

    int attackInterval();

    Level level();

    Vec3 position();

    @Nullable
    default Entity attacker() {
        return null;
    }

    Holder<TargetingMode> getTargetingMode();

    Holder<TargetingSpace> getTargetingSpace();

    /**
     * What can be attacked by this attacker. Creative Players, Spectators and dead entities will be sorted out automatically.
     */
    Predicate<Entity> validTargets();

    default List<LivingEntity> prepareAttack() {
        final List<LivingEntity> entities = level().getEntities(attacker(), AABB.ofSize(position(), 2*getHorizontalRange(), 2*getVerticalRange(), 2*getHorizontalRange()),
                        validTargets().and(EntitySelector.LIVING_ENTITY_STILL_ALIVE).and(EntitySelector.NO_CREATIVE_OR_SPECTATOR)).stream()
                .map(LivingEntity.class::cast)
                .filter(entity -> getTargetingSpace().value().inRange(position(), getHorizontalRange(), getVerticalRange(), entity))
                .toList();
        return getTargetingMode().value().target(position(), entities);
    }

    void attack(final List<Entity> targets);
}
