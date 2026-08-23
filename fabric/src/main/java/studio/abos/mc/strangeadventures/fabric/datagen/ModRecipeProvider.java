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
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
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
                livingSapRevivalRecipes();
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
                sapRecipes((AbstractSapFluid)ModFluids.LIVING_SAP_STILL.value());
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
                shaped(RecipeCategory.MISC, ModItems.LIVING_ROD, 4)
                        .pattern("W")
                        .pattern("W")
                        .define('W', ModBlocks.LIVING_WOOD)
                        .unlockedBy("has_living_wood", has(ModBlocks.LIVING_WOOD))
                        .save(exporter);
//                String description = ModBlocks.SLEEPING_WOOD.asItem().getDescriptionId();
//                description = description.substring(description.lastIndexOf('.') + 1);
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLEEPING_WOOD)
                        .requires(Items.ANCIENT_DEBRIS)
                        .requires(ModItems.LIVING_SAP_BUCKET, 8)
                        .unlockedBy("has_ancient_debris", has(Items.ANCIENT_DEBRIS))
                        .save(exporter);
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIVING_WOOD)
                        .requires(ModBlocks.SLEEPING_WOOD)
                        .requires(ModItems.LIVING_SAP_BUCKET, 8)
                        .unlockedBy("has_sleeping_wood", has(ModBlocks.SLEEPING_WOOD))
                        .save(exporter);
            }

            private void livingSapRevivalRecipes() {
                livingSapCoralRevivalRecipes();
                livingSapWoodRepairRecipes();
                reviveWithBottle(Items.COOKED_BEEF, Items.BEEF, false);
                reviveWithBottle(Items.COOKED_CHICKEN, Items.CHICKEN, false);
                reviveWithBottle(Items.COOKED_COD, Items.COD, true);
                reviveWithBottle(Items.COOKED_MUTTON, Items.MUTTON, false);
                reviveWithBottle(Items.COOKED_PORKCHOP, Items.PORKCHOP, false);
                reviveWithBottle(Items.COOKED_RABBIT, Items.RABBIT, false);
                reviveWithBottle(Items.COOKED_SALMON, Items.SALMON, true);
                reviveWithBottle(Items.DRIED_KELP, Items.KELP, true);
                reviveWithBottle(Items.POPPED_CHORUS_FRUIT, Items.CHORUS_FRUIT, false);
                shapeless(RecipeCategory.MISC, Items.CHORUS_FLOWER)
                        .requires(Items.CHORUS_FRUIT)
                        .requires(((AbstractSapFluid)ModFluids.CHORUS_SAP_STILL.value()).getBottle())
                        .requires(ModItems.LIVING_SAP_BOTTLE)
                        .unlockedBy("has_chorus_sap", has(((AbstractSapFluid)ModFluids.CHORUS_SAP_STILL.value()).getBottle()))
                        .save(exporter);
            }

            private void livingSapCoralRevivalRecipes() {
                reviveWithBucket(Items.DEAD_BRAIN_CORAL_BLOCK, Items.BRAIN_CORAL_BLOCK, true);
                reviveWithBucket(Items.DEAD_BUBBLE_CORAL_BLOCK, Items.BUBBLE_CORAL_BLOCK, true);
                reviveWithBucket(Items.DEAD_FIRE_CORAL_BLOCK, Items.FIRE_CORAL_BLOCK, true);
                reviveWithBucket(Items.DEAD_HORN_CORAL_BLOCK, Items.HORN_CORAL_BLOCK, true);
                reviveWithBucket(Items.DEAD_TUBE_CORAL_BLOCK, Items.TUBE_CORAL_BLOCK, true);
                reviveWithBottle(Items.DEAD_BRAIN_CORAL_FAN, Items.BRAIN_CORAL_FAN, true);
                reviveWithBottle(Items.DEAD_BUBBLE_CORAL_FAN, Items.BUBBLE_CORAL_FAN, true);
                reviveWithBottle(Items.DEAD_FIRE_CORAL_FAN, Items.FIRE_CORAL_FAN, true);
                reviveWithBottle(Items.DEAD_HORN_CORAL_FAN, Items.HORN_CORAL_FAN, true);
                reviveWithBottle(Items.DEAD_TUBE_CORAL_FAN, Items.TUBE_CORAL_FAN, true);
                reviveWithBottle(Items.DEAD_BRAIN_CORAL, Items.BRAIN_CORAL, true);
                reviveWithBottle(Items.DEAD_BUBBLE_CORAL, Items.BUBBLE_CORAL, true);
                reviveWithBottle(Items.DEAD_FIRE_CORAL, Items.FIRE_CORAL, true);
                reviveWithBottle(Items.DEAD_HORN_CORAL, Items.HORN_CORAL, true);
                reviveWithBottle(Items.DEAD_TUBE_CORAL, Items.TUBE_CORAL, true);
            }

            private void reviveWithBucket(final ItemLike dead, final ItemLike living, final boolean requiresWater) {
                ShapelessRecipeBuilder buider = shapeless(RecipeCategory.MISC, living)
                        .requires(dead)
                        .requires(ModItems.LIVING_SAP_BUCKET);
                if (requiresWater) {
                    buider = buider.requires(Items.WATER_BUCKET);
                }
                buider.unlockedBy("has_living_sap_bucket", has(ModItems.LIVING_SAP_BUCKET))
                        .save(exporter);
            }

            private void reviveWithBottle(final ItemLike dead, final ItemLike living, final boolean requiresWater) {
                ShapelessRecipeBuilder builder = shapeless(RecipeCategory.MISC, living)
                        .requires(dead)
                        .requires(ModItems.LIVING_SAP_BOTTLE);
                if (requiresWater) {
                    builder = builder.requires(Items.WATER_BUCKET);
                }
                builder.unlockedBy("has_living_sap_bottle", has(ModItems.LIVING_SAP_BOTTLE))
                        .save(exporter);
            }

            private void livingSapWoodRepairRecipes() {
                // acacia
                strippedWoodToWood(Items.STRIPPED_ACACIA_LOG, (AbstractSapFluid)ModFluids.ACACIA_SAP_STILL.value(), Items.ACACIA_LOG);
                planksToStrippedWood(Items.ACACIA_PLANKS, (AbstractSapFluid)ModFluids.ACACIA_SAP_STILL.value(), Items.STRIPPED_ACACIA_LOG);
                stairsToPlanks(Items.ACACIA_STAIRS, Items.ACACIA_PLANKS);
                slabsToPlanks(Items.ACACIA_SLAB, Items.ACACIA_PLANKS);
                // birch
                strippedWoodToWood(Items.STRIPPED_BIRCH_LOG, (AbstractSapFluid)ModFluids.BIRCH_SAP_STILL.value(), Items.BIRCH_LOG);
                planksToStrippedWood(Items.BIRCH_PLANKS, (AbstractSapFluid)ModFluids.BIRCH_SAP_STILL.value(), Items.STRIPPED_BIRCH_LOG);
                stairsToPlanks(Items.BIRCH_STAIRS, Items.BIRCH_PLANKS);
                slabsToPlanks(Items.BIRCH_SLAB, Items.BIRCH_PLANKS);
                // cherry
                strippedWoodToWood(Items.STRIPPED_CHERRY_LOG, (AbstractSapFluid)ModFluids.CHERRY_SAP_STILL.value(), Items.CHERRY_LOG);
                planksToStrippedWood(Items.CHERRY_PLANKS, (AbstractSapFluid)ModFluids.CHERRY_SAP_STILL.value(), Items.STRIPPED_CHERRY_LOG);
                stairsToPlanks(Items.CHERRY_STAIRS, Items.CHERRY_PLANKS);
                slabsToPlanks(Items.CHERRY_SLAB, Items.CHERRY_PLANKS);
                // crimson
                strippedWoodToWood(Items.STRIPPED_CRIMSON_STEM, (AbstractSapFluid)ModFluids.CRIMSON_SAP_STILL.value(), Items.CRIMSON_STEM);
                planksToStrippedWood(Items.CRIMSON_PLANKS, (AbstractSapFluid)ModFluids.CRIMSON_SAP_STILL.value(), Items.STRIPPED_CRIMSON_STEM);
                stairsToPlanks(Items.CRIMSON_STAIRS, Items.CRIMSON_PLANKS);
                slabsToPlanks(Items.CRIMSON_SLAB, Items.CRIMSON_PLANKS);
                // dark oak
                strippedWoodToWood(Items.STRIPPED_DARK_OAK_LOG, (AbstractSapFluid)ModFluids.OAK_SAP_STILL.value(), Items.DARK_OAK_LOG);
                planksToStrippedWood(Items.DARK_OAK_PLANKS, (AbstractSapFluid)ModFluids.OAK_SAP_STILL.value(), Items.STRIPPED_DARK_OAK_LOG);
                stairsToPlanks(Items.DARK_OAK_STAIRS, Items.DARK_OAK_PLANKS);
                slabsToPlanks(Items.DARK_OAK_SLAB, Items.DARK_OAK_PLANKS);
                // jungle
                strippedWoodToWood(Items.STRIPPED_JUNGLE_LOG, (AbstractSapFluid)ModFluids.JUNGLE_SAP_STILL.value(), Items.JUNGLE_LOG);
                planksToStrippedWood(Items.JUNGLE_PLANKS, (AbstractSapFluid)ModFluids.JUNGLE_SAP_STILL.value(), Items.STRIPPED_JUNGLE_LOG);
                stairsToPlanks(Items.JUNGLE_STAIRS, Items.JUNGLE_PLANKS);
                slabsToPlanks(Items.JUNGLE_SLAB, Items.JUNGLE_PLANKS);
                // mangrove
                strippedWoodToWood(Items.STRIPPED_MANGROVE_LOG, (AbstractSapFluid)ModFluids.MANGROVE_SAP_STILL.value(), Items.MANGROVE_LOG);
                planksToStrippedWood(Items.MANGROVE_PLANKS, (AbstractSapFluid)ModFluids.MANGROVE_SAP_STILL.value(), Items.STRIPPED_MANGROVE_LOG);
                stairsToPlanks(Items.MANGROVE_STAIRS, Items.MANGROVE_PLANKS);
                slabsToPlanks(Items.MANGROVE_SLAB, Items.MANGROVE_PLANKS);
                // oak
                strippedWoodToWood(Items.STRIPPED_OAK_LOG, (AbstractSapFluid)ModFluids.OAK_SAP_STILL.value(), Items.OAK_LOG);
                planksToStrippedWood(Items.OAK_PLANKS, (AbstractSapFluid)ModFluids.OAK_SAP_STILL.value(), Items.STRIPPED_OAK_LOG);
                stairsToPlanks(Items.OAK_STAIRS, Items.OAK_PLANKS);
                slabsToPlanks(Items.OAK_SLAB, Items.OAK_PLANKS);
                // pale oak
                strippedWoodToWood(Items.STRIPPED_PALE_OAK_LOG, (AbstractSapFluid)ModFluids.OAK_SAP_STILL.value(), Items.PALE_OAK_LOG);
                planksToStrippedWood(Items.PALE_OAK_PLANKS, (AbstractSapFluid)ModFluids.OAK_SAP_STILL.value(), Items.STRIPPED_PALE_OAK_LOG);
                stairsToPlanks(Items.PALE_OAK_STAIRS, Items.PALE_OAK_PLANKS);
                slabsToPlanks(Items.PALE_OAK_SLAB, Items.PALE_OAK_PLANKS);
                // spruce
                strippedWoodToWood(Items.STRIPPED_SPRUCE_LOG, (AbstractSapFluid)ModFluids.SPRUCE_SAP_STILL.value(), Items.SPRUCE_LOG);
                planksToStrippedWood(Items.SPRUCE_PLANKS, (AbstractSapFluid)ModFluids.SPRUCE_SAP_STILL.value(), Items.STRIPPED_SPRUCE_LOG);
                stairsToPlanks(Items.SPRUCE_STAIRS, Items.SPRUCE_PLANKS);
                slabsToPlanks(Items.SPRUCE_SLAB, Items.SPRUCE_PLANKS);
                // warped
                strippedWoodToWood(Items.STRIPPED_WARPED_STEM, (AbstractSapFluid)ModFluids.WARPED_SAP_STILL.value(), Items.WARPED_STEM);
                planksToStrippedWood(Items.WARPED_PLANKS, (AbstractSapFluid)ModFluids.WARPED_SAP_STILL.value(), Items.STRIPPED_WARPED_STEM);
                stairsToPlanks(Items.WARPED_STAIRS, Items.WARPED_PLANKS);
                slabsToPlanks(Items.WARPED_SLAB, Items.WARPED_PLANKS);
            }

            private void strippedWoodToWood(ItemLike strippedLog, AbstractSapFluid treeSap, ItemLike log) {
                shapeless(RecipeCategory.BUILDING_BLOCKS, log)
                        .requires(strippedLog)
                        .requires(ModItems.LIVING_SAP_BOTTLE)
                        .requires(treeSap.getBottle())
                        .unlockedBy("has_tree_sap_bottle", has(treeSap.getBottle()))
                        .save(exporter);
            }

            private void planksToStrippedWood(ItemLike planks, AbstractSapFluid treeSap, ItemLike strippedLog) {
                shapeless(RecipeCategory.BUILDING_BLOCKS, strippedLog)
                        .requires(planks, 4)
                        .requires(ModItems.LIVING_SAP_BOTTLE)
                        .requires(treeSap.getBottle())
                        .unlockedBy("has_tree_sap_bottle", has(treeSap.getBottle()))
                        .save(exporter);
            }

            private void stairsToPlanks(ItemLike stairs, ItemLike planks) {
                String description = planks.asItem().getDescriptionId();
                description = description.substring(description.lastIndexOf('.') + 1) + "_from_stairs";
                shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 3)
                        .requires(stairs, 4)
                        .requires(ModItems.LIVING_SAP_BOTTLE)
                        .unlockedBy("has_living_sap_bottle", has(ModItems.LIVING_SAP_BOTTLE))
                        .save(exporter, description);
            }

            private void slabsToPlanks(ItemLike slabs, ItemLike planks) {
                String description = planks.asItem().getDescriptionId();
                description = description.substring(description.lastIndexOf('.') + 1) + "_from_slabs";
                shapeless(RecipeCategory.BUILDING_BLOCKS, planks)
                        .requires(slabs, 2)
                        .requires(ModItems.LIVING_SAP_BOTTLE)
                        .unlockedBy("has_living_sap_bottle", has(ModItems.LIVING_SAP_BOTTLE))
                        .save(exporter, description);
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
                sapSipper(ModFluids.LIVING_SAP_STILL)
                        .requires(ModBlockTags.MAKES_LIVING_SAP)
                        .ticks(40)
                        .amount(1)
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
