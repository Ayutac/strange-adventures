package studio.abos.mc.strangeadventures.entity;

import net.blay09.mods.balm.world.entity.BalmEntityTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;

public final class ModEntityTypes {

    public static Holder<EntityType<HinderingRootsEntity>> HINDERING_ROOTS;
    public static Holder<EntityType<SpikyCactusEntity>> SPIKY_CACTUS;

    public static Holder<EntityType<HinderingRootsProjectile>> HINDERING_ROOTS_PROJECTILE;
    public static Holder<EntityType<TreeTransformatorProjectile>> TREE_TRANSFORMATOR_PROJECTILE;

    public static void initialize(final BalmEntityTypeRegistrar entityTypes) {
        HINDERING_ROOTS = entityTypes.register("hindering_roots", () ->
                        EntityType.Builder.of(HinderingRootsEntity::new, MobCategory.MISC)
                                .sized(1f, 0.25f))
                .withDefaultAttributes(PathfinderMob::createMobAttributes)
                .asHolder();
        SPIKY_CACTUS = entityTypes.register("spiky_cactus", () ->
                        EntityType.Builder.of(SpikyCactusEntity::new, MobCategory.MISC)
                                .sized(1f, 1f))
                .withDefaultAttributes(PathfinderMob::createMobAttributes)
                .asHolder();

        HINDERING_ROOTS_PROJECTILE = entityTypes.register("hindering_roots_projectile", () ->
                EntityType.Builder.of(HinderingRootsProjectile::new, MobCategory.MISC)
                        .sized(0.5f, 0.5f))
                .asHolder();
        TREE_TRANSFORMATOR_PROJECTILE = entityTypes.register("tree_transformator_projectile", () ->
                EntityType.Builder.of(TreeTransformatorProjectile::new, MobCategory.MISC)
                        .sized(0.5f, 0.5f))
                .asHolder();
    }

}
