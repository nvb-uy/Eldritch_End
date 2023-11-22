package elocindev.eldritch_end.entity.client.dendler;

import elocindev.eldritch_end.entity.dendler.DendlerEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class DendlerRenderer extends GeoEntityRenderer<DendlerEntity> {
    public DendlerRenderer(EntityRendererFactory.Context context) {
        super(context, new DendlerModel());
    }
}