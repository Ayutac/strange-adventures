package studio.abos.mc.strangeadventures.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ClientModInitializer;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.client.StrangeAdventuresClient;

public class FabricStrangeAdventuresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(StrangeAdventures.MOD_ID, FabricLoadContext.INSTANCE, StrangeAdventuresClient::initialize);
    }
}
