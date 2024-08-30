package elocindev.eldritch_end.client.entity.ominous_eye;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.ominous_eye.OminousEyeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class OminousEyeRenderer extends EntityRenderer<OminousEyeEntity> {
    private static final Identifier TEXTURE_ID = new Identifier(EldritchEnd.MODID, "textures/entity/ominous_eye.png");
    private static final Identifier MODEL_ID = new Identifier(EldritchEnd.MODID, "entity/ominous_eye");
    private final BakedModel model;

    public OminousEyeRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model = ctx.getModelManager().getModel(MODEL_ID);
    }

    @Override
    public void render(OminousEyeEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

        matrices.push();
        {
            var headYaw = MathHelper.lerp(tickDelta, entity.prevHeadYaw, entity.headYaw);
            var pitch = entity.getPitch(tickDelta);
            matrices.translate(0.0, 0.5, 0.0);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f - headYaw));
            matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(pitch));
            renderBakedModel(model, entity.getRandom(), light, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()));
        }
        matrices.pop();
    }

    @Override
    public Identifier getTexture(OminousEyeEntity entity) {
        return TEXTURE_ID;
    }

    private static void renderBakedModel(
        BakedModel model,
        Random random,
        int light,
        MatrixStack matrices,
        VertexConsumer buffer
    ) {
        for (var direction : Direction.values()) {
            var quads = model.getQuads(null, direction, random);
            renderBakedQuads(quads, matrices, buffer, light);
        }

        var noDirectionQuads = model.getQuads(null, null, random);
        renderBakedQuads(noDirectionQuads, matrices, buffer, light);
    }

    private static void renderBakedQuads(
        List<BakedQuad> quads,
        MatrixStack matrices,
        VertexConsumer buffer,
        int light
    ) {
        for (var quad : quads) {
            buffer.quad(
                matrices.peek(),
                quad,
                new float[]{1.0F, 1.0F, 1.0F, 1.0F},
                1f, 1f, 1f,
                new int[]{light, light, light, light},
                OverlayTexture.DEFAULT_UV,
                false
            );
        }
    }
}