package elocindev.eldritch_end.client.entity.faceless;

import elocindev.eldritch_end.entity.faceless.FacelessEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class FacelessRenderer extends GeoEntityRenderer<FacelessEntity> {
    public FacelessRenderer(EntityRendererFactory.Context context) {
        super(context, new FacelessModel());
    }
}