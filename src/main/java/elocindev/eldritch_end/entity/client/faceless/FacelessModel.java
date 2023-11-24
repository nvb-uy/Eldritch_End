package elocindev.eldritch_end.entity.client.faceless;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.faceless.FacelessEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class FacelessModel extends GeoModel<FacelessEntity> {
    @Override
    public Identifier getModelResource(FacelessEntity object) {
        return new Identifier(EldritchEnd.MODID, "geo/faceless.geo.json");
    }

    @Override
    public Identifier getTextureResource(FacelessEntity object) {
        return new Identifier(EldritchEnd.MODID, "textures/entity/faceless.png");
    }

    @Override
    public Identifier getAnimationResource(FacelessEntity animatable) {
        return new Identifier(EldritchEnd.MODID, "animations/faceless.animation.json");
    }
}
