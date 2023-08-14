package elocindev.eldritch_end.entity.client.tentacle;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TentacleRenderer extends GeoEntityRenderer<TentacleEntity> {
    public TentacleRenderer(EntityRendererFactory.Context context) {
        super(context, new TentacleModel());
    }
}