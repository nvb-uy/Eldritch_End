package elocindev.eldritch_end;

import elocindev.eldritch_end.registry.EntityRegistry;
import elocindev.eldritch_end.entity.client.aberration.AberrationRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EldritchEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.ABERRATION, AberrationRenderer::new);
    }
}
