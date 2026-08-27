package studio.abos.mc.strangeadventures.client;

import net.blay09.mods.balm.client.renderer.blockentity.BalmBlockEntityRendererRegistrar;
import net.blay09.mods.balm.client.renderer.entity.BalmEntityRendererRegistrar;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.client.blockrenderer.EssenceCauldronBlockEntityRenderer;
import studio.abos.mc.strangeadventures.client.entityrenderer.SpikyCactusEntityRenderer;
import studio.abos.mc.strangeadventures.entity.ModEntityTypes;

public final class ModRenderers {

    public static void initialize(BalmEntityRendererRegistrar renderers) {
        renderers.register(ModEntityTypes.SPIKY_CACTUS, SpikyCactusEntityRenderer::new);
    }

    public static void initialize(BalmBlockEntityRendererRegistrar renderers) {
        renderers.register(ModBlockEntities.ESSENCE_CAULDRON, EssenceCauldronBlockEntityRenderer::new);
    }

}
