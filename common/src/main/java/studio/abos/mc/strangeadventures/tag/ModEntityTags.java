package studio.abos.mc.strangeadventures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModEntityTags {

    public static final TagKey<EntityType<?>> TREE_TRANSFORMATOR_IMMUNE = TagKey.create(Registries.ENTITY_TYPE, StrangeAdventures.id("tree_transformator_immune"));

}
