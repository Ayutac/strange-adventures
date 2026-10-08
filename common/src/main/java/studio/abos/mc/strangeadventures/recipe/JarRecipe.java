package studio.abos.mc.strangeadventures.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.codec.RegistryCodecs;
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
import studio.abos.mc.strangeadventures.blockentity.JarBlockEntity;

import java.util.List;

@Getter
public class JarRecipe implements Recipe<JarRecipeInput> {

    public static final MapCodec<JarRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("result").forGetter(JarRecipe::getResult),
                    ExtraCodecs.POSITIVE_INT.fieldOf("amount").forGetter(JarRecipe::getAmount),
                    RegistryCodecs.holderSet(BuiltInRegistries.ITEM.key()).listOf().fieldOf("items").forGetter(JarRecipe::getItems),
                    RegistryCodecs.holderSet(BuiltInRegistries.FLUID.key()).fieldOf("fluid").forGetter(JarRecipe::getFluid),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("time").forGetter(JarRecipe::getTime)
            ).apply(instance, JarRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, JarRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(BuiltInRegistries.ITEM.key()), JarRecipe::getResult,
            ByteBufCodecs.VAR_INT, JarRecipe::getAmount,
            ByteBufCodecs.holderSet(BuiltInRegistries.ITEM.key()).apply(ByteBufCodecs.list()), JarRecipe::getItems,
            ByteBufCodecs.holderSet(BuiltInRegistries.FLUID.key()), JarRecipe::getFluid,
            ByteBufCodecs.VAR_INT, JarRecipe::getTime,
            JarRecipe::new
    );

    protected final Holder<Item> result;
    protected final int amount;
    protected final List<HolderSet<Item>> items;
    protected final HolderSet<Fluid> fluid;
    protected final int time;

    public JarRecipe(final Holder<Item> result, final int amount, final List<HolderSet<Item>> items, final HolderSet<Fluid> fluid, final int time) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        if (items.size() > JarBlockEntity.MAX_ITEMS) {
            throw new IllegalArgumentException("Too many item types for this jar recipe!");
        }
        if (items.isEmpty()) {
            throw new IllegalArgumentException("At least one item type must be given!");
        }
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative!");
        }
        this.result = result;
        this.amount = amount;
        this.items = items;
        this.fluid = fluid;
        this.time = time;
    }

    @Override
    public boolean matches(final JarRecipeInput input, final Level level) {
        return ModRecipeTypes.matches(items, input.getProperItems()) &&
                !input.getFluids().isEmpty() &&
                fluid.contains(input.getFluids().getFirst());
    }

    @Override
    public ItemStack assemble(final JarRecipeInput jarRecipeInput) {
        return new ItemStack(result, amount);
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "jar";
    }

    @Override
    public RecipeSerializer<? extends Recipe<JarRecipeInput>> getSerializer() {
        return ModRecipeTypes.JAR.serializer();
    }

    @Override
    public RecipeType<? extends Recipe<JarRecipeInput>> getType() {
        return ModRecipeTypes.JAR.type();
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
