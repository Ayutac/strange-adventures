package studio.abos.mc.strangeadventures.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public record ServerboundUprootPayload() implements CustomPacketPayload {

    public static final Identifier ID = StrangeAdventures.id("up");

    public static final Type<ServerboundUprootPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final ServerboundUprootPayload INSTANCE = new ServerboundUprootPayload();

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundUprootPayload> CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<ServerboundUprootPayload> type() {
        return TYPE;
    }

}
