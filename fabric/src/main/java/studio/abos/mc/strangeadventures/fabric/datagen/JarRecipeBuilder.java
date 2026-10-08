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
import net.minecraft.world.level.material.FluidIds;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.JarBlockEntity;
import studio.abos.mc.strangeadventures.recipe.JarRecipe;

import java.util.LinkedList;
import java.util.List;

public class JarRecipeBuilder implements RecipeBuilder {

    protected final HolderGetter<Item> itemGetter;
    protected final HolderGetter<Fluid> fluidGetter;
    protected final Holder<Item> result;
    protected final int amount;
    protected final List<HolderSet<Item>> items = new LinkedList<>();
    protected HolderSet<Fluid> fluid;
    protected int time;
    protected final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    @Nullable
    protected String group;

    public JarRecipeBuilder(final HolderGetter<Item> itemGetter, final HolderGetter<Fluid> fluidGetter, final Holder<Item> result, final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        this.itemGetter = itemGetter;
        this.fluidGetter = fluidGetter;
        this.result = result;
        this.amount = amount;
        this.fluid = HolderSet.direct(fluidGetter.getOrThrow(FluidIds.EMPTY));
    }

    public JarRecipeBuilder requiresItem(final Holder<Item> item) {
        return requiresItem(HolderSet.direct(item));
    }

    public JarRecipeBuilder requiresItem(final TagKey<Item> tag) {
        return requiresItem(itemGetter.getOrThrow(tag));
    }

    public JarRecipeBuilder requiresItem(final HolderSet<Item> itemSet) {
        items.add(itemSet);
        return this;
    }

    public JarRecipeBuilder requiresFluid(final Holder<Fluid> fluid) {
        return requiresFluid(HolderSet.direct(fluid));
    }

    public JarRecipeBuilder requiresFluid(final TagKey<Fluid> tag) {
        return requiresFluid(fluidGetter.getOrThrow(tag));
    }

    public JarRecipeBuilder requiresFluid(final HolderSet<Fluid> fluidSet) {
        fluid = fluidSet;
        return this;
    }

    public JarRecipeBuilder requiresTime(final int time) {
        this.time = time;
        return this;
    }

    @Override
    public JarRecipeBuilder unlockedBy(final String name, final Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public JarRecipeBuilder group(final @Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return ResourceKey.create(Registries.RECIPE, result.unwrapKey().orElseThrow().identifier());
    }

    @Override
    public void save(final RecipeOutput output, final ResourceKey<Recipe<?>> location) {
        if (items.isEmpty()) {
            throw new IllegalStateException("At least one item needs to be specified!");
        }
        if (items.size() > JarBlockEntity.MAX_ITEMS) {
            throw new IllegalStateException("Too many item ingredients!");
        }
        final JarRecipe recipe = new JarRecipe(result, amount, items, fluid, time);
        output.accept(location, recipe, this.advancementBuilder.build(output, location, RecipeCategory.MISC));
    }
}
