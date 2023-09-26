package elocindev.eldritch_end.entity.client.undead_tentacle;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class UndeadTentacleRenderer extends GeoEntityRenderer<TentacleEntity> {
    public UndeadTentacleRenderer(EntityRendererFactory.Context context) {
        super(context, new UndeadTentacleModel());
    }
}