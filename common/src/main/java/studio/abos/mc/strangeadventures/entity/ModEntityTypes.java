package studio.abos.mc.strangeadventures.entity;

import net.blay09.mods.balm.world.entity.BalmEntityTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;

public final class ModEntityTypes {

    public static Holder<EntityType<HinderingRootsEntity>> HINDERING_ROOTS;
    public static Holder<EntityType<SpikyCactusEntity>> SPIKY_CACTUS;

    public static void initialize(final BalmEntityTypeRegistrar entityTypes) {
        HINDERING_ROOTS = entityTypes.register("hindering_roots", () ->
                        EntityType.Builder.of(HinderingRootsEntity::new, MobCategory.MISC)
                                .sized(1f, 0.2f))
                .withDefaultAttributes(PathfinderMob::createMobAttributes)
                .asHolder();
        SPIKY_CACTUS = entityTypes.register("spiky_cactus", () ->
                        EntityType.Builder.of(SpikyCactusEntity::new, MobCategory.MISC)
                                .sized(1f, 1f))
                .withDefaultAttributes(PathfinderMob::createMobAttributes)
                .asHolder();
    }

}
