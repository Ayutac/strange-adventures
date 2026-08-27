package studio.abos.mc.strangeadventures.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class AbstractPlantEntity extends PathfinderMob {

    protected AbstractPlantEntity(final EntityType<? extends AbstractPlantEntity> type, final Level level) {
        super(type, level);
    }

}
