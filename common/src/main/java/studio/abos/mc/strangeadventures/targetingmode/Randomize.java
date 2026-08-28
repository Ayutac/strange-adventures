package studio.abos.mc.strangeadventures.targetingmode;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class Randomize implements TargetingMode {

    protected Random random;

    public Randomize(final Random random) {
        this.random = random;
    }

    @Override
    public List<LivingEntity> target(final Vec3 targeterPosition, final List<LivingEntity> possibleTargets) {
        return List.of(possibleTargets.get(random.nextInt(possibleTargets.size())));
    }

}
