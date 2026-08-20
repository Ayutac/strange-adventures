package studio.abos.mc.strangeadventures.neoforge.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.client.StrangeAdventuresClient;

@Mod(value = StrangeAdventures.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeStrangeAdventuresClient {

    public NeoForgeStrangeAdventuresClient(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        BalmClient.initializeMod(StrangeAdventures.MOD_ID, context, StrangeAdventuresClient::initialize);
    }
}
