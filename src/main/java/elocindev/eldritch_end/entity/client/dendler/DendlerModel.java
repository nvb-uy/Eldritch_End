package elocindev.eldritch_end.entity.client.dendler;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.dendler.DendlerEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class DendlerModel extends GeoModel<DendlerEntity> {
    @Override
    public Identifier getModelResource(DendlerEntity object) {
        return new Identifier(EldritchEnd.MODID, "geo/dendler.geo.json");
    }

    @Override
    public Identifier getTextureResource(DendlerEntity object) {
        return new Identifier(EldritchEnd.MODID, "textures/entity/dendler.png");
    }

    @Override
    public Identifier getAnimationResource(DendlerEntity animatable) {
        return new Identifier(EldritchEnd.MODID, "animations/dendler.animation.json");
    }
}
