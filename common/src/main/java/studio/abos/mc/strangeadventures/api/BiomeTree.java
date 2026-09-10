package studio.abos.mc.strangeadventures.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.grower.TreeGrower;
import studio.abos.mc.strangeadventures.mixin.TreeGrowerAccessor;

public record BiomeTree(Identifier biome, String treeGrower) {

    public static Codec<BiomeTree> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("biome").forGetter(BiomeTree::biome),
            Codec.STRING.fieldOf("treeGrower").forGetter(BiomeTree::treeGrower)
        ).apply(instance, BiomeTree::new));

    public static void plant(final ServerLevel level, final BlockPos pos) {
        find(level, pos).growTree(level, level.getChunkSource().getGenerator(), pos, level.getBlockState(pos), level.getRandom());
    }

    private static TreeGrower find(final ServerLevel level, final BlockPos pos) {
        final String growerId = level.getBiome(pos).unwrapKey().map(biome ->
                level.registryAccess().lookupOrThrow(StrangeAdventuresApi.BIOME_TREE_REGISTRY_KEY).stream()
                        .filter(biomeTree -> biomeTree.biome.equals(biome.identifier()))
                        .findFirst()
                        .map(BiomeTree::treeGrower)
                        .orElse("oak")
            ).orElse("oak");
        return TreeGrowerAccessor.getGrowers().getOrDefault(growerId, TreeGrower.OAK);
    }

}
