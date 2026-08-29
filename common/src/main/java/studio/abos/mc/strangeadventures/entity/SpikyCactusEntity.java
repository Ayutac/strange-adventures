package studio.abos.mc.strangeadventures.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import studio.abos.mc.strangeadventures.targetingmode.ModTargetingModes;
import studio.abos.mc.strangeadventures.targetingmode.TargetingMode;
import studio.abos.mc.strangeadventures.targetingspace.ModTargetingSpaces;
import studio.abos.mc.strangeadventures.targetingspace.TargetingSpace;

import java.util.List;
import java.util.function.Predicate;

public class SpikyCactusEntity extends AbstractPlantEntity implements AutonomousAttacker {

    protected int ticks;

    public SpikyCactusEntity(final EntityType<SpikyCactusEntity> type, final Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (++ticks % attackInterval() == 0) {
            final List<LivingEntity> targets = prepareAttack();
            /*StrangeAdventures.logger.info("Targets: {}", targets.size());
            if (!targets.isEmpty()) {
                StrangeAdventures.logger.info("First: {}", targets.getFirst().getType().getDescriptionId());
            }*/
            attack(targets);
            ticks = 0;
        }
    }

    @Override
    public float getHorizontalRange() {
        return 5f;
    }

    @Override
    public float getVerticalRange() {
        return 5f;
    }

    @Override
    public int attackInterval() {
        return 20;
    }

    @Override
    public Holder<TargetingMode> getTargetingMode() {
        return ModTargetingModes.ALL;
    }

    @Override
    public Holder<TargetingSpace> getTargetingSpace() {
        return ModTargetingSpaces.ELLIPSOID;
    }

    @Override
    public DamageSource getDamageSource() {
        return level().damageSources().mobProjectile(this, this);
    }

    @Override
    public float getDamagePerAttack() {
        return 1f;
    }

    @Override
    public Entity attacker() {
        return this;
    }

    @Override
    public Predicate<Entity> validTargets() {
        return entity -> entity != owner() && hasLineOfSight(entity);
    }

}
