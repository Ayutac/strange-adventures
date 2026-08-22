package studio.abos.mc.strangeadventures.recipe;

import lombok.Getter;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.Block;

@Getter
public class SingleBlockRecipeInput implements RecipeInput {

    protected final Holder<Block> input;

    public SingleBlockRecipeInput(final Holder<Block> input) {
        this.input = input;
    }

    @Override
    public ItemStack getItem(final int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }
}
