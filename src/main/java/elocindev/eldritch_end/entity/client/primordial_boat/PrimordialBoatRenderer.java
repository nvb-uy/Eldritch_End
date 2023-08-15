package elocindev.eldritch_end.entity.client.primordial_boat;

import com.mojang.datafixers.util.Pair;
import elocindev.eldritch_end.EldritchEnd;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;

public class PrimordialBoatRenderer extends BoatEntityRenderer {
    Identifier BOAT_TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/primordial_boat.png");

    public PrimordialBoatRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, false);
    }

    public Identifier getTexture(BoatEntity boatEntity) {
        return BOAT_TEXTURE;
    }
}
