package studio.abos.mc.strangeadventures.compat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.platform.LoaderPlatforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.FluidUtil;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.recipe.EssenceCauldronRecipe;
import studio.abos.mc.strangeadventures.recipe.ModRecipeTypes;
import studio.abos.mc.strangeadventures.recipe.SapSipperRecipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {

    public static final IRecipeType<SapSipperRecipe> SAP_SIPPER = IRecipeType.create(
            StrangeAdventures.MOD_ID, "sap_sipper", SapSipperRecipe.class);
    public static final IRecipeType<EssenceCauldronRecipe> ESSENCE_CAULDRON = IRecipeType.create(
            StrangeAdventures.MOD_ID, "essence_cauldron", EssenceCauldronRecipe.class);

    @Override
    public void registerCategories(final IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new SapSipperCategory(guiHelper));
        registration.addRecipeCategories(new EssenceCauldronCategory(guiHelper));
    }

    @Override
    public void registerRecipes(final IRecipeRegistration registration) {
        final RecipeMap recipeMap = Balm.safeClientAccess().getRecipeMap().orElseThrow();
        registration.addRecipes(SAP_SIPPER,
                recipeMap.byType(ModRecipeTypes.SAP_SIPPER.type()).stream()
                        .map(RecipeHolder::value)
                        .toList());
        registration.addRecipes(ESSENCE_CAULDRON,
                recipeMap.byType(ModRecipeTypes.ESSENCE_CAULDRON.type()).stream()
                        .map(RecipeHolder::value)
                        .toList());
    }

    @Override
    public void registerIngredients(final IModIngredientRegistration registration) {
        registration.register(BlocksIngredient.TYPE, List.of(), new BlocksIngredientHelper(), new BlocksIngredientRenderer(), BlocksIngredient.CODEC);
        registration.register(FluidsIngredient.TYPE, List.of(), new FluidsIngredientHelper(), new FluidsIngredientRenderer(), FluidsIngredient.CODEC);
    }

    @Override
    public void registerRecipeCatalysts(final IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(SAP_SIPPER, ModBlocks.SAP_SIPPER);
        registration.addCraftingStation(ESSENCE_CAULDRON, ModBlocks.ESSENCE_CAULDRON);
    }

    @Override
    public Identifier getPluginUid() {
        return StrangeAdventures.id("jei_plugin");
    }

    public record BlocksIngredient(HolderSet<Block> blocks) {

        public static final IIngredientType<BlocksIngredient> TYPE = new IIngredientType<>() {
            @Override
            public Class<? extends BlocksIngredient> getIngredientClass() {
                return BlocksIngredient.class;
            }

            @Override
            public String getUid() {
                return StrangeAdventures.MOD_ID + ":blocks";
            }
        };

        public static final Codec<BlocksIngredient> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    RegistryCodecs.homogeneousList(BuiltInRegistries.BLOCK.key()).fieldOf("blocks").forGetter(BlocksIngredient::blocks)
            ).apply(instance, BlocksIngredient::new));

    }

    public static class BlocksIngredientHelper implements IIngredientHelper<BlocksIngredient> {

        @Override
        public IIngredientType<BlocksIngredient> getIngredientType() {
            return BlocksIngredient.TYPE;
        }

        @Override
        public String getDisplayName(final BlocksIngredient ingredient) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return "Accepts tag: #" + named.key().location();
            }
            throw new IllegalStateException("Only named holder sets are allowed!");
        }

        @Override
        public Object getUid(final BlocksIngredient ingredient, final UidContext context) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return named.key().toString();
            }
            throw new IllegalStateException("Only named holder sets are allowed!");
        }

        @Override
        public Identifier getIdentifier(final BlocksIngredient ingredient) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return named.key().location();
            }
            throw new IllegalStateException("Only named holder sets are allowed!");
        }

        @Override
        public BlocksIngredient copyIngredient(final BlocksIngredient ingredient) {
            return ingredient;
        }

        @Override
        public String getErrorInfo(@Nullable final BlocksIngredient ingredient) {
            return String.valueOf(ingredient);
        }

        @Override
        public boolean isValidIngredient(final BlocksIngredient ingredient) {
            return ingredient.blocks() instanceof HolderSet.Named<Block>;
        }

    }

    public static class BlocksIngredientRenderer implements IIngredientRenderer<BlocksIngredient> {

        public final int DISPLAY_DURATION = 15; // in ticks

        protected final Map<BlocksIngredient, Integer> ingredientIndex = new HashMap<>();

        protected long lastGameTick = -1;

        @Override
        public void render(final GuiGraphicsExtractor guiGraphics, final BlocksIngredient ingredient) {
            final long currentGameTick = Minecraft.getInstance().level.getGameTime();
            if (lastGameTick == -1) {
                lastGameTick = currentGameTick;
            }
            if (!ingredientIndex.containsKey(ingredient)) {
                ingredientIndex.put(ingredient, 0);
            }
            if (currentGameTick >= lastGameTick + DISPLAY_DURATION) {
                lastGameTick = currentGameTick;
                for (final BlocksIngredient ingredientKey : ingredientIndex.keySet()) {
                    final int newIndex = ingredientIndex.get(ingredientKey) + 1;
                    if (newIndex >= ingredientKey.blocks.size()) {
                        ingredientIndex.put(ingredientKey, 0);
                    } else {
                        ingredientIndex.put(ingredientKey, newIndex);
                    }
                }
            }
            guiGraphics.item(ingredient.blocks().get(ingredientIndex.get(ingredient))
                    .value().asItem().getDefaultInstance(), 0, 0);
        }

        @Override
        public List<Component> getTooltip(final BlocksIngredient ingredient, final TooltipFlag tooltipFlag) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return List.of(
                        ingredient.blocks().get(ingredientIndex.get(ingredient)).value().getName(),
                        Component.translatable("gui.strangeadventures.accept_blocks_tag"),
                        Component.literal("#" + named.key().location()));
            }
            return List.of();
        }

    }

    public record FluidsIngredient(HolderSet<Fluid> fluids) {

        public static final IIngredientType<FluidsIngredient> TYPE = new IIngredientType<>() {
            @Override
            public Class<? extends FluidsIngredient> getIngredientClass() {
                return FluidsIngredient.class;
            }

            @Override
            public String getUid() {
                return StrangeAdventures.MOD_ID + ":fluids";
            }
        };

        public static final Codec<FluidsIngredient> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                RegistryCodecs.homogeneousList(BuiltInRegistries.FLUID.key()).fieldOf("fluids").forGetter(FluidsIngredient::fluids)
        ).apply(instance, FluidsIngredient::new));

    }

    public static class FluidsIngredientHelper implements IIngredientHelper<FluidsIngredient> {

        @Override
        public IIngredientType<FluidsIngredient> getIngredientType() {
            return FluidsIngredient.TYPE;
        }

        @Override
        public String getDisplayName(final FluidsIngredient ingredient) {
            if (ingredient.fluids() instanceof HolderSet.Named<Fluid> named) {
                return "Accepts tag: #" + named.key().location();
            }
            throw new IllegalStateException("Only named holder sets are allowed!");
        }

        @Override
        public Object getUid(final FluidsIngredient ingredient, final UidContext context) {
            if (ingredient.fluids() instanceof HolderSet.Named<Fluid> named) {
                return named.key().toString();
            }
            throw new IllegalStateException("Only named holder sets are allowed!");
        }

        @Override
        public Identifier getIdentifier(final FluidsIngredient ingredient) {
            if (ingredient.fluids() instanceof HolderSet.Named<Fluid> named) {
                return named.key().location();
            }
            throw new IllegalStateException("Only named holder sets are allowed!");
        }

        @Override
        public FluidsIngredient copyIngredient(final FluidsIngredient ingredient) {
            return ingredient;
        }

        @Override
        public String getErrorInfo(@Nullable final FluidsIngredient ingredient) {
            return String.valueOf(ingredient);
        }

        @Override
        public boolean isValidIngredient(final FluidsIngredient ingredient) {
            return ingredient.fluids() instanceof HolderSet.Named<Fluid>;
        }

    }

    public static class FluidsIngredientRenderer implements IIngredientRenderer<FluidsIngredient> {

        public final int DISPLAY_DURATION = 15; // in ticks

        protected final Map<FluidsIngredient, Integer> ingredientIndex = new HashMap<>();

        protected long lastGameTick = -1;

        @Override
        public void render(final GuiGraphicsExtractor guiGraphics, final FluidsIngredient ingredient) {
            final long currentGameTick = Minecraft.getInstance().level.getGameTime();
            if (lastGameTick == -1) {
                lastGameTick = currentGameTick;
            }
            if (!ingredientIndex.containsKey(ingredient)) {
                ingredientIndex.put(ingredient, 0);
            }
            if (currentGameTick >= lastGameTick + DISPLAY_DURATION) {
                lastGameTick = currentGameTick;
                for (final FluidsIngredient ingredientKey : ingredientIndex.keySet()) {
                    final int newIndex = ingredientIndex.get(ingredientKey) + 1;
                    if (newIndex >= ingredientKey.fluids.size()) {
                        ingredientIndex.put(ingredientKey, 0);
                    } else {
                        ingredientIndex.put(ingredientKey, newIndex);
                    }
                }
            }
            final FluidModel fluidModel = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(
                    ingredient.fluids().get(ingredientIndex.get(ingredient)).value().defaultFluidState());
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, fluidModel.stillMaterial().sprite(), 0, 0, 16, 16);
        }

        @Override
        public List<Component> getTooltip(final FluidsIngredient ingredient, final TooltipFlag tooltipFlag) {
            if (ingredient.fluids() instanceof HolderSet.Named<Fluid> named) {
                return List.of(
                        Component.translatable("fluid." + ingredient.fluids().get(ingredientIndex.get(ingredient)).getRegisteredName().replace(':','.')),
                        Component.translatable("gui.strangeadventures.accept_fluids_tag"),
                        Component.literal("#" + named.key().location()));
            }
            return List.of();
        }

    }

    public static class SapSipperCategory extends AbstractRecipeCategory<SapSipperRecipe> {

        public SapSipperCategory(final IGuiHelper guiHelper) {
            super(SAP_SIPPER, Component.translatable("gui.strangeadventures.title.sap_sipper"),
                    guiHelper.createDrawableItemLike(ModBlocks.SAP_SIPPER),
                    116, 18);
        }

        @Override
        public void setRecipe(final IRecipeLayoutBuilder builder, final SapSipperRecipe recipe, final IFocusGroup focuses) {
            builder.addInputSlot(13, 1)
                    .setStandardSlotBackground()
                    .add(BlocksIngredient.TYPE, new BlocksIngredient(recipe.getSapBlocks()));
            builder.addOutputSlot(84, 1)
                    .setFluidRenderer(FluidUtil.MB_PER_BUCKET, true, 16, 16)
                    .add(recipe.getSapResult().value(), (Balm.platform().name().equals(LoaderPlatforms.FABRIC) ? FluidUtil.DROPLETS_PER_MB : 1L) * recipe.getAmountPerSap());
        }

        @Override
        public void createRecipeExtras(final IRecipeExtrasBuilder builder, final SapSipperRecipe recipe, final IFocusGroup focuses) {
            builder.addAnimatedRecipeArrowWidget(recipe.getTicksPerSap())
                    .setPosition(45, 1);
        }

    }

    public static class EssenceCauldronCategory extends AbstractRecipeCategory<EssenceCauldronRecipe> {

        public EssenceCauldronCategory(final IGuiHelper guiHelper) {
            super(ESSENCE_CAULDRON, Component.translatable("gui.strangeadventures.title.essence_cauldron"),
                    guiHelper.createDrawableItemLike(ModBlocks.ESSENCE_CAULDRON),
                    116, 36);
        }

        @Override
        public void setRecipe(final IRecipeLayoutBuilder builder, final EssenceCauldronRecipe recipe, final IFocusGroup focuses) {
            for (int i = 0; i < recipe.getItems().size(); i++) {
                builder.addInputSlot(18 * i + 1, 1)
                        .setStandardSlotBackground()
                        .add(Ingredient.of(recipe.getItems().get(i)));
            }
            for (int i = 0; i < recipe.getFluids().size(); i++) {
                builder.addInputSlot(18 * i + 1, 19)
                        .setStandardSlotBackground()
                        .add(FluidsIngredient.TYPE, new FluidsIngredient(recipe.getFluids().get(i)));
            }
            builder.addOutputSlot(95, 9)
                    .setOutputSlotBackground()
                    .add(new ItemStack(recipe.getResult(), recipe.getAmount()));
        }

        @Override
        public void createRecipeExtras(final IRecipeExtrasBuilder builder, final EssenceCauldronRecipe recipe, final IFocusGroup focuses) {
            builder.addRecipeArrowWidget()
                    .setPosition(60, 9);
        }

    }

}
