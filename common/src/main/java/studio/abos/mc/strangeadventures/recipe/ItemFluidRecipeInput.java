package studio.abos.mc.strangeadventures.recipe;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ItemFluidRecipeInput implements RecipeInput {

    protected final Holder<Item>[] items;
    protected final Holder<Fluid>[] fluids;

    public ItemFluidRecipeInput(Holder<Item>[] items, Holder<Fluid>[] fluids) {
        if (items.length == 0 && fluids.length == 0) {
            throw new IllegalArgumentException("At least one item or fluid must be specified!");
        }
        for (final Holder<Item> item : items) {
            if (item == null || item.value() == Items.AIR) { // compiler warning is wrong
                throw new NullPointerException("item cannot be null or air!");
            }
        }
        for (final Holder<Fluid> fluid : fluids) {
            if (fluid == null || fluid.value().isSame(Fluids.EMPTY)) { // compiler warning is wrong
                throw new NullPointerException("fluid cannot be null or empty!");
            }
        }
        this.items = new Holder[items.length];
        System.arraycopy(items, 0, this.items, 0, items.length);
        this.fluids = new Holder[fluids.length];
        System.arraycopy(fluids, 0, this.fluids, 0, fluids.length);
    }

    @Override
    public ItemStack getItem(final int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return itemCount();
    }

    public List<Holder<Item>> getProperItems() {
        return Arrays.asList(items);
    }

    @Nullable
    public Holder<Item> getProperItem(final int i) {
        if (i < 0 || i >= itemCount()) {
            return null;
        }
        return items[i];
    }

    public int itemCount() {
        return items.length;
    }

    public List<Holder<Fluid>> getFluids() {
        return Arrays.asList(fluids);
    }

    @Nullable
    public Holder<Fluid> getFluid(final int i) {
        if (i < 0 || i >= fluidCount()) {
            return null;
        }
        return fluids[i];
    }

    public int fluidCount() {
        return fluids.length;
    }

}
