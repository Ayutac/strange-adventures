package studio.abos.mc.strangeadventures.client.entityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.ResolvableProfile;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.entity.GreenAvatarCloneEntity;

public class GreenAvatarCloneEntityRenderer extends LivingEntityRenderer<GreenAvatarCloneEntity, GreenAvatarCloneEntityRendererState, PlayerModel> {

    @Nullable
    protected PlayerSkinRenderCache skinCache;

    public GreenAvatarCloneEntityRenderer(final EntityRendererProvider.Context context) {
        super(context, new PlayerModel(context.bakeLayer(ModelLayers.PLAYER), false), 0.7f);
    }

    @Override
    public GreenAvatarCloneEntityRendererState createRenderState() {
        return new GreenAvatarCloneEntityRendererState();
    }

    @Override
    public void extractRenderState(final GreenAvatarCloneEntity entity, final GreenAvatarCloneEntityRendererState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        if (skinCache == null) {
            skinCache = Minecraft.getInstance().playerSkinRenderCache();
        }
        if (entity.getOwner() instanceof final Player player) {
            state.skin = skinCache.getOrDefault(ResolvableProfile.createResolved(player.getGameProfile())).playerSkin();
        }
    }

    @Override
    protected boolean shouldShowName(final GreenAvatarCloneEntity entity, final double distanceToCameraSq) {
        return super.shouldShowName(entity, distanceToCameraSq) && entity.hasCustomName();
    }

    @Override
    public void submit(final GreenAvatarCloneEntityRendererState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public Identifier getTextureLocation(final GreenAvatarCloneEntityRendererState state) {
        return state.skin.body().id();
    }

}
