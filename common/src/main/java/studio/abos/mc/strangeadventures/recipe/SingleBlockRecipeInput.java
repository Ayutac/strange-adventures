package studio.abos.mc.strangeadventures.recipe;

import lombok.Getter;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@Getter
public class SingleBlockRecipeInput implements RecipeInput {

    protected final Holder<Block> input;

    public SingleBlockRecipeInput(final Holder<Block> input) {
        this.input = input;
    }

    public SingleBlockRecipeInput(Block input) {
        this(BuiltInRegistries.BLOCK.wrapAsHolder(input));
    }

    public SingleBlockRecipeInput(BlockState input) {
        this(input.getBlock());
    }

    @Override
    public ItemStack getItem(final int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
