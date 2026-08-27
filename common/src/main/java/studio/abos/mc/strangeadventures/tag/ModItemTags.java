package studio.abos.mc.strangeadventures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModItemTags {

    public static final TagKey<Item> WEIR_LOGS = TagKey.create(Registries.ITEM, StrangeAdventures.id("weir_logs"));

    public static final TagKey<Item> SEEDS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "seeds"));

}
