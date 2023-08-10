package elocindev.eldritch_end.entity.abberation;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AberrationRenderer extends GeoEntityRenderer<AberrationEntity> {
    public AberrationRenderer(EntityRendererFactory.Context context) {
        super(context, new AberrationModel());
    }
}