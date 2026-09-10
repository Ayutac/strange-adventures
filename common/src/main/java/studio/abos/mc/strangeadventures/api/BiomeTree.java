package studio.abos.mc.strangeadventures.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import studio.abos.mc.strangeadventures.mixin.TreeGrowerAccessor;

import java.util.Optional;

public record BiomeTree(Identifier biome, String treeGrower, Identifier fallbackBlock) {

    public static final BiomeTree DEFAULT = new BiomeTree(Biomes.THE_VOID.identifier(), "oak", Identifier.fromNamespaceAndPath("minecraft", "oak_leaves"));
    public static final TreeGrower DEFAULT_GROWER = TreeGrower.OAK;
    public static final Block DEFAULT_FALLBACK_BLOCK = Blocks.OAK_LEAVES;

    public static Codec<BiomeTree> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("biome").forGetter(BiomeTree::biome),
            Codec.STRING.fieldOf("treeGrower").forGetter(BiomeTree::treeGrower),
            Identifier.CODEC.fieldOf("fallbackBlock").forGetter(BiomeTree::fallbackBlock)
        ).apply(instance, BiomeTree::new));

    public static void plant(final ServerLevel level, final BlockPos pos) {
        final BiomeTree biomeTree = findBiomeTree(level, pos).orElse(DEFAULT);
        if (!TreeGrowerAccessor.getGrowers().getOrDefault(biomeTree.treeGrower(), DEFAULT_GROWER)
                .growTree(level, level.getChunkSource().getGenerator(), pos, level.getBlockState(pos), level.getRandom())) {
            level.setBlockAndUpdate(pos, findBlock(level, biomeTree.fallbackBlock).orElse(DEFAULT_FALLBACK_BLOCK).defaultBlockState());
        }
    }

    private static Optional<BiomeTree> findBiomeTree(final ServerLevel level, final BlockPos pos) {
        return level.getBiome(pos).unwrapKey().flatMap(biome -> level.registryAccess().lookupOrThrow(StrangeAdventuresApi.BIOME_TREE_REGISTRY_KEY).stream()
                .filter(biomeTree -> biomeTree.biome.equals(biome.identifier()))
                .findFirst());
    }

    private static Optional<Block> findBlock(final ServerLevel level, final Identifier blockId) {
        return level.registryAccess().lookupOrThrow(Registries.BLOCK).getOptional(blockId);
    }

}
