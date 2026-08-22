package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.fluid.AbstractSapFluid;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.mixin.RecipeProviderAccessor;

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
                sapRecipes();
                sapSipperRecipes();
            }

            private void sapRecipes() {
                sapRecipes((AbstractSapFluid)ModFluids.ACACIA_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.BIRCH_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.CACTUS_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.CHERRY_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.CHORUS_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.CRIMSON_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.JUNGLE_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.MANGROVE_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.OAK_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.SPRUCE_SAP_STILL.value());
                sapRecipes((AbstractSapFluid)ModFluids.WARPED_SAP_STILL.value());
            }

            private void sapRecipes(AbstractSapFluid sap) {
                shapeless(RecipeCategory.MISC, sap.getBucket())
                        .requires(Items.BUCKET)
                        .requires(sap.getBottle())
                        .requires(sap.getBottle())
                        .requires(sap.getBottle())
                        .unlockedBy("has_bottle", has(sap.getBottle()))
                        .save(exporter);
                shapeless(RecipeCategory.MISC, sap.getBottle(), 3)
                        .requires(sap.getBucket())
                        .requires(Items.GLASS_BOTTLE)
                        .requires(Items.GLASS_BOTTLE)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy("has_bucket", has(sap.getBucket()))
                        .save(exporter);
                String bottleId = sap.getBottle().getDescriptionId();
                bottleId = bottleId.substring(bottleId.lastIndexOf('.') + 1);
                shaped(RecipeCategory.MISC, sap.getBottle(), 3)
                        .pattern("GBG")
                        .pattern(" G ")
                        .define('B', sap.getBucket())
                        .define('G', Items.GLASS)
                        .unlockedBy("has_bucket", has(sap.getBucket()))
                        .save(exporter, bottleId + "_from_glass");
            }

            private void sapSipperRecipes() {

            }

            private SapSipperRecipeBuilder sapSipper(final Holder<Fluid> sapResult) {
                return new SapSipperRecipeBuilder(((RecipeProviderAccessor)(Object)this).getItems(), sapResult);
            }
        };
    }

    @Override
    public String getName() {
        return StrangeAdventures.MOD_ID;
    }

}
