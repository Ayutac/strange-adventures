package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.api.BiomeTree;
import studio.abos.mc.strangeadventures.mixin.TreeGrowerAccessor;

import java.util.concurrent.CompletableFuture;

public class ModDynamicProvider extends FabricDynamicRegistryProvider {

    public ModDynamicProvider(final FabricPackOutput dataOutput, final CompletableFuture<HolderLookup.Provider> provider) {
        super(dataOutput, provider);
    }

    @Override
    protected void configure(final HolderLookup.Provider registries, final Entries entries) {
        entries.addAll(registries.lookupOrThrow(StrangeAdventuresApi.BIOME_TREE_REGISTRY_KEY));
    }

    public static void configureBiomeTrees(final BootstrapContext<BiomeTree> context) {
        // vanilla biomes ordered as in Biomes
        registerBiomeTree(context, "plains", Biomes.PLAINS, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "sunflower_plains", Biomes.SUNFLOWER_PLAINS, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "snowy_plains", Biomes.SNOWY_PLAINS, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "ice_spikes", Biomes.ICE_SPIKES, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "desert", Biomes.DESERT, TreeGrower.ACACIA, Blocks.ACACIA_LEAVES);
        registerBiomeTree(context, "swamp", Biomes.SWAMP, TreeGrower.MANGROVE, Blocks.MANGROVE_LEAVES);
        registerBiomeTree(context, "mangrove_swamp", Biomes.MANGROVE_SWAMP, TreeGrower.MANGROVE, Blocks.MANGROVE_LEAVES);
        registerBiomeTree(context, "forest", Biomes.FOREST, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "flower_forest", Biomes.FLOWER_FOREST, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "birch_forest", Biomes.BIRCH_FOREST, TreeGrower.BIRCH, Blocks.BIRCH_LEAVES);
        registerBiomeTree(context, "dark_forest", Biomes.DARK_FOREST, TreeGrower.DARK_OAK, Blocks.DARK_OAK_LEAVES);
        registerBiomeTree(context, "pale_garden", Biomes.PALE_GARDEN, TreeGrower.PALE_OAK, Blocks.PALE_OAK_LEAVES);
        registerBiomeTree(context, "old_growth_birch_forest", Biomes.OLD_GROWTH_BIRCH_FOREST, TreeGrower.BIRCH, Blocks.BIRCH_LEAVES);
        registerBiomeTree(context, "old_growth_pine_taiga", Biomes.OLD_GROWTH_PINE_TAIGA, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "old_growth_spruce_taiga", Biomes.OLD_GROWTH_SPRUCE_TAIGA, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "taiga", Biomes.TAIGA, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "snowy_taiga", Biomes.SNOWY_TAIGA, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "savanna", Biomes.SAVANNA, TreeGrower.ACACIA, Blocks.ACACIA_LEAVES);
        registerBiomeTree(context, "savanna_plateau", Biomes.SAVANNA_PLATEAU, TreeGrower.ACACIA, Blocks.ACACIA_LEAVES);
        registerBiomeTree(context, "windswept_hills", Biomes.WINDSWEPT_HILLS, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "windswept_gravelly_hills", Biomes.WINDSWEPT_GRAVELLY_HILLS, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "windswept_forest", Biomes.WINDSWEPT_FOREST, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "windswept_savanna", Biomes.WINDSWEPT_SAVANNA, TreeGrower.ACACIA, Blocks.ACACIA_LEAVES);
        registerBiomeTree(context, "jungle", Biomes.JUNGLE, TreeGrower.JUNGLE, Blocks.JUNGLE_LEAVES);
        registerBiomeTree(context, "sparse_jungle", Biomes.SPARSE_JUNGLE, TreeGrower.JUNGLE, Blocks.JUNGLE_LEAVES);
        registerBiomeTree(context, "bamboo_jungle", Biomes.BAMBOO_JUNGLE, TreeGrower.JUNGLE, Blocks.JUNGLE_LEAVES);
        registerBiomeTree(context, "badlands", Biomes.BADLANDS, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "eroded_badlands", Biomes.ERODED_BADLANDS, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "wooded_badlands", Biomes.WOODED_BADLANDS, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "meadow", Biomes.MEADOW, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "cherry_grove", Biomes.CHERRY_GROVE, TreeGrower.CHERRY, Blocks.CHERRY_LEAVES);
        registerBiomeTree(context, "grove", Biomes.GROVE, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "snowy_slopes", Biomes.SNOWY_SLOPES, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "frozen_peaks", Biomes.FROZEN_PEAKS, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "jagged_peaks", Biomes.JAGGED_PEAKS, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "stony_peaks", Biomes.STONY_PEAKS, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "river", Biomes.RIVER, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "frozen_river", Biomes.FROZEN_RIVER, TreeGrower.OAK, Blocks.OAK_LEAVES);
        registerBiomeTree(context, "beach", Biomes.BEACH, TreeGrower.JUNGLE, Blocks.JUNGLE_LEAVES);
        registerBiomeTree(context, "snowy_beach", Biomes.SNOWY_BEACH, TreeGrower.SPRUCE, Blocks.SPRUCE_LEAVES);
        registerBiomeTree(context, "stony_shore", Biomes.STONY_SHORE, TreeGrower.OAK, Blocks.OAK_LEAVES);
        // oceans should default to oak
        // caves as well except lush caves
        registerBiomeTree(context, "lush_caves", Biomes.LUSH_CAVES, TreeGrower.AZALEA, Blocks.AZALEA_LEAVES);
        // FIXME nether and end biomes with appropriate tree growers
    }

    public static void registerBiomeTree(final BootstrapContext<BiomeTree> context, String name, ResourceKey<Biome> biome, TreeGrower treeGrower, Block fallback) {
        context.register(
                ResourceKey.create(StrangeAdventuresApi.BIOME_TREE_REGISTRY_KEY, StrangeAdventures.id(name)),
                new BiomeTree(biome.identifier(), ((TreeGrowerAccessor)(Object)treeGrower).getName(), BuiltInRegistries.BLOCK.getKey(fallback))
        );
    }

    @Override
    public String getName() {
        return "Dynamic Provider";
    }

}
