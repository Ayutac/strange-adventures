package studio.abos.mc.strangeadventures.targetingmode;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;

public final class ModTargetingModes {

    public static Holder<TargetingMode> ALL;
    public static Holder<TargetingMode> NORTH;
    public static Holder<TargetingMode> EAST;
    public static Holder<TargetingMode> SOUTH;
    public static Holder<TargetingMode> WEST;
    public static Holder<TargetingMode> UP;
    public static Holder<TargetingMode> DOWN;
    public static Holder<TargetingMode> NEAREST;
    public static Holder<TargetingMode> FURTHEST;

    public static void initialize(BalmRegistrar.Scoped<TargetingMode> targetingModes) {
        ALL = targetingModes.register("all", _ -> new All());
        NORTH = targetingModes.register("north", _ -> new FurthestAlong(Direction.NORTH.getUnitVec3()));
        EAST = targetingModes.register("east", _ -> new FurthestAlong(Direction.EAST.getUnitVec3()));
        SOUTH = targetingModes.register("south", _ -> new FurthestAlong(Direction.SOUTH.getUnitVec3()));
        WEST = targetingModes.register("west", _ -> new FurthestAlong(Direction.WEST.getUnitVec3()));
        UP = targetingModes.register("up", _ -> new FurthestAlong(Direction.UP.getUnitVec3()));
        DOWN = targetingModes.register("down", _ -> new FurthestAlong(Direction.DOWN.getUnitVec3()));
        NEAREST = targetingModes.register("nearest", _ -> new Nearest());
        FURTHEST = targetingModes.register("furthest", _ -> new Furthest());
    }

}
