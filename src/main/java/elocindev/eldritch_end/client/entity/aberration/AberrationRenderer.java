package elocindev.eldritch_end.client.entity.aberration;

import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class AberrationRenderer extends GeoEntityRenderer<AberrationEntity> {
    public AberrationRenderer(EntityRendererFactory.Context context) {
        super(context, new AberrationModel());
    }
}