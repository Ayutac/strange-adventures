package studio.abos.mc.strangeadventures.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

public record BiomeTree(Identifier biome, String treeGrower) {

    public static Codec<BiomeTree> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("biome").forGetter(BiomeTree::biome),
            Codec.STRING.fieldOf("treeGrower").forGetter(BiomeTree::treeGrower)
        ).apply(instance, BiomeTree::new));

}
