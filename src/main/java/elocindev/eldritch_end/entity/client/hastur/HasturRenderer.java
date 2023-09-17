package elocindev.eldritch_end.entity.client.hastur;

import elocindev.eldritch_end.entity.hastur.HasturEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HasturRenderer extends GeoEntityRenderer<HasturEntity> {
    public HasturRenderer(EntityRendererFactory.Context context) {
        super(context, new HasturModel());
    }
}