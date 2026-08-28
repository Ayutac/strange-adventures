package studio.abos.mc.strangeadventures.targetingmode;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class All implements TargetingMode {

    @Override
    public List<LivingEntity> target(final Vec3 targeterPosition, final List<LivingEntity> possibleTargets) {
        return List.copyOf(possibleTargets);
    }

}
