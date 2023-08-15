package elocindev.eldritch_end;

import elocindev.eldritch_end.entity.client.primordial_boat.PrimordialBoatRenderer;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import elocindev.eldritch_end.config.ConfigLoader;
import elocindev.eldritch_end.entity.client.aberration.AberrationRenderer;
import elocindev.eldritch_end.entity.client.tentacle.TentacleRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class EldritchEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigLoader.initClient();

        EntityRendererRegistry.register(EntityRegistry.ABERRATION, AberrationRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.TENTACLE, TentacleRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.PRIMORDIAL_BOAT, PrimordialBoatRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PRIMORDIAL_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ABYSMAL_ROOTS, RenderLayer.getCutout());

    }
}
