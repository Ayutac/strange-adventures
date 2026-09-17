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
        FluidRenderingRegistry.register(
                ModFluids.BIRCH_SAP_STILL.value(), ModFluids.BIRCH_SAP_FLOWING.value(),
                new FluidModel.Unbaked(
                        new Material(StrangeAdventures.id("block/birch_sap_still")),
                        new Material(StrangeAdventures.id("block/birch_sap_flow")),
                        null, null)
        );
    }

}
