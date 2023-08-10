package elocindev.eldritch_end.entity.client.aberration;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AberrationModel extends AnimatedGeoModel<AberrationEntity> {
    @Override
    public Identifier getModelResource(AberrationEntity object) {
        return new Identifier(EldritchEnd.MODID, "geo/aberration.geo.json");
    }

    @Override
    public Identifier getTextureResource(AberrationEntity object) {
        return new Identifier(EldritchEnd.MODID, "textures/entity/texture_aberration.png");
    }

    @Override
    public Identifier getAnimationResource(AberrationEntity animatable) {
        return new Identifier(EldritchEnd.MODID, "animations/aberration.animation.json");
    }
}
