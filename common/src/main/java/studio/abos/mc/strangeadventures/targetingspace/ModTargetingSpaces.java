package studio.abos.mc.strangeadventures.targetingspace;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;

public final class ModTargetingSpaces {

    public static Holder<TargetingSpace> ELLIPSOID;
    public static Holder<TargetingSpace> BOX;
    public static Holder<TargetingSpace> CYLINDER;

    public static void initialize(BalmRegistrar.Scoped<TargetingSpace> targetingSpaces) {
        ELLIPSOID = targetingSpaces.register("ellipsoid", _ -> new Ellipsoid());
        BOX = targetingSpaces.register("box", _ -> new Box());
        CYLINDER = targetingSpaces.register("cylinder", _ -> new Cylinder());
    }

}
