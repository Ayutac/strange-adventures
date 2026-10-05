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
                        new Material(StrangeAdventures.id("block/acacia_sap_still")),
                        new Material(StrangeAdventures.id("block/acacia_sap_flow")),
                        null, null),
                ModFluids.ACACIA_SAP_STILL.value(), ModFluids.ACACIA_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/birch_sap_still")),
                        new Material(StrangeAdventures.id("block/birch_sap_flow")),
                        null, null),
                ModFluids.BIRCH_SAP_STILL.value(), ModFluids.BIRCH_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/cactus_sap_still")),
                        new Material(StrangeAdventures.id("block/cactus_sap_flow")),
                        null, null),
                ModFluids.CACTUS_SAP_STILL.value(), ModFluids.CACTUS_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/cherry_sap_still")),
                        new Material(StrangeAdventures.id("block/cherry_sap_flow")),
                        null, null),
                ModFluids.CHERRY_SAP_STILL.value(), ModFluids.CHERRY_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/jungle_sap_still")),
                        new Material(StrangeAdventures.id("block/jungle_sap_flow")),
                        null, null),
                ModFluids.JUNGLE_SAP_STILL.value(), ModFluids.JUNGLE_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/mangrove_sap_still")),
                        new Material(StrangeAdventures.id("block/mangrove_sap_flow")),
                        null, null),
                ModFluids.MANGROVE_SAP_STILL.value(), ModFluids.MANGROVE_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/oak_sap_still")),
                        new Material(StrangeAdventures.id("block/oak_sap_flow")),
                        null, null),
                ModFluids.OAK_SAP_STILL.value(), ModFluids.OAK_SAP_FLOWING.value());
        event.register(new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/spruce_sap_still")),
                        new Material(StrangeAdventures.id("block/spruce_sap_flow")),
                        null, null),
                ModFluids.SPRUCE_SAP_STILL.value(), ModFluids.SPRUCE_SAP_FLOWING.value());
    }

}
