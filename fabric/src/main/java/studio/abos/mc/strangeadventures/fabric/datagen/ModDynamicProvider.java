package studio.abos.mc.strangeadventures.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
        registerBiomeTree(context, "plains", Biomes.PLAINS, TreeGrower.OAK);
        registerBiomeTree(context, "sunflower_plains", Biomes.SUNFLOWER_PLAINS, TreeGrower.OAK);
        registerBiomeTree(context, "snowy_plains", Biomes.SNOWY_PLAINS, TreeGrower.OAK);
        registerBiomeTree(context, "ice_spikes", Biomes.ICE_SPIKES, TreeGrower.SPRUCE);
        registerBiomeTree(context, "desert", Biomes.DESERT, TreeGrower.ACACIA);
        registerBiomeTree(context, "swamp", Biomes.SWAMP, TreeGrower.MANGROVE);
        registerBiomeTree(context, "mangrove_swamp", Biomes.MANGROVE_SWAMP, TreeGrower.MANGROVE);
        registerBiomeTree(context, "forest", Biomes.FOREST, TreeGrower.OAK);
        registerBiomeTree(context, "flower_forest", Biomes.FLOWER_FOREST, TreeGrower.OAK);
        registerBiomeTree(context, "birch_forest", Biomes.BIRCH_FOREST, TreeGrower.BIRCH);
        registerBiomeTree(context, "dark_forest", Biomes.DARK_FOREST, TreeGrower.DARK_OAK);
        registerBiomeTree(context, "pale_garden", Biomes.PALE_GARDEN, TreeGrower.PALE_OAK);
        registerBiomeTree(context, "old_growth_birch_forest", Biomes.OLD_GROWTH_BIRCH_FOREST, TreeGrower.BIRCH);
        registerBiomeTree(context, "old_growth_pine_taiga", Biomes.OLD_GROWTH_PINE_TAIGA, TreeGrower.SPRUCE);
        registerBiomeTree(context, "old_growth_spruce_taiga", Biomes.OLD_GROWTH_SPRUCE_TAIGA, TreeGrower.SPRUCE);
        registerBiomeTree(context, "taiga", Biomes.TAIGA, TreeGrower.SPRUCE);
        registerBiomeTree(context, "snowy_taiga", Biomes.SNOWY_TAIGA, TreeGrower.SPRUCE);
        registerBiomeTree(context, "savanna", Biomes.SAVANNA, TreeGrower.ACACIA);
        registerBiomeTree(context, "savanna_plateau", Biomes.SAVANNA_PLATEAU, TreeGrower.ACACIA);
        registerBiomeTree(context, "windswept_hills", Biomes.WINDSWEPT_HILLS, TreeGrower.SPRUCE);
        registerBiomeTree(context, "windswept_gravelly_hills", Biomes.WINDSWEPT_GRAVELLY_HILLS, TreeGrower.SPRUCE);
        registerBiomeTree(context, "windswept_forest", Biomes.WINDSWEPT_FOREST, TreeGrower.SPRUCE);
        registerBiomeTree(context, "windswept_savanna", Biomes.WINDSWEPT_SAVANNA, TreeGrower.ACACIA);
        registerBiomeTree(context, "jungle", Biomes.JUNGLE, TreeGrower.JUNGLE);
        registerBiomeTree(context, "sparse_jungle", Biomes.SPARSE_JUNGLE, TreeGrower.JUNGLE);
        registerBiomeTree(context, "bamboo_jungle", Biomes.BAMBOO_JUNGLE, TreeGrower.JUNGLE);
        registerBiomeTree(context, "badlands", Biomes.BADLANDS, TreeGrower.OAK);
        registerBiomeTree(context, "eroded_badlands", Biomes.ERODED_BADLANDS, TreeGrower.OAK);
        registerBiomeTree(context, "wooded_badlands", Biomes.WOODED_BADLANDS, TreeGrower.OAK);
        registerBiomeTree(context, "meadow", Biomes.MEADOW, TreeGrower.OAK);
        registerBiomeTree(context, "cherry_grove", Biomes.CHERRY_GROVE, TreeGrower.CHERRY);
        registerBiomeTree(context, "grove", Biomes.GROVE, TreeGrower.SPRUCE);
        registerBiomeTree(context, "snowy_slopes", Biomes.SNOWY_SLOPES, TreeGrower.SPRUCE);
        registerBiomeTree(context, "frozen_peaks", Biomes.FROZEN_PEAKS, TreeGrower.SPRUCE);
        registerBiomeTree(context, "jagged_peaks", Biomes.JAGGED_PEAKS, TreeGrower.SPRUCE);
        registerBiomeTree(context, "stony_peaks", Biomes.STONY_PEAKS, TreeGrower.SPRUCE);
        registerBiomeTree(context, "river", Biomes.RIVER, TreeGrower.OAK);
        registerBiomeTree(context, "frozen_river", Biomes.FROZEN_RIVER, TreeGrower.OAK);
        registerBiomeTree(context, "beach", Biomes.BEACH, TreeGrower.JUNGLE);
        registerBiomeTree(context, "snowy_beach", Biomes.SNOWY_BEACH, TreeGrower.SPRUCE);
        registerBiomeTree(context, "stony_shore", Biomes.STONY_SHORE, TreeGrower.OAK);
        // oceans should default to oak
        // caves as well except lush caves
        registerBiomeTree(context, "lush_caves", Biomes.LUSH_CAVES, TreeGrower.AZALEA);
        // FIXME nether and end biomes with appropriate tree growers
    }

    public static void registerBiomeTree(final BootstrapContext<BiomeTree> context, String name, ResourceKey<Biome> biome, TreeGrower treeGrower) {
        context.register(
                ResourceKey.create(StrangeAdventuresApi.BIOME_TREE_REGISTRY_KEY, StrangeAdventures.id(name)),
                new BiomeTree(biome.identifier(), ((TreeGrowerAccessor)(Object)treeGrower).getName())
        );
    }

    @Override
    public String getName() {
        return "Dynamic Provider";
    }

}
