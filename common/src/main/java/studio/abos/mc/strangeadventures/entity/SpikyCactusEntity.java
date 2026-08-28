package studio.abos.mc.strangeadventures.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.targetingmode.ModTargetingModes;

import java.util.List;

public class SpikyCactusEntity extends AbstractPlantEntity {

    protected int ticks;

    public SpikyCactusEntity(final EntityType<SpikyCactusEntity> type, final Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (++ticks % 20 == 0) {
            final List<LivingEntity> targets = ModTargetingModes.NORTH.value().target(level().getEntitiesOfClass(LivingEntity.class, AABB.ofSize(position(), 128d, 128d, 128d)));
            StrangeAdventures.logger.info("Targets: {}", targets.size());
            if (!targets.isEmpty()) {
                StrangeAdventures.logger.info("First: {}", targets.getFirst().getType().getDescriptionId());
            }
            ticks = 0;
        }
    }
}
