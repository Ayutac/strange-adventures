package studio.abos.mc.strangeadventures.client;

import net.blay09.mods.balm.client.BalmClientRegistrars;

public class StrangeAdventuresClient {

    public static void initialize(BalmClientRegistrars registrars) {
        registrars.blockEntityRenderers(ModRenderers::initialize);
        registrars.entityRenderers(ModRenderers::initialize);
        ModKeyMappings.initialize();
    }

}
