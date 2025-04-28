
package elocindev.eldritch_end.client.entity.eye_remastered;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class EyeModel<T extends EyeEntity> extends GeoModel<T>  {
    private static final Identifier DEFAULT_LOCATION = Identifier.of(EldritchEnd.MODID,"textures/entity/eyetexture.png");

    @Override
    public Identifier getModelResource(T reaver) {

        return Identifier.of(EldritchEnd.MODID,"geo/eye.geo.json");
    }

    @Override
    public Identifier getTextureResource(T animatable) {
        return DEFAULT_LOCATION;
    }


    @Override
    public Identifier getAnimationResource(T reaver) {
        return Identifier.of(EldritchEnd.MODID,"animations/eye.animation.json");
    }


}
