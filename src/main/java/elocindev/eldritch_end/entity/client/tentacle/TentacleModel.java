package elocindev.eldritch_end.entity.client.tentacle;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class TentacleModel extends GeoModel<TentacleEntity> {
    @Override
    public Identifier getModelResource(TentacleEntity object) {
        return new Identifier(EldritchEnd.MODID, "geo/tentacle.geo.json");
    }

    @Override
    public Identifier getTextureResource(TentacleEntity object) {
        return new Identifier(EldritchEnd.MODID, "textures/entity/tentacle.png");
    }

    @Override
    public Identifier getAnimationResource(TentacleEntity animatable) {
        return new Identifier(EldritchEnd.MODID, "animations/tentacle.animation.json");
    }
}
