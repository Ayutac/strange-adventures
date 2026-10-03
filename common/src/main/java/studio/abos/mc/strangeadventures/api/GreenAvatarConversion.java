package studio.abos.mc.strangeadventures.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.world.level.block.Block;

public record GreenAvatarConversion(HolderSet<Block> input, HolderSet<Block> output) {

    public static final Codec<GreenAvatarConversion> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.holderSet(BuiltInRegistries.BLOCK.key()).fieldOf("input").forGetter(GreenAvatarConversion::input),
            RegistryCodecs.holderSet(BuiltInRegistries.BLOCK.key()).fieldOf("output").forGetter(GreenAvatarConversion::output)
    ).apply(instance, GreenAvatarConversion::new));

}
