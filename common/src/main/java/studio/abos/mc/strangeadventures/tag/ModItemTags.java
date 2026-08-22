package studio.abos.mc.strangeadventures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModItemTags {
    public static final TagKey<Item> YOUR_TAG = TagKey.create(Registries.ITEM, StrangeAdventures.id("your_tag"));
}
