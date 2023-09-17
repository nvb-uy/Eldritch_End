package elocindev.eldritch_end.entity.client.hastur;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.hastur.HasturEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HasturModel extends AnimatedGeoModel<HasturEntity> {
    @Override
    public Identifier getModelResource(HasturEntity object) {
        return new Identifier(EldritchEnd.MODID, "geo/hastur.geo.json");
    }

    @Override
    public Identifier getTextureResource(HasturEntity object) {
        return new Identifier(EldritchEnd.MODID, "textures/entity/hastur.png");
    }

    @Override
    public Identifier getAnimationResource(HasturEntity animatable) {
        return new Identifier(EldritchEnd.MODID, "animations/hastur.animation.json");
    }
}
