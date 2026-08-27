package studio.abos.mc.strangeadventures.client.entityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import studio.abos.mc.strangeadventures.entity.SpikyCactusEntity;

public class SpikyCactusEntityRenderer extends EntityRenderer<SpikyCactusEntity, SpikyCactusEntityRendererState> {

    public SpikyCactusEntityRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public SpikyCactusEntityRendererState createRenderState() {
        return new SpikyCactusEntityRendererState();
    }

    @Override
    public void extractRenderState(final SpikyCactusEntity entity, final SpikyCactusEntityRendererState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(final SpikyCactusEntityRendererState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
