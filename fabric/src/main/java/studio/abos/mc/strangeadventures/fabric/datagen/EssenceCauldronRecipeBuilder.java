package studio.abos.mc.strangeadventures.fabric.datagen;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.material.Fluid;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;
import studio.abos.mc.strangeadventures.recipe.EssenceCauldronRecipe;

import java.util.LinkedList;
import java.util.List;

public class EssenceCauldronRecipeBuilder implements RecipeBuilder {

    protected final HolderGetter<Item> itemGetter;
    protected final HolderGetter<Fluid> fluidGetter;
    protected final Holder<Item> result;
    protected final int amount;
    protected final List<HolderSet<Item>> items = new LinkedList<>();
    protected final List<HolderSet<Fluid>> fluids = new LinkedList<>();
    protected final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    @Nullable
    protected String group;

    public EssenceCauldronRecipeBuilder(final HolderGetter<Item> itemGetter, final HolderGetter<Fluid> fluidGetter, final Holder<Item> result, final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        this.itemGetter = itemGetter;
        this.fluidGetter = fluidGetter;
        this.result = result;
        this.amount = amount;
    }

    public EssenceCauldronRecipeBuilder requiresItem(final Holder<Item> item) {
        return requiresItem(HolderSet.direct(item));
    }

    public EssenceCauldronRecipeBuilder requiresItem(final TagKey<Item> tag) {
        return requiresItem(itemGetter.getOrThrow(tag));
    }

    public EssenceCauldronRecipeBuilder requiresItem(final HolderSet<Item> itemSet) {
        items.add(itemSet);
        return this;
    }

    public EssenceCauldronRecipeBuilder requiresFluid(final Holder<Fluid> fluid) {
        return requiresFluid(HolderSet.direct(fluid));
    }

    public EssenceCauldronRecipeBuilder requiresFluid(final TagKey<Fluid> tag) {
        return requiresFluid(fluidGetter.getOrThrow(tag));
    }

    public EssenceCauldronRecipeBuilder requiresFluid(final HolderSet<Fluid> fluidSet) {
        fluids.add(fluidSet);
        return this;
    }

    @Override
    public EssenceCauldronRecipeBuilder unlockedBy(final String name, final Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public EssenceCauldronRecipeBuilder group(final @Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return ResourceKey.create(Registries.RECIPE, result.unwrapKey().orElseThrow().identifier());
    }

    @Override
    public void save(final RecipeOutput output, final ResourceKey<Recipe<?>> location) {
        if (items.isEmpty() && fluids.isEmpty()) {
            throw new IllegalStateException("At least one item or one fluid needs to be specified!");
        }
        if (items.size() > EssenceCauldronBlockEntity.MAX_ITEMS) {
            throw new IllegalStateException("Too many item ingredients!");
        }
        if (fluids.size() > EssenceCauldronBlockEntity.MAX_FLUIDS) {
            throw new IllegalStateException("Too many fluid ingredients!");
        }
        final EssenceCauldronRecipe recipe = new EssenceCauldronRecipe(result, amount, items, fluids);
        output.accept(location, recipe, this.advancementBuilder.build(output, location, RecipeCategory.MISC));
    }
}
