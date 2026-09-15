package studio.abos.mc.strangeadventures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModDamageTypeTags {

    public static final TagKey<DamageType> GREEN_AVATAR_IMMUNITIES = TagKey.create(Registries.DAMAGE_TYPE, StrangeAdventures.id("green_avatar_immunities"));

}
