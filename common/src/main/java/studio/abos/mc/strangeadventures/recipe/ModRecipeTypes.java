package studio.abos.mc.strangeadventures.recipe;

import net.blay09.mods.balm.world.item.crafting.BalmRecipeTypeRegistrar;
import net.blay09.mods.balm.world.item.crafting.DeferredRecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;

public final class ModRecipeTypes {

    public static DeferredRecipeType<SingleRecipeInput, SapSipperRecipe> SAP_SIPPER;

    public static void initialize(final BalmRecipeTypeRegistrar recipeTypes) {
        SAP_SIPPER = recipeTypes.register("sap_sipper", SapSipperRecipe.class)
                .withSerializer(() -> new RecipeSerializer<>(SapSipperRecipe.CODEC, SapSipperRecipe.STREAM_CODEC))
                .asDeferredRecipeType();
    }

}
