package studio.abos.mc.strangeadventures.recipe;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.blockentity.JarBlockEntity;

public class JarRecipeInput extends ItemFluidRecipeInput {

    public JarRecipeInput(final Holder<Item>[] items, final Holder<Fluid>[] fluids) {
        super(items, fluids);
        if (items.length > JarBlockEntity.MAX_ITEMS) {
            throw new IllegalArgumentException("Too many items for a cauldron recipe!");
        }
        if (fluids.length > 1) {
            throw new IllegalArgumentException("Too many fluids for a cauldron recipe!");
        }
    }

}
