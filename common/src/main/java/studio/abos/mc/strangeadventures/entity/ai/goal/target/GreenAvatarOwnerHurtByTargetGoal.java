package studio.abos.mc.strangeadventures.entity.ai.goal.target;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import studio.abos.mc.strangeadventures.entity.GreenAvatarCloneEntity;

import java.util.EnumSet;

public class GreenAvatarOwnerHurtByTargetGoal extends TargetGoal {
    private final GreenAvatarCloneEntity tameAnimal;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public GreenAvatarOwnerHurtByTargetGoal(final GreenAvatarCloneEntity tameAnimal) {
        super(tameAnimal, false);
        this.tameAnimal = tameAnimal;
        setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canUse() {
        if (tameAnimal.getOwner() != null) {
            LivingEntity owner = tameAnimal.getOwner();
            if (owner == null) {
                return false;
            } else {
                DamageSource lastDamageSource = owner.getLastDamageSource(100);
                if (lastDamageSource != null /*&& !lastDamageSource.is(DamageTypeTags.NO_WOLF_RETALIATION)*/) {
                    ownerLastHurtBy = owner.getLastHurtByMob();
                    int ts = owner.getLastHurtByMobTimestamp();
                    return ts != timestamp && canAttack(ownerLastHurtBy, TargetingConditions.DEFAULT) && tameAnimal.wantsToAttack(ownerLastHurtBy, owner);
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void start() {
        mob.setTarget(ownerLastHurtBy);
        final LivingEntity owner = tameAnimal.getOwner();
        if (owner != null) {
            timestamp = owner.getLastHurtByMobTimestamp();
        }
        super.start();
    }

}
