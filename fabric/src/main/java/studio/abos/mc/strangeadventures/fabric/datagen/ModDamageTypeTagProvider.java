package studio.abos.mc.strangeadventures.fabric.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.world.damagesource.DamageTypes;
import studio.abos.mc.strangeadventures.tag.ModDamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends DamageTypeTagsProvider {

    public ModDamageTypeTagProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(final HolderLookup.Provider arg) {
        tag(ModDamageTypeTags.GREEN_AVATAR_IMMUNITIES).add(DamageTypes.IN_WALL, DamageTypes.CACTUS, DamageTypes.DROWN);
    }

}
