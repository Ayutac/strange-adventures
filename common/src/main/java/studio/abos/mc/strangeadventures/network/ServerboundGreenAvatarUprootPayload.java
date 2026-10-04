package studio.abos.mc.strangeadventures.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public record ServerboundGreenAvatarUprootPayload() implements CustomPacketPayload {

    public static final Identifier ID = StrangeAdventures.id("up");

    public static final Type<ServerboundGreenAvatarUprootPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final ServerboundGreenAvatarUprootPayload INSTANCE = new ServerboundGreenAvatarUprootPayload();

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundGreenAvatarUprootPayload> CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<ServerboundGreenAvatarUprootPayload> type() {
        return TYPE;
    }

}
