package studio.abos.mc.strangeadventures.client.entityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import studio.abos.mc.strangeadventures.entity.HinderingRootsProjectile;

public class HinderingRootsProjectileRenderer extends EntityRenderer<HinderingRootsProjectile, HinderingRootsProjectileRendererState> {

    public HinderingRootsProjectileRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public HinderingRootsProjectileRendererState createRenderState() {
        return new HinderingRootsProjectileRendererState();
    }

    @Override
    public void extractRenderState(final HinderingRootsProjectile entity, final HinderingRootsProjectileRendererState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(final HinderingRootsProjectileRendererState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
