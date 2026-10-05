package studio.abos.mc.strangeadventures.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.client.StrangeAdventuresClient;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

public class FabricStrangeAdventuresClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(StrangeAdventures.MOD_ID, FabricLoadContext.INSTANCE, StrangeAdventuresClient::initialize);
        registerFluidRenderers();
    }

    private static void registerFluidRenderers() {
        FluidRenderingRegistry.register(
                ModFluids.ACACIA_SAP_STILL.value(), ModFluids.ACACIA_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/acacia_sap_still")),
                        new Material(StrangeAdventures.id("block/acacia_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.BIRCH_SAP_STILL.value(), ModFluids.BIRCH_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/birch_sap_still")),
                        new Material(StrangeAdventures.id("block/birch_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.CACTUS_SAP_STILL.value(), ModFluids.CACTUS_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/cactus_sap_still")),
                        new Material(StrangeAdventures.id("block/cactus_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.CHERRY_SAP_STILL.value(), ModFluids.CHERRY_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/cherry_sap_still")),
                        new Material(StrangeAdventures.id("block/cherry_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.JUNGLE_SAP_STILL.value(), ModFluids.JUNGLE_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/jungle_sap_still")),
                        new Material(StrangeAdventures.id("block/jungle_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.MANGROVE_SAP_STILL.value(), ModFluids.MANGROVE_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/mangrove_sap_still")),
                        new Material(StrangeAdventures.id("block/mangrove_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.OAK_SAP_STILL.value(), ModFluids.OAK_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/oak_sap_still")),
                        new Material(StrangeAdventures.id("block/oak_sap_flow")),
                        null, null)
        );
        FluidRenderingRegistry.register(
                ModFluids.SPRUCE_SAP_STILL.value(), ModFluids.SPRUCE_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/spruce_sap_still")),
                        new Material(StrangeAdventures.id("block/spruce_sap_flow")),
                        null, null)
        );
    }

}
