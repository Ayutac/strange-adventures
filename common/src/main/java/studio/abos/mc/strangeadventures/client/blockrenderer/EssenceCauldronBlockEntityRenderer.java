package studio.abos.mc.strangeadventures.client.blockrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.FluidStateModelSet;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;

import java.util.Arrays;
import java.util.List;

public class EssenceCauldronBlockEntityRenderer implements BlockEntityRenderer<EssenceCauldronBlockEntity, EssenceCauldronBlockEntityRenderState> {

    public static final float MIN_XY = 2 / 16f;
    public static final float MAX_XY = 14 / 16f;
    public static final float MIN_X_2_1 = MIN_XY;
    public static final float MAX_X_2_1 = 8 / 16f;
    public static final float MIN_X_2_2 = MAX_X_2_1;
    public static final float MAX_X_2_2 = MAX_XY;
    public static final float MIN_X_3_1 = MIN_XY;
    public static final float MAX_X_3_1 = 1 / 3f;
    public static final float MIN_X_3_2 = MAX_X_3_1;
    public static final float MAX_X_3_2 = 2 / 3f;
    public static final float MIN_X_3_3 = MAX_X_3_2;
    public static final float MAX_X_3_3 = MAX_XY;
    public static final float[] Y = new float[]{7 / 16f, 11 / 16f, 15 / 16f};

    private final FluidStateModelSet fluidModels;
    private final ItemModelResolver itemModels;

