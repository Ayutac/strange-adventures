package studio.abos.mc.strangeadventures.neoforge.platform;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import studio.abos.mc.strangeadventures.platform.ModPlatform;

public class ModPlatformNeoForge implements ModPlatform {

    @Override
    public void c2s(final CustomPacketPayload payload) {
        ClientPacketDistributor.sendToServer(payload);
    }

}
