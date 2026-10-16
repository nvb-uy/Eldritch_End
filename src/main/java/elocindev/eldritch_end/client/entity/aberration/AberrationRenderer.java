package elocindev.eldritch_end.client.entity.aberration;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class AberrationRenderer extends AzEntityRenderer<AberrationEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/aberration.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/texture_aberration.png");

    public AberrationRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<AberrationEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(AberrationAnimator::new)
                .build(), context);
    }
}
