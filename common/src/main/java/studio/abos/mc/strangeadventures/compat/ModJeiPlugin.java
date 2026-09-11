package studio.abos.mc.strangeadventures.compat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.recipe.EssenceCauldronRecipe;
import studio.abos.mc.strangeadventures.recipe.ModRecipeTypes;
import studio.abos.mc.strangeadventures.recipe.SapSipperRecipe;

import java.util.List;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {

    public static final IRecipeType<SapSipperRecipe> SAP_SIPPER = IRecipeType.create(
            StrangeAdventures.MOD_ID, "sap_sipper", SapSipperRecipe.class);
    public static final IRecipeType<EssenceCauldronRecipe> CAULDRON_MIXING = IRecipeType.create(
            StrangeAdventures.MOD_ID, "cauldron_mixing", EssenceCauldronRecipe.class);

    @Override
    public void registerCategories(final IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new SippingCategory(guiHelper));
    }

    @Override
    public void registerRecipes(final IRecipeRegistration registration) {
        registration.addRecipes(SAP_SIPPER,
                Balm.safeClientAccess().getRecipeMap().orElseThrow().byType(ModRecipeTypes.SAP_SIPPER.type()).stream()
                        .map(RecipeHolder::value)
                        .toList());
    }

    @Override
    public void registerIngredients(final IModIngredientRegistration registration) {
        registration.register(BlocksIngredient.TYPE, List.of(), new BlocksIngredientHelper(), new BlocksIngredientRenderer(RandomSource.create()), BlocksIngredient.CODEC);
    }

    @Override
    public void registerRecipeCatalysts(final IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(SAP_SIPPER, ModBlocks.SAP_SIPPER);
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
                return named.key().toString();
            }
            return "various blocks";
        }

        @Override
        public Object getUid(final BlocksIngredient ingredient, final UidContext context) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return named.key().toString();
            }
            return ingredient.toString();
        }

        @Override
        public Identifier getIdentifier(final BlocksIngredient ingredient) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return named.key().location();
            }
            return StrangeAdventures.id(ingredient.toString());
        }

        @Override
        public BlocksIngredient copyIngredient(final BlocksIngredient ingredient) {
            return ingredient;
        }

        @Override
        public String getErrorInfo(@Nullable final BlocksIngredient ingredient) {
            return String.valueOf(ingredient);
        }

    }

    public static class BlocksIngredientRenderer implements IIngredientRenderer<BlocksIngredient> {

        public final int DISPLAY_DURATION = 10; // in ticks

        protected final RandomSource random;

        protected BlocksIngredient currentIngredient;

        protected int index;

        protected Holder<Block> currentBlock;

        protected long lastGameTick = -1;

        public BlocksIngredientRenderer(final RandomSource random) {
            this.random = random;
        }

        @Override
        public void render(final GuiGraphicsExtractor guiGraphics, final BlocksIngredient ingredient) {
            final long currentGameTick = Minecraft.getInstance().level.getGameTime();
            if (currentIngredient != ingredient) {
                currentIngredient = ingredient;
                index = 0;
                lastGameTick = currentGameTick;
            }
            else {
                if (currentGameTick >= lastGameTick + DISPLAY_DURATION) {
                    lastGameTick = currentGameTick;
                    if (++index >= currentIngredient.blocks.size()) {
                        index = 0;
                    }
                }
            }
            currentBlock = currentIngredient.blocks().get(index);
            guiGraphics.item(currentBlock.value().asItem().getDefaultInstance(), 0, 0);
        }

        @Override
        public List<Component> getTooltip(final BlocksIngredient ingredient, final TooltipFlag tooltipFlag) {
            if (ingredient.blocks() instanceof HolderSet.Named<Block> named) {
                return List.of(Component.literal(named.key().toString()));
            }
            return List.of();
        }
    }

    public static class SippingCategory extends AbstractRecipeCategory<SapSipperRecipe> {

        public SippingCategory(final IGuiHelper guiHelper) {
            super(SAP_SIPPER, Component.translatable("jei.strangeadventures.sap_sipper"),
                    guiHelper.createDrawableItemLike(ModBlocks.SAP_SIPPER),
                    116, 54);
        }

        @Override
        public void setRecipe(final IRecipeLayoutBuilder builder, final SapSipperRecipe recipe, final IFocusGroup focuses) {
            builder.addInputSlot(1, 19)
                    .setStandardSlotBackground()
                    .add(BlocksIngredient.TYPE, new BlocksIngredient(recipe.getSapBlocks()));
            builder.addOutputSlot(95, 19)
                    .setFluidRenderer(1000L, true, 16, 16)
                    .add(recipe.getSapResult().value(), recipe.getAmountPerSap());
        }

    }

    /*//public class Fluid

    public class CauldronMixingCategory extends AbstractRecipeCategory<EssenceCauldronRecipe> {

        public CauldronMixingCategory(final IGuiHelper guiHelper) {
            super(CAULDRON_MIXING, Component.translatable("jei.strangeadventures.cauldron_mixing"),
                    guiHelper.createDrawableItemLike(ModBlocks.ESSENCE_CAULDRON),
                    116, 54);
        }

        @Override
        public void setRecipe(final IRecipeLayoutBuilder builder, final EssenceCauldronRecipe recipe, final IFocusGroup focuses) {
            builder.addInputSlot(1, 19)
                    .setStandardSlotBackground()
                    .add(Ingredient.of(recipe.getItems().get(0)));
            builder.addInputSlot(20, 19)
                    .setFluidRenderer(333L, true, 16, 16)
                    .add(recipe.getFluids().value(), recipe.getAmountPerSap());
        }

    }*/

}
