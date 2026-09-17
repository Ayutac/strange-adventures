package studio.abos.mc.strangeadventures.neoforge.client;

import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

@EventBusSubscriber(modid = StrangeAdventures.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerFluidModels(final RegisterFluidModelsEvent event) {
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/birch_sap_still")),
                        new Material(StrangeAdventures.id("block/birch_sap_flow")),
                        null, null),
                ModFluids.BIRCH_SAP_STILL.value(), ModFluids.BIRCH_SAP_FLOWING.value());
    }

}
