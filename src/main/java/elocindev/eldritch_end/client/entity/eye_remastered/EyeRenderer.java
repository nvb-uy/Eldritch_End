package elocindev.eldritch_end.client.entity.eye_remastered;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.Identifier;

public class EyeRenderer extends AzEntityRenderer<EyeEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/eye.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/eyetexture.png");

    public EyeRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<EyeEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(EyeAnimator::new)
                .setScale(3.0F)
                .build(), context);
    }

    @Override
    public void render(EyeEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        // The old renderer pitched only the root bone; this pitches the whole model around its origin.
        poseStack.push();
        poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-entity.getPitch(partialTick)));
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pop();
    }
}
