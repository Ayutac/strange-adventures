package studio.abos.mc.strangeadventures.targetingmode;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Furthest implements TargetingMode {

    @Override
    public List<LivingEntity> target(final Vec3 targeterPosition, final List<LivingEntity> possibleTargets) {
        if (possibleTargets.isEmpty()) {
            return List.of();
        }
        double furthest = possibleTargets.stream()
                .map(Entity::position)
                .mapToDouble(targeterPosition::distanceToSqr)
                .max()
                .orElseThrow(() -> new IllegalStateException("No maximum found in non empty list!"));
        return possibleTargets.stream()
                .filter(entity -> targeterPosition.distanceToSqr(entity.position()) == furthest)
                .toList();
    }
}
