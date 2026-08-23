package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.fluid.AbstractSapFluid;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.item.ModItems;
import studio.abos.mc.strangeadventures.tag.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new AbstractRecipeProvider(registryLookup, exporter) {

            @Override
            public void buildRecipes() {
                sapRecipes();
                cookingRecipes();
                miscRecipes();
                sapSipperRecipes();
            }

            private void sapRecipes() {
                sapRecipes((AbstractSapFluid) ModFluids.ACACIA_SAP_STILL.value());
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
                        .requires(sap.getBottle(), 3)
                        .unlockedBy("has_bottle", has(sap.getBottle()))
                        .save(exporter);
                shapeless(RecipeCategory.MISC, sap.getBottle(), 3)
                        .requires(sap.getBucket())
                        .requires(Items.GLASS_BOTTLE, 3)
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

            private void cookingRecipes() {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.GREEN_CACTUS.asItem()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.DYE.green(), 1.0F, 200)
                        .unlockedBy("has_green_cactus", has(ModBlocks.GREEN_CACTUS))
                        .save(exporter);
            }

            private void miscRecipes() {
                shapeless(RecipeCategory.MISC, ModBlocks.GREEN_CACTUS)
                        .requires(Items.CACTUS)
                        .requires(ModItems.GREEN_SAFEGUARD)
                        .unlockedBy("has_cactus", has(Items.CACTUS))
                        .save(exporter);
                shapeless(RecipeCategory.MISC, ModBlocks.GREEN_FARMLAND)
                        .requires(Items.FARMLAND)
                        .requires(ModItems.GREEN_SAFEGUARD)
                        .unlockedBy("has_farmland", has(Items.FARMLAND))
                        .save(exporter);
            }

            private void sapSipperRecipes() {
                sapSipper(ModFluids.ACACIA_SAP_STILL)
                        .requires(ModBlockTags.MAKES_ACACIA_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.BIRCH_SAP_STILL)
                        .requires(ModBlockTags.MAKES_BIRCH_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.CACTUS_SAP_STILL)
                        .requires(ModBlockTags.MAKES_CACTUS_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.CHERRY_SAP_STILL)
                        .requires(ModBlockTags.MAKES_CHERRY_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.CHORUS_SAP_STILL)
                        .requires(ModBlockTags.MAKES_CHORUS_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.CRIMSON_SAP_STILL)
                        .requires(ModBlockTags.MAKES_CRIMSON_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.JUNGLE_SAP_STILL)
                        .requires(ModBlockTags.MAKES_JUNGLE_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.MANGROVE_SAP_STILL)
                        .requires(ModBlockTags.MAKES_MANGROVE_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.OAK_SAP_STILL)
                        .requires(ModBlockTags.MAKES_OAK_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.SPRUCE_SAP_STILL)
                        .requires(ModBlockTags.MAKES_SPRUCE_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
                sapSipper(ModFluids.WARPED_SAP_STILL)
                        .requires(ModBlockTags.MAKES_WARPED_SAP)
                        .ticks(10)
                        .amount(2)
                        .unlockedBy("has_sipper", has(ModBlocks.SAP_SIPPER))
                        .save(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return StrangeAdventures.MOD_ID;
    }

    public static abstract class AbstractRecipeProvider extends RecipeProvider {

        protected final HolderGetter<Block> blocks;

        public AbstractRecipeProvider(final HolderLookup.Provider registryLookup, final RecipeOutput exporter) {
            super(registryLookup, exporter);
            blocks = registries.lookupOrThrow(Registries.BLOCK);
        }

        protected SapSipperRecipeBuilder sapSipper(final Holder<Fluid> sapResult) {
            return new SapSipperRecipeBuilder(blocks, sapResult);
        }
    }
}
