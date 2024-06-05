package elocindev.eldritch_end.entity.client.ominous_eye;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.ominous_eye.OminousEyeEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class OminousEyeModel extends GeoModel<OminousEyeEntity> {
    @Override
    public Identifier getModelResource(OminousEyeEntity object) {
        return new Identifier(EldritchEnd.MODID, "geo/ominous_eye.geo.json");
    }

    @Override
    public Identifier getTextureResource(OminousEyeEntity object) {
        return new Identifier(EldritchEnd.MODID, "textures/entity/ominous_eye.png");
    }

    @Override
    public Identifier getAnimationResource(OminousEyeEntity animatable) {
        return new Identifier(EldritchEnd.MODID, "animations/faceless.animation.json");
    }
}
