package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import studio.abos.mc.strangeadventures.StrangeAdventures;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
//                shaped(RecipeCategory.DECORATIONS, ModBlocks.GREEN_FLOWER)
//                        .pattern("DDD")
//                        .pattern("SSS")
//                        .pattern("DDD")
//                        .define('D', Items.DIAMOND)
//                        .define('S', Items.STICK)
//                        .unlockedBy("has_diamond", has(Items.DIAMOND))
//                        .save(exporter);

//                shapeless(RecipeCategory.DECORATIONS, ModItems.GREEN_FLOWER)
//                        .requires(Items.DIAMOND)
//                        .requires(Items.BONE_MEAL)
//                        .unlockedBy("has_bone_meal", has(Items.BONE_MEAL))
//                        .save(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return StrangeAdventures.MOD_ID;
    }
}
