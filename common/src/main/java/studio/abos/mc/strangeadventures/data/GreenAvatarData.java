package studio.abos.mc.strangeadventures.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.With;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;

@With
public record GreenAvatarData(boolean active, float mass, int lastHurtTick, int regenerationTick) {

    public static final Codec<GreenAvatarData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("active").forGetter(GreenAvatarData::active),
            Codec.FLOAT.fieldOf("mass").forGetter(GreenAvatarData::mass),
            Codec.INT.fieldOf("lastHurtTick").forGetter(GreenAvatarData::lastHurtTick),
            Codec.INT.fieldOf("regenerationTick").forGetter(GreenAvatarData::regenerationTick)
        ).apply(instance, GreenAvatarData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, GreenAvatarData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, GreenAvatarData::active,
            ByteBufCodecs.FLOAT, GreenAvatarData::mass,
            ByteBufCodecs.INT, GreenAvatarData::lastHurtTick,
            ByteBufCodecs.INT, GreenAvatarData::regenerationTick,
            GreenAvatarData::new
    );

    public GreenAvatarData() {
        this(false, 1f, 0, 0);
    }

    public GreenAvatarData withRegenerationReset() {
        return new GreenAvatarData(active, mass, 0, 0);
    }

    public GreenAvatarData withIncreasedRegenerationTicks() {
        if (lastHurtTick > StrangeAdventuresApi.GREEN_AVATAR_REGEN_COOLDOWN) {
            if (regenerationTick >= StrangeAdventuresApi.GREEN_AVATAR_REGEN_DURATION) {
                return new GreenAvatarData(active, mass, lastHurtTick + 1, 0);
            }
            return new GreenAvatarData(active, mass, lastHurtTick + 1, regenerationTick + 1);
        }
        return new GreenAvatarData(active, mass, lastHurtTick + 1, 0);
    }
}
