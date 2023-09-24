package elocindev.eldritch_end.entity.client.undead_tentacle;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class UndeadTentacleRenderer extends GeoEntityRenderer<TentacleEntity> {
    public UndeadTentacleRenderer(EntityRendererFactory.Context context) {
        super(context, new UndeadTentacleModel());
    }
}