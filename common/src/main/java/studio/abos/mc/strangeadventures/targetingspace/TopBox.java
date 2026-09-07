package studio.abos.mc.strangeadventures.targetingspace;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TopBox implements TargetingSpace {

    @Override
    public boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final Vec3 positionToCheck) {
        return positionToCheck.x() >= center.x() - horizontalRange && positionToCheck.x() <= center.x() + horizontalRange &&
                positionToCheck.y() >= center.y() && positionToCheck.y() <= center.y() + 2*verticalRange &&
                positionToCheck.z() >= center.z() - horizontalRange && positionToCheck.z() <= center.z() + horizontalRange;
    }

    @Override
    public AABB boundingBox(final Vec3 center, final float horizontalRange, final float verticalRange) {
        return new AABB(center.x - horizontalRange, center.y, center.z - horizontalRange,
                center.x + horizontalRange, center.y + 2 * verticalRange, center.z + horizontalRange);
    }
}
