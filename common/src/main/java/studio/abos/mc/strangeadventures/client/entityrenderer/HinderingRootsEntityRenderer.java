package studio.abos.mc.strangeadventures.client.entityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import studio.abos.mc.strangeadventures.entity.HinderingRootsEntity;

public class HinderingRootsEntityRenderer extends EntityRenderer<HinderingRootsEntity, HinderingRootsEntityRendererState> {

    public HinderingRootsEntityRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public HinderingRootsEntityRendererState createRenderState() {
        return new HinderingRootsEntityRendererState();
    }

    @Override
    public void extractRenderState(final HinderingRootsEntity entity, final HinderingRootsEntityRendererState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(final HinderingRootsEntityRendererState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
