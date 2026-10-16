package elocindev.eldritch_end.client.entity.tentacle;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class TentacleRenderer extends AzEntityRenderer<TentacleEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/tentacle.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/tentacle.png");

    public TentacleRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<TentacleEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(TentacleAnimator::new)
                .build(), context);
    }
}
