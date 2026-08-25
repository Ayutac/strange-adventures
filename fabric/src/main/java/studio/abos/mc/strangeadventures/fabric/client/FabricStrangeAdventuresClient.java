package studio.abos.mc.strangeadventures.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.client.StrangeAdventuresClient;
import studio.abos.mc.strangeadventures.client.blockrenderer.EssenceCauldronBlockEntityRenderer;

public class FabricStrangeAdventuresClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(StrangeAdventures.MOD_ID, FabricLoadContext.INSTANCE, StrangeAdventuresClient::initialize);
        BlockEntityRenderers.register(ModBlockEntities.ESSENCE_CAULDRON.value(), EssenceCauldronBlockEntityRenderer::new);
    }

}
