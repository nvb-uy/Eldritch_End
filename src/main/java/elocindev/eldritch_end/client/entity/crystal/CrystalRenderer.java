package elocindev.eldritch_end.client.entity.crystal;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.crystal_entity.CrystalEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.Identifier;

public class CrystalRenderer extends AzEntityRenderer<CrystalEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/crystal.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/crystaltexture.png");

    public CrystalRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<CrystalEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(CrystalAnimator::new)
                .build(), context);
    }

    @Override
    public void render(CrystalEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        // Hidden for the first ticks, while the spawn animation is not ready yet.
        if (entity.age <= 2) {
            return;
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
