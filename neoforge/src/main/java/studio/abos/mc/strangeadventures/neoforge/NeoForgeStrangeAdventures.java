package studio.abos.mc.strangeadventures.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import studio.abos.mc.strangeadventures.StrangeAdventures;

@Mod(StrangeAdventures.MOD_ID)
public class NeoForgeStrangeAdventures {

    public NeoForgeStrangeAdventures(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(StrangeAdventures.MOD_ID, context, StrangeAdventures::initialize);
    }
}
