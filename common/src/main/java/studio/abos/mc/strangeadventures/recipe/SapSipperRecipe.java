package studio.abos.mc.strangeadventures.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

@Getter
public class SapSipperRecipe implements Recipe<SingleBlockRecipeInput> {

    public static final MapCodec<SapSipperRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BuiltInRegistries.FLUID.holderByNameCodec().fieldOf("sapResult").forGetter(SapSipperRecipe::getSapResult),
                    RegistryCodecs.homogeneousList(BuiltInRegistries.BLOCK.key()).fieldOf("sapBlocks").forGetter(SapSipperRecipe::getSapBlocks),
                    ExtraCodecs.POSITIVE_INT.fieldOf("ticksPerSap").forGetter(SapSipperRecipe::getTicksPerSap),
                    ExtraCodecs.POSITIVE_INT.fieldOf("amountPerSap").forGetter(SapSipperRecipe::getAmountPerSap)
            ).apply(instance, SapSipperRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SapSipperRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(BuiltInRegistries.FLUID.key()), SapSipperRecipe::getSapResult,
            ByteBufCodecs.holderSet(BuiltInRegistries.BLOCK.key()), SapSipperRecipe::getSapBlocks,
            ByteBufCodecs.INT, SapSipperRecipe::getTicksPerSap,
            ByteBufCodecs.INT, SapSipperRecipe::getAmountPerSap,
            SapSipperRecipe::new
    );

    protected final Holder<Fluid> sapResult;
    protected final HolderSet<Block> sapBlocks;
    protected final int ticksPerSap;
    protected final int amountPerSap; // in mB

    public SapSipperRecipe(final Holder<Fluid> sapResult, final HolderSet<Block> sapBlocks, final int ticksPerSap, final int amountPerSap) {
        this.sapResult = sapResult;
        this.sapBlocks = sapBlocks;
        if (ticksPerSap <= 0) {
            throw new IllegalArgumentException("ticksPerSap must be positive!");
        }
        this.ticksPerSap = ticksPerSap;
        if (amountPerSap <= 0) {
            throw new IllegalArgumentException("amountPerSap must be positive!");
        }
        this.amountPerSap = amountPerSap;
    }

    @Override
    public boolean matches(final SingleBlockRecipeInput singleBlockRecipeInput, final Level level) {
        return sapBlocks.contains(singleBlockRecipeInput.getInput());
    }

    @Override
    public ItemStack assemble(final SingleBlockRecipeInput singleRecipeInput) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "sapping";
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleBlockRecipeInput>> getSerializer() {
        return ModRecipeTypes.SAP_SIPPER.serializer();
    }

    @Override
    public RecipeType<? extends Recipe<SingleBlockRecipeInput>> getType() {
        return ModRecipeTypes.SAP_SIPPER.type();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

}
