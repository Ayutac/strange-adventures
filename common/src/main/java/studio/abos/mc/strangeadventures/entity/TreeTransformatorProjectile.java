package studio.abos.mc.strangeadventures.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import studio.abos.mc.strangeadventures.item.ModItems;

public class TreeTransformatorProjectile extends ThrowableItemProjectile {

    public TreeTransformatorProjectile(final EntityType<TreeTransformatorProjectile> type, final Level level) {
        super(type, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TREE_TRANSFORMATOR.asItem();
    }

}
