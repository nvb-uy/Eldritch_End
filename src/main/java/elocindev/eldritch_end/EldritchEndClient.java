package elocindev.eldritch_end;

import elocindev.eldritch_end.entity.client.undead_tentacle.UndeadTentacleRenderer;
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
        EntityRendererRegistry.register(EntityRegistry.UNDEAD_TENTACLE, UndeadTentacleRenderer::new);
        
        // EntityRendererRegistry.register(EntityRegistry.HASTUR, HasturRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PRIMORDIAL_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ETYR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DECADENT_ETYR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PERTURBED_ETYR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CORRUPTED_ETYR_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ABYSMAL_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ETYR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DECADENT_ETYR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PERTURBED_ETYR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CORRUPTED_ETYR_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.HASTURIAN_GRASS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ETYR_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DECADENT_ETYR_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PERTURBED_ETYR_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CORRUPTED_ETYR_BARS, RenderLayer.getCutout());

    }
}
