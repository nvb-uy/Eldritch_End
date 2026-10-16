package elocindev.eldritch_end.client.entity.faceless;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.faceless.FacelessEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class FacelessRenderer extends AzEntityRenderer<FacelessEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/faceless.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/faceless.png");

    public FacelessRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<FacelessEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(FacelessAnimator::new)
                .build(), context);
    }
}
