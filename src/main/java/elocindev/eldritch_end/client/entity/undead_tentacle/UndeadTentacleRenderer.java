package elocindev.eldritch_end.client.entity.undead_tentacle;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.client.entity.tentacle.TentacleAnimator;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class UndeadTentacleRenderer extends AzEntityRenderer<TentacleEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/tentacle.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/hastur_tentacle.png");

    public UndeadTentacleRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<TentacleEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(TentacleAnimator::new)
                .build(), context);
    }
}
