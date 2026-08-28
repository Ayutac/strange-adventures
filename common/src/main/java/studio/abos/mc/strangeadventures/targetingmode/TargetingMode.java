package studio.abos.mc.strangeadventures.targetingmode;

import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public interface TargetingMode {

    List<LivingEntity> target(List<LivingEntity> possibleTargets);


    /*NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    DOWN,
    NEAREST,
    FURTHEST,
    ALL*/

}
