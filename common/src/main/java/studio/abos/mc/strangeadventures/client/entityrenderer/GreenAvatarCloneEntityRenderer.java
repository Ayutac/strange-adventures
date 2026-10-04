package studio.abos.mc.strangeadventures.client.entityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import studio.abos.mc.strangeadventures.entity.GreenAvatarCloneEntity;

public class GreenAvatarCloneEntityRenderer extends EntityRenderer<GreenAvatarCloneEntity, GreenAvatarCloneEntityRendererState> {

    public GreenAvatarCloneEntityRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public GreenAvatarCloneEntityRendererState createRenderState() {
        return new GreenAvatarCloneEntityRendererState();
    }

    @Override
    public void extractRenderState(final GreenAvatarCloneEntity entity, final GreenAvatarCloneEntityRendererState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(final GreenAvatarCloneEntityRendererState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

}
