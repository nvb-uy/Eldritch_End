package elocindev.eldritch_end.client.entity.dendler;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.dendler.DendlerEntity;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class DendlerRenderer extends AzEntityRenderer<DendlerEntity> {
    private static final Identifier MODEL = new Identifier(EldritchEnd.MODID, "geo/dendler.geo.json");
    private static final Identifier TEXTURE = new Identifier(EldritchEnd.MODID, "textures/entity/dendler.png");

    public DendlerRenderer(EntityRendererFactory.Context context) {
        super(AzEntityRendererConfig.<DendlerEntity>builder(MODEL, TEXTURE)
                .setAnimatorProvider(DendlerAnimator::new)
                .build(), context);
    }
}
