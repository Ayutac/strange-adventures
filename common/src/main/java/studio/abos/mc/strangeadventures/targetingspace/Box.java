package studio.abos.mc.strangeadventures.targetingspace;

import net.minecraft.world.phys.Vec3;

public class Box implements TargetingSpace {

    @Override
    public boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final Vec3 positionToCheck) {
        return positionToCheck.x() >= center.x() - horizontalRange && positionToCheck.x() <= center.x() + horizontalRange &&
                positionToCheck.y() >= center.y() - verticalRange && positionToCheck.y() <= center.y() + verticalRange &&
                positionToCheck.z() >= center.z() - horizontalRange && positionToCheck.z() <= center.z() + horizontalRange;
    }

}
