package studio.abos.mc.strangeadventures.targetingspace;

import net.minecraft.world.phys.Vec3;

public class Cylinder implements TargetingSpace {

    @Override
    public boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final Vec3 positionToCheck) {
        final Vec3 centered = positionToCheck.subtract(positionToCheck);
        return centered.x() * centered.x() + centered.z() * centered.z() <= horizontalRange * horizontalRange &&
                verticalRange >= centered.y() && -verticalRange <= centered.y();
    }

}
