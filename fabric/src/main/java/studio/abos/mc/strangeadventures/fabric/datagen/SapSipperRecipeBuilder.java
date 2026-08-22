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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.recipe.SapSipperRecipe;

public class SapSipperRecipeBuilder implements RecipeBuilder {

    protected final HolderGetter<Block> blocks;
    protected final Holder<Fluid> sapResult;
    @Nullable
    protected HolderSet<Block> sapBlocks;
    protected int ticksPerSap;
    protected int amountPerSap; // in mB
    protected final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    @Nullable
    protected String group;

    public SapSipperRecipeBuilder(final HolderGetter<Block> blocks, final Holder<Fluid> sapResult) {
        this.blocks = blocks;
        this.sapResult = sapResult;
    }

    public SapSipperRecipeBuilder requires(final Holder<Block> block) {
        return requires(HolderSet.direct(block));
    }

    public SapSipperRecipeBuilder requires(final TagKey<Block> tag) {
        return requires(blocks.getOrThrow(tag));
    }

    public SapSipperRecipeBuilder requires(final HolderSet<Block> blocks) {
        sapBlocks = blocks;
        return this;
    }

    public SapSipperRecipeBuilder ticks(final int ticks) {
        if (ticks <= 0) {
            throw new IllegalArgumentException("ticks must be positive!");
        }
        ticksPerSap = ticks;
        return this;
    }

    public SapSipperRecipeBuilder amount(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive!");
        }
        amountPerSap = amount;
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(final String name, final Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(final @Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return ResourceKey.create(Registries.RECIPE, sapResult.unwrapKey().orElseThrow().identifier());
    }

    @Override
    public void save(final RecipeOutput output, final ResourceKey<Recipe<?>> location) {
        if (sapBlocks == null) {
            throw new IllegalStateException("Required blocks not specified!");
        }
        if (ticksPerSap <= 0) {
            throw new IllegalStateException("Ticks per sap not specified!");
        }
        if (amountPerSap <= 0) {
            throw new IllegalStateException("Amount per sap not specified!");
        }
        final SapSipperRecipe recipe = new SapSipperRecipe(sapResult, sapBlocks, ticksPerSap, amountPerSap);
        output.accept(location, recipe, this.advancementBuilder.build(output, location, RecipeCategory.MISC));
    }

}
