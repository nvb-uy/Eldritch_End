package elocindev.eldritch_end.client.entity.eye_remastered;


import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.cache.object.GeoBone;
import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.renderer.DynamicGeoEntityRenderer;
import mod.azure.azurelib.util.RenderUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

import java.util.List;

public class EyeRenderer<T extends EyeEntity> extends DynamicGeoEntityRenderer<T> {

    GeoModel<T> model;
    public EyeRenderer(EntityRendererFactory.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
        this.model = (GeoModel<T>) model;
    }

    @Override
    public void actuallyRender(MatrixStack poseStack, T animatable, BakedGeoModel model, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

    }

    @Override
    public void preRender(MatrixStack poseStack, T animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        this.scaleHeight = 3;
        this.scaleWidth = 3;

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }

    @Override
    public void renderRecursively(MatrixStack poseStack, T animatable, GeoBone bone, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            poseStack.push();
            if(bone.getParent()== null) {
                poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-animatable.getPitch(partialTick)));

            }
        RenderUtils.translateMatrixToBone(poseStack, bone);
            RenderUtils.translateToPivotPoint(poseStack, bone);
            RenderUtils.rotateMatrixAroundBone(poseStack, bone);
            RenderUtils.scaleMatrixForBone(poseStack, bone);

            if (bone.isTrackingMatrices()) {
                Matrix4f poseState = new Matrix4f(poseStack.peek().getPositionMatrix());
                Matrix4f localMatrix = RenderUtils.invertAndMultiplyMatrices(poseState, this.entityRenderTranslations);
                bone.setModelSpaceMatrix(RenderUtils.invertAndMultiplyMatrices(poseState, this.modelRenderTranslations));
                bone.setLocalSpaceMatrix(RenderUtils.translateMatrix(localMatrix, this.getPositionOffset(this.animatable, 1.0F).toVector3f()));
                bone.setWorldSpaceMatrix(RenderUtils.translateMatrix(new Matrix4f(localMatrix), this.animatable.getPos().toVector3f()));
            }

            RenderUtils.translateAwayFromPivotPoint(poseStack, bone);
            this.textureOverride = this.getTextureOverrideForBone(bone, this.animatable, partialTick);
            Identifier texture = this.textureOverride == null ? this.getTexture(this.animatable) : this.textureOverride;
            RenderLayer renderTypeOverride = this.getRenderTypeOverrideForBone(bone, this.animatable, texture, bufferSource, partialTick);
            if (texture != null && renderTypeOverride == null) {
                renderTypeOverride = this.getRenderType(this.animatable, texture, bufferSource, partialTick);
            }

            if (renderTypeOverride != null) {
                buffer = bufferSource.getBuffer(renderTypeOverride);
            }

            if (!this.boneRenderOverride(poseStack, bone, bufferSource, buffer, partialTick, packedLight, packedOverlay, red, green, blue, alpha)) {
                super.renderCubesOfBone(poseStack, bone, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }

            if (renderTypeOverride != null) {
                buffer = bufferSource.getBuffer(this.getRenderType(this.animatable, this.getTexture(this.animatable), bufferSource, partialTick));
            }

            if (!isReRender) {
                this.applyRenderLayersForBone(poseStack, animatable, bone, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
            }

            super.renderChildBones(poseStack, animatable, bone, renderType, bufferSource, buffer, false, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.pop();


    }

    @Override
    public void postRender(MatrixStack poseStack, T animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        super.postRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected void applyRotations(T animatable, MatrixStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        List<PlayerEntity> list =  animatable.getWorld().getTargets(PlayerEntity.class, TargetPredicate.DEFAULT,animatable,animatable.getBoundingBox().expand(12));

        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);

    }




}