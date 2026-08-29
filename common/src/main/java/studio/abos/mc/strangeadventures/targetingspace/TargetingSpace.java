package studio.abos.mc.strangeadventures.targetingspace;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public interface TargetingSpace {

    boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final Vec3 positionToCheck);

    default boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final AABB boxToCheck) {
        final Vec3[] boxCoords = new Vec3[]{
                new Vec3(boxToCheck.minX, boxToCheck.minY, boxToCheck.minZ),
                new Vec3(boxToCheck.minX, boxToCheck.minY, boxToCheck.maxZ),
                new Vec3(boxToCheck.minX, boxToCheck.maxY, boxToCheck.minZ),
                new Vec3(boxToCheck.minX, boxToCheck.maxY, boxToCheck.maxZ),
                new Vec3(boxToCheck.maxX, boxToCheck.minY, boxToCheck.minZ),
                new Vec3(boxToCheck.maxX, boxToCheck.minY, boxToCheck.maxZ),
                new Vec3(boxToCheck.maxX, boxToCheck.maxY, boxToCheck.minZ),
                new Vec3(boxToCheck.maxX, boxToCheck.maxY, boxToCheck.maxZ)
        };
        for (final Vec3 vertex : boxCoords) {
            if (inRange(center, horizontalRange, verticalRange, vertex)) {
                return true;
            }
        }
        return false;
    }

    default boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final LivingEntity entityToCheck) {
        return inRange(center, horizontalRange, verticalRange, entityToCheck.getBoundingBox());
    }

}
