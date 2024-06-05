package elocindev.eldritch_end.entity.client.ominous_eye;

import elocindev.eldritch_end.entity.ominous_eye.OminousEyeEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class OminousEyeRenderer extends GeoEntityRenderer<OminousEyeEntity> {
    public OminousEyeRenderer(EntityRendererFactory.Context context) {
        super(context, new OminousEyeModel());
    }
}