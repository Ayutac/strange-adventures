package studio.abos.mc.strangeadventures.recipe;

import net.blay09.mods.balm.world.item.crafting.BalmRecipeTypeRegistrar;
import net.blay09.mods.balm.world.item.crafting.DeferredRecipeType;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.crafting.RecipeSerializer;
import studio.abos.mc.strangeadventures.MathUtil;

import java.util.List;

public final class ModRecipeTypes {

    public static DeferredRecipeType<SingleBlockRecipeInput, SapSipperRecipe> SAP_SIPPER;
    public static DeferredRecipeType<EssenceCauldronRecipeInput, EssenceCauldronRecipe> ESSENCE_CAULDRON;

    public static void initialize(final BalmRecipeTypeRegistrar recipeTypes) {
        SAP_SIPPER = recipeTypes.register("sap_sipper", SapSipperRecipe.class)
                .withSerializer(() -> new RecipeSerializer<>(SapSipperRecipe.CODEC, SapSipperRecipe.STREAM_CODEC))
                .asDeferredRecipeType();
        ESSENCE_CAULDRON = recipeTypes.register("essence_cauldron", EssenceCauldronRecipe.class)
                .withSerializer(() -> new RecipeSerializer<>(EssenceCauldronRecipe.CODEC, EssenceCauldronRecipe.STREAM_CODEC))
                .asDeferredRecipeType();
    }

    public static <T> boolean matches(List<HolderSet<T>> expected, List<Holder<T>> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        final int[][] permutations = MathUtil.permutations(expected.size());
        p: for (final int[] permutation : permutations) {
            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).contains(actual.get(permutation[i]))) {
                    continue p;
                }
            }
            // at this point all permutated actuals have hit the expected
            return true;
        }
        return false;
    }

}
