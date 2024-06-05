package elocindev.eldritch_end.client.entity.hastur;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.hastur.HasturEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class HasturModel extends GeoModel<HasturEntity> {
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
