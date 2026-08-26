package studio.abos.mc.strangeadventures.recipe;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;

public class EssenceCauldronRecipeInput extends ItemFluidRecipeInput {

    public EssenceCauldronRecipeInput(final Holder<Item>[] items, final Holder<Fluid>[] fluids) {
        super(items, fluids);
        if (items.length > EssenceCauldronBlockEntity.MAX_ITEMS) {
            throw new IllegalArgumentException("Too many items for a cauldron recipe!");
        }
        if (fluids.length > EssenceCauldronBlockEntity.MAX_FLUIDS) {
            throw new IllegalArgumentException("Too many fluids for a cauldron recipe!");
        }
    }

}
