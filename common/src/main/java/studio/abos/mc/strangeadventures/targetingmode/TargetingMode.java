package studio.abos.mc.strangeadventures.targetingmode;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface TargetingMode {

    List<LivingEntity> target(final Vec3 targeterPosition, List<LivingEntity> possibleTargets);


}
