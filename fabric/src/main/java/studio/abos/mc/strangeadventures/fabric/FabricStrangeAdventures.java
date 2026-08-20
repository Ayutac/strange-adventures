package studio.abos.mc.strangeadventures.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ModInitializer;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public class FabricStrangeAdventures implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(StrangeAdventures.MOD_ID, FabricLoadContext.INSTANCE, StrangeAdventures::initialize);
    }
}
