package elocindev.eldritch_end.entity.client.tentacle;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TentacleModel extends AnimatedGeoModel<TentacleEntity> {
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
