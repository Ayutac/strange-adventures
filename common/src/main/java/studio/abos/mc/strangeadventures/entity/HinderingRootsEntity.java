package studio.abos.mc.strangeadventures.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import studio.abos.mc.strangeadventures.targetingmode.ModTargetingModes;
import studio.abos.mc.strangeadventures.targetingmode.TargetingMode;
import studio.abos.mc.strangeadventures.targetingspace.ModTargetingSpaces;
import studio.abos.mc.strangeadventures.targetingspace.TargetingSpace;

import java.util.List;
import java.util.function.Predicate;

public class HinderingRootsEntity extends AbstractPlantEntity implements AutonomousAttacker {

    protected HinderingRootsEntity(final EntityType<HinderingRootsEntity> type, final Level level) {
        super(type, level);
    }

    @Override
    public float getHorizontalRange() {
        return 0.5f;
    }

    @Override
    public float getVerticalRange() {
        return 0.2f;
    }

    @Override
    public int attackInterval() {
        return 10;
    }

    @Override
    public Holder<TargetingMode> getTargetingMode() {
        return ModTargetingModes.ALL;
    }

    @Override
    public Holder<TargetingSpace> getTargetingSpace() {
        return ModTargetingSpaces.BOX;
    }

    @Override
    public DamageSource getDamageSource() {
        return level().damageSources().mobProjectile(this, this);
    }

    @Override
    public boolean blockingStopsEffects() {
        return false;
    }

    @Override
    public List<MobEffectInstance> prepareEffectsForAttack() {
        return List.of(new MobEffectInstance(MobEffects.SLOWNESS, 20, 3));
    }

    @Override
    public float getDamagePerAttack() {
        return 0f;
    }

    @Override
    public Predicate<Entity> validTargets() {
        return entity -> entity != owner();
    }

}
