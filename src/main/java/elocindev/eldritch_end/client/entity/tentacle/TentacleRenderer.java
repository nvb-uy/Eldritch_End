package elocindev.eldritch_end.client.entity.tentacle;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class TentacleRenderer extends GeoEntityRenderer<TentacleEntity> {
    public TentacleRenderer(EntityRendererFactory.Context context) {
        super(context, new TentacleModel());
    }
}