package studio.abos.mc.strangeadventures.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public record ServerboundGreenAvatarAttemptSplitPayload() implements CustomPacketPayload {

    public static final Identifier ID = StrangeAdventures.id("split");

    public static final CustomPacketPayload.Type<ServerboundGreenAvatarAttemptSplitPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final ServerboundGreenAvatarAttemptSplitPayload INSTANCE = new ServerboundGreenAvatarAttemptSplitPayload();

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundGreenAvatarAttemptSplitPayload> CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public CustomPacketPayload.Type<ServerboundGreenAvatarAttemptSplitPayload> type() {
        return TYPE;
    }

}
