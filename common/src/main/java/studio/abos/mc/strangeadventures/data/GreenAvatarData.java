package studio.abos.mc.strangeadventures.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.With;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

@With
public record GreenAvatarData(boolean active) {

    public static final Codec<GreenAvatarData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("active").forGetter(GreenAvatarData::active)
        ).apply(instance, GreenAvatarData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, GreenAvatarData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, GreenAvatarData::active,
            GreenAvatarData::new
    );

    public GreenAvatarData() {
        this(false);
    }

}
