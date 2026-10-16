package elocindev.eldritch_end.client.entity.hastur;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.hastur.HasturEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class HasturRenderer extends AzEntityRenderer<HasturEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/hastur.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/hastur.png");

    public HasturRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<HasturEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(HasturAnimator::new)
                .build(), context);
    }
}
