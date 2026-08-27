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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;

import java.util.List;

@Getter
public class EssenceCauldronRecipe implements Recipe<EssenceCauldronRecipeInput> {

    public static final MapCodec<EssenceCauldronRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("result").forGetter(EssenceCauldronRecipe::getResult),
                    ExtraCodecs.POSITIVE_INT.fieldOf("amount").forGetter(EssenceCauldronRecipe::getAmount),
                    RegistryCodecs.homogeneousList(BuiltInRegistries.ITEM.key()).listOf().fieldOf("items").forGetter(EssenceCauldronRecipe::getItems),
                    RegistryCodecs.homogeneousList(BuiltInRegistries.FLUID.key()).listOf().fieldOf("fluids").forGetter(EssenceCauldronRecipe::getFluids)
            ).apply(instance, EssenceCauldronRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, EssenceCauldronRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(BuiltInRegistries.ITEM.key()), EssenceCauldronRecipe::getResult,
            ByteBufCodecs.INT, EssenceCauldronRecipe::getAmount,
            ByteBufCodecs.holderSet(BuiltInRegistries.ITEM.key()).apply(ByteBufCodecs.list()), EssenceCauldronRecipe::getItems,
            ByteBufCodecs.holderSet(BuiltInRegistries.FLUID.key()).apply(ByteBufCodecs.list()), EssenceCauldronRecipe::getFluids,
            EssenceCauldronRecipe::new
    );

    protected final Holder<Item> result;
    protected final int amount;
    protected final List<HolderSet<Item>> items;
    protected final List<HolderSet<Fluid>> fluids;

    public EssenceCauldronRecipe(final Holder<Item> result, final int amount, final List<HolderSet<Item>> items, final List<HolderSet<Fluid>> fluids) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        if (items.size() > EssenceCauldronBlockEntity.MAX_ITEMS) {
            throw new IllegalArgumentException("Too many item types for this cauldron recipe!");
        }
        if (fluids.size() > EssenceCauldronBlockEntity.MAX_FLUIDS) {
            throw new IllegalArgumentException("Too many fluid types for this cauldron recipe!");
        }
        if (items.isEmpty() && fluids.isEmpty()) {
            throw new IllegalArgumentException("Items and Fluids cannot both be empty!");
        }
        this.result = result;
        this.amount = amount;
        this.items = List.copyOf(items);
        this.fluids = List.copyOf(fluids);
    }

    @Override
    public boolean matches(final EssenceCauldronRecipeInput input, final Level level) {
        return ModRecipeTypes.matches(items, input.getProperItems()) &&
                ModRecipeTypes.matches(fluids, input.getFluids());
    }

    @Override
    public ItemStack assemble(final EssenceCauldronRecipeInput input) {
        return new ItemStack(result, amount);
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "cauldron";
    }

    @Override
    public RecipeSerializer<? extends Recipe<EssenceCauldronRecipeInput>> getSerializer() {
        return ModRecipeTypes.ESSENCE_CAULDRON.serializer();
    }

    @Override
    public RecipeType<? extends Recipe<EssenceCauldronRecipeInput>> getType() {
        return ModRecipeTypes.ESSENCE_CAULDRON.type();
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
