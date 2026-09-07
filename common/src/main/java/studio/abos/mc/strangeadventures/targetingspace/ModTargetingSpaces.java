package studio.abos.mc.strangeadventures.targetingspace;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;

public final class ModTargetingSpaces {

    public static Holder<TargetingSpace> BOX;
    public static Holder<TargetingSpace> CYLINDER;
    public static Holder<TargetingSpace> ELLIPSOID;
    public static Holder<TargetingSpace> TOP_BOX;

    public static void initialize(BalmRegistrar.Scoped<TargetingSpace> targetingSpaces) {
        BOX = targetingSpaces.register("box", _ -> new Box());
        CYLINDER = targetingSpaces.register("cylinder", _ -> new Cylinder());
        ELLIPSOID = targetingSpaces.register("ellipsoid", _ -> new Ellipsoid());
        TOP_BOX = targetingSpaces.register("top_box", _ -> new TopBox());
    }

}
