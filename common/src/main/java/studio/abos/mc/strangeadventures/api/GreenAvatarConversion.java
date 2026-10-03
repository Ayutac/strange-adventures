package studio.abos.mc.strangeadventures.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record GreenAvatarConversion(HolderSet<Block> input, HolderSet<Block> output) {

    public static final Codec<GreenAvatarConversion> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.holderSet(BuiltInRegistries.BLOCK.key()).fieldOf("input").forGetter(GreenAvatarConversion::input),
            RegistryCodecs.holderSet(BuiltInRegistries.BLOCK.key()).fieldOf("output").forGetter(GreenAvatarConversion::output)
    ).apply(instance, GreenAvatarConversion::new));

    public static boolean convert(final ServerLevel level, final BlockPos pos) {
        final BlockState block = level.getBlockState(pos);
        final var registry = level.registryAccess().lookupOrThrow(StrangeAdventuresApi.GREEN_AVATAR_CONVERSION_REGISTRY_KEY);
        final var conversionOptional = registry.stream()
                .filter(conversion -> block.is(conversion.input))
                .findFirst();
        if (conversionOptional.isEmpty()) {
            return false;
        }
        final GreenAvatarConversion conversion = conversionOptional.get();
        level.setBlockAndUpdate(pos, conversion.output.getRandomElement(level.getRandom()).get().value().defaultBlockState());
        return true;
    }

}