    public EssenceCauldronBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemModels = context.itemModelResolver();
        fluidModels = Minecraft.getInstance().getModelManager().getFluidStateModelSet();
    }

    @Override
    public EssenceCauldronBlockEntityRenderState createRenderState() {
        return new EssenceCauldronBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(final EssenceCauldronBlockEntity blockEntity, final EssenceCauldronBlockEntityRenderState renderState, final float partialTicks, final Vec3 cameraPosition, final ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTicks, cameraPosition, breakProgress);
        if (!(blockEntity.getLevel() instanceof final ClientLevel level)) {
            return;
        }
        // prepare items
        final List<ItemStack> items = blockEntity.getItems();
        final long gameTime = level.getGameTime();
        final var itemRenderStates = new EssenceCauldronBlockEntityRenderState.ItemRenderState[items.size()];
        for (int i = 0; i < items.size(); i++) {
            final ItemStack item = items.get(i);
            if (!item.isEmpty()) {
                final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
                itemModels.updateForTopItem(itemStackRenderState, item, ItemDisplayContext.FIXED, level, null, 0);
                itemRenderStates[i] = new EssenceCauldronBlockEntityRenderState.ItemRenderState(itemStackRenderState, new Vec3(0.5, 0.5, 0));
            }
        }
        Arrays.sort(itemRenderStates, EssenceCauldronBlockEntityRenderState.ITEM_NULL_LAST_COMPARATOR);
        renderState.setItems(itemRenderStates);
        // prepare fluids
        final EssenceCauldronBlockEntity.Tank tank = blockEntity.getFluidTank();
        final int slotCount = tank.getSlotCount();
        final var fluidRenderStates = new EssenceCauldronBlockEntityRenderState.FluidRenderState[slotCount];
        for (int i = 0; i < slotCount; i++) {
            final Fluid fluid = tank.getFluid(i);
            if (!fluid.isSame(Fluids.EMPTY)) {
                final FluidState fluidState = fluid.defaultFluidState();
                final var fluidTintSource = fluidModels.get(fluidState).tintSource();
                fluidRenderStates[i] = new EssenceCauldronBlockEntityRenderState.FluidRenderState(
                        fluidModels.get(fluidState).stillMaterial().sprite(),
                        fluidTintSource != null ? fluidTintSource.colorInWorld(fluidState.createLegacyBlock(), level, blockEntity.getBlockPos()) : -1
                );
            }
        }
        Arrays.sort(fluidRenderStates, EssenceCauldronBlockEntityRenderState.FLUID_NULL_LAST_COMPARATOR);
        renderState.setFluids(fluidRenderStates);
        // prepare rest
        final int amountOfBottles = tank.getAmountOfBottles();
        renderState.setHeight(amountOfBottles == 0 ? 0f : Y[amountOfBottles-1]);
        final Direction direction = blockEntity.getBlockState().getValue(HorizontalDirectionalBlock.FACING);
        renderState.setRotation(switch (direction) {
            case EAST -> 90f;
            case SOUTH -> 180f;
            case WEST -> 270f;
            default -> 0f;
        });
    }

    @Override
    public void submit(final EssenceCauldronBlockEntityRenderState renderState, final PoseStack poseStack, final SubmitNodeCollector queue, final CameraRenderState cameraRenderState) {
        final float height = renderState.getHeight();
        if (height == 0f) {
            return;
        }
        final var fluids = renderState.getFluids();
        // count fluids (assumed they are ordered with nulls last)
        int count = 0;
        for (int i = 0; i < fluids.length; i++) {
            if (fluids[i] != null) {
                count++;
            }
            else {
                break;
            }
        }
        final int fluidCount = count;
        // center everything for rotation and back
        poseStack.pushPose();
        poseStack.translate(0.5f, 0f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(renderState.getRotation()));
        poseStack.translate(-0.5f, 0f, -0.5f);
        final int light = renderState.lightCoords;
        // draw items
        for (final var itemRenderState : renderState.getItems()) {
            if (itemRenderState == null) { // the compiler warning is wrong
                continue;
            }
            poseStack.pushPose();
            poseStack.translate(itemRenderState.relPos());
            itemRenderState.renderState().submit(poseStack, queue, light, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
        // draw fluids
        queue.submitCustomGeometry(poseStack, RenderTypes.translucentMovingBlock(), (pose, consumer) -> {
            if (fluidCount == 1) {
                drawOneFluid(pose, consumer, fluids[0], light, height);
            }
            else if (fluidCount == 2) {
                drawTwoFluids(pose, consumer, fluids[0], fluids[1], light, height);
            }
            else {
                drawThreeFluids(pose, consumer, fluids[0], fluids[1], fluids[2], light, height);
            }
        });
        poseStack.popPose();
    }

    private static void drawOneFluid(final PoseStack.Pose pose, final VertexConsumer consumer, final EssenceCauldronBlockEntityRenderState.FluidRenderState renderState, final int light, final float height) {
        final float minU = renderState.sprite().getU(MIN_XY);
        final float maxU = renderState.sprite().getU(MAX_XY);
        final float minV = renderState.sprite().getV(MIN_XY);
        final float maxV = renderState.sprite().getV(MAX_XY);
        final int tint = renderState.tint();
        consumer.addVertex(pose, MIN_XY, height, MIN_XY)
                .setColor(tint)
                .setUv(minU, minV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MIN_XY, height, MAX_XY)
                .setColor(tint)
                .setUv(minU, maxV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_XY, height, MAX_XY)
                .setColor(tint)
                .setUv(maxU, maxV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_XY, height, MIN_XY)
                .setColor(tint)
                .setUv(maxU, minV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
    }

    private static void drawTwoFluids(final PoseStack.Pose pose, final VertexConsumer consumer, final EssenceCauldronBlockEntityRenderState.FluidRenderState renderState1, final EssenceCauldronBlockEntityRenderState.FluidRenderState renderState2, final int light, final float height) {
        final float minU1 = renderState1.sprite().getU(MIN_X_2_1);
        final float maxU1 = renderState1.sprite().getU(MAX_X_2_1);
        final float minV1 = renderState1.sprite().getV(MIN_XY);
        final float maxV1 = renderState1.sprite().getV(MAX_XY);
        final int tint1 = renderState1.tint();
        consumer.addVertex(pose, MIN_X_2_1, height, MIN_XY)
                .setColor(tint1)
                .setUv(minU1, minV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MIN_X_2_1, height, MAX_XY)
                .setColor(tint1)
                .setUv(minU1, maxV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_2_1, height, MAX_XY)
                .setColor(tint1)
                .setUv(maxU1, maxV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_2_1, height, MIN_XY)
                .setColor(tint1)
                .setUv(maxU1, minV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        final float minU2 = renderState2.sprite().getU(MIN_X_2_2);
        final float maxU2 = renderState2.sprite().getU(MAX_X_2_2);
        final float minV2 = renderState2.sprite().getV(MIN_XY);
        final float maxV2 = renderState2.sprite().getV(MAX_XY);
        final int tint2 = renderState2.tint();
        consumer.addVertex(pose, MIN_X_2_2, height, MIN_XY)
                .setColor(tint2)
                .setUv(minU2, minV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MIN_X_2_2, height, MAX_XY)
                .setColor(tint2)
                .setUv(minU2, maxV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_2_2, height, MAX_XY)
                .setColor(tint2)
                .setUv(maxU2, maxV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_2_2, height, MIN_XY)
                .setColor(tint2)
                .setUv(maxU2, minV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
    }

    private static void drawThreeFluids(final PoseStack.Pose pose, final VertexConsumer consumer, final EssenceCauldronBlockEntityRenderState.FluidRenderState renderState1, final EssenceCauldronBlockEntityRenderState.FluidRenderState renderState2, final EssenceCauldronBlockEntityRenderState.FluidRenderState renderState3, final int light, final float height) {
        final float minU1 = renderState1.sprite().getU(MIN_X_3_1);
        final float maxU1 = renderState1.sprite().getU(MAX_X_3_1);
        final float minV1 = renderState1.sprite().getV(MIN_XY);
        final float maxV1 = renderState1.sprite().getV(MAX_XY);
        final int tint1 = renderState1.tint();
        consumer.addVertex(pose, MIN_X_3_1, height, MIN_XY)
                .setColor(tint1)
                .setUv(minU1, minV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MIN_X_3_1, height, MAX_XY)
                .setColor(tint1)
                .setUv(minU1, maxV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_3_1, height, MAX_XY)
                .setColor(tint1)
                .setUv(maxU1, maxV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_3_1, height, MIN_XY)
                .setColor(tint1)
                .setUv(maxU1, minV1)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        final float minU2 = renderState2.sprite().getU(MIN_X_3_2);
        final float maxU2 = renderState2.sprite().getU(MAX_X_3_2);
        final float minV2 = renderState2.sprite().getV(MIN_XY);
        final float maxV2 = renderState2.sprite().getV(MAX_XY);
        final int tint2 = renderState2.tint();
        consumer.addVertex(pose, MIN_X_3_2, height, MIN_XY)
                .setColor(tint2)
                .setUv(minU2, minV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MIN_X_3_2, height, MAX_XY)
                .setColor(tint2)
                .setUv(minU2, maxV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_3_2, height, MAX_XY)
                .setColor(tint2)
                .setUv(maxU2, maxV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_3_2, height, MIN_XY)
                .setColor(tint2)
                .setUv(maxU2, minV2)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        final float minU3 = renderState3.sprite().getU(MIN_X_3_3);
        final float maxU3 = renderState3.sprite().getU(MAX_X_3_3);
        final float minV3 = renderState3.sprite().getV(MIN_XY);
        final float maxV3 = renderState3.sprite().getV(MAX_XY);
        final int tint3 = renderState3.tint();
        consumer.addVertex(pose, MIN_X_3_3, height, MIN_XY)
                .setColor(tint3)
                .setUv(minU3, minV3)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MIN_X_3_3, height, MAX_XY)
                .setColor(tint3)
                .setUv(minU3, maxV3)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_3_3, height, MAX_XY)
                .setColor(tint3)
                .setUv(maxU3, maxV3)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
        consumer.addVertex(pose, MAX_X_3_3, height, MIN_XY)
                .setColor(tint3)
                .setUv(maxU3, minV3)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, Direction.UP.getUnitVec3f());
    }

}
