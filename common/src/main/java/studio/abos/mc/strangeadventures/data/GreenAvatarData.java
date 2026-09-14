package studio.abos.mc.strangeadventures.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GreenAvatarData {

    public static final Codec<GreenAvatarData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("active").forGetter(GreenAvatarData::isActive)
        ).apply(instance, GreenAvatarData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, GreenAvatarData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, GreenAvatarData::isActive,
            GreenAvatarData::new
    );

    protected boolean active;

}
