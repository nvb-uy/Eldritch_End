
package elocindev.eldritch_end.client.entity.crystal;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.crystal_entity.CrystalEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class CrystalModel<T extends CrystalEntity> extends GeoModel<T>  {
    private static final Identifier DEFAULT_LOCATION = Identifier.of(EldritchEnd.MODID,"textures/entity/crystaltexture.png");

    @Override
    public Identifier getModelResource(T reaver) {

        return Identifier.of(EldritchEnd.MODID,"geo/crystal.geo.json");
    }

    @Override
    public Identifier getTextureResource(T animatable) {
        return DEFAULT_LOCATION;
    }


    @Override
    public Identifier getAnimationResource(T reaver) {
        return Identifier.of(EldritchEnd.MODID,"animations/crystal.animation.json");
    }


}
