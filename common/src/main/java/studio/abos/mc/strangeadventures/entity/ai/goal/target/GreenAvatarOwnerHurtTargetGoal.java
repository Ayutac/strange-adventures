package studio.abos.mc.strangeadventures.entity.ai.goal.target;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import studio.abos.mc.strangeadventures.entity.GreenAvatarCloneEntity;

import java.util.EnumSet;

public class GreenAvatarOwnerHurtTargetGoal extends TargetGoal {
    
    private final GreenAvatarCloneEntity tameAnimal;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public GreenAvatarOwnerHurtTargetGoal(final GreenAvatarCloneEntity tameAnimal) {
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
                ownerLastHurt = owner.getLastHurtMob();
                int ts = owner.getLastHurtMobTimestamp();
                return ts != timestamp && canAttack(ownerLastHurt, TargetingConditions.DEFAULT) && tameAnimal.wantsToAttack(ownerLastHurt, owner);
            }
        } else {
            return false;
        }
    }

    public void start() {
        mob.setTarget(ownerLastHurt);
        LivingEntity owner = tameAnimal.getOwner();
        if (owner != null) {
            timestamp = owner.getLastHurtMobTimestamp();
        }
        super.start();
    }
    
}
