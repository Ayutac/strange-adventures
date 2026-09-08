package studio.abos.mc.strangeadventures.client.entityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import studio.abos.mc.strangeadventures.entity.TreeTransformatorProjectile;

public class TreeTransformatorProjectileRenderer extends EntityRenderer<TreeTransformatorProjectile, TreeTransformatorProjectileRendererState> {

    public TreeTransformatorProjectileRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public TreeTransformatorProjectileRendererState createRenderState() {
        return new TreeTransformatorProjectileRendererState();
    }

    @Override
    public void extractRenderState(final TreeTransformatorProjectile entity, final TreeTransformatorProjectileRendererState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(final TreeTransformatorProjectileRendererState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

}
