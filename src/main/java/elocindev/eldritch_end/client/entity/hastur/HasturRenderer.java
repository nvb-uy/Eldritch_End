package elocindev.eldritch_end.client.entity.hastur;

import elocindev.eldritch_end.entity.hastur.HasturEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class HasturRenderer extends GeoEntityRenderer<HasturEntity> {
    public HasturRenderer(EntityRendererFactory.Context context) {
        super(context, new HasturModel());
    }
}