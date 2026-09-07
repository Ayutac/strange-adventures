package studio.abos.mc.strangeadventures.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class AbstractPlantEntity extends PathfinderMob {

    protected int damageTicks;

    protected AbstractPlantEntity(final EntityType<? extends AbstractPlantEntity> type, final Level level) {
        super(type, level);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public boolean isOnSupportingBlock() {
        return isFallFlying();
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide() && ++damageTicks % 10 == 0 & !isOnSupportingBlock()) {
            hurtServer((ServerLevel)level(), level().damageSources().cramming(), 1f);
            damageTicks = 0;
        }
        if (isOnSupportingBlock()) {
            damageTicks = 0;
        }
    }
}
