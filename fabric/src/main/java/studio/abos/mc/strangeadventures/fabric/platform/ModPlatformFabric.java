package studio.abos.mc.strangeadventures.fabric.platform;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import studio.abos.mc.strangeadventures.platform.ModPlatform;

public class ModPlatformFabric implements ModPlatform {

    @Override
    public void c2s(final CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }

}
