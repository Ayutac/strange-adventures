package studio.abos.mc.strangeadventures.targetingmode;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FurthestAlong implements TargetingMode {

    protected Vec3 direction;
    protected double directionLength;

    public FurthestAlong(final Vec3 direction) {
        if (direction.equals(Vec3.ZERO)) {
            throw new IllegalArgumentException("Direction vector cannot be zero!");
        }
        this.direction = direction;
        directionLength = direction.length();
    }

    @Override
    public List<LivingEntity> target(final Vec3 targeterPosition, final List<LivingEntity> possibleTargets) {
        if (possibleTargets.isEmpty()) {
            return List.of();
        }
        double furthestAlong = possibleTargets.stream()
                .map(Entity::position)
                .mapToDouble(this::projection)
                .max()
                .orElseThrow(() -> new IllegalStateException("No maximum found in non empty list!"));
        return possibleTargets.stream()
                .filter(entity -> projection(entity.position()) == furthestAlong)
                .toList();
    }

    protected double projection(final Vec3 pos) {
        return (pos.x() * direction.x() + pos.y() * direction.y() + pos.z() * direction.z()) / directionLength;
    }

}
