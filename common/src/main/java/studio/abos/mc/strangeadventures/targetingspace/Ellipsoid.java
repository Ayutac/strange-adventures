package studio.abos.mc.strangeadventures.targetingspace;

import net.minecraft.world.phys.Vec3;

public class Ellipsoid implements TargetingSpace {

    @Override
    public boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final Vec3 positionToCheck) {
        final Vec3 centered = center.subtract(positionToCheck);
        final double scaledX = centered.x() / horizontalRange;
        final double scaledY = centered.y() / verticalRange;
        final double scaledZ = centered.z() / horizontalRange;
        return scaledX * scaledX + scaledY * scaledY + scaledZ * scaledZ <= 1;
    }

}
