package studio.abos.mc.strangeadventures.targetingspace;

import net.minecraft.world.phys.Vec3;

public interface TargetingSpace {

    boolean inRange(final Vec3 center, final float horizontalRange, final float verticalRange, final Vec3 positionToCheck);

}
