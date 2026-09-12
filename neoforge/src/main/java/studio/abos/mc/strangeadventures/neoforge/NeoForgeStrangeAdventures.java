package studio.abos.mc.strangeadventures.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import studio.abos.mc.strangeadventures.StrangeAdventures;

@Mod(StrangeAdventures.MOD_ID)
public final class NeoForgeStrangeAdventures {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, StrangeAdventures.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> SAP_FLUID_TYPE = FLUID_TYPES.register("sap_fluid", () -> new FluidType(FluidType.Properties.create()));

    public NeoForgeStrangeAdventures(final ModContainer modContainer, final IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        FLUID_TYPES.register(modEventBus);
        Balm.initializeMod(StrangeAdventures.MOD_ID, context, StrangeAdventures::initialize);
    }

}
