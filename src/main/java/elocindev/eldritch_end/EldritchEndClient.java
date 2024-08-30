package elocindev.eldritch_end;

import elocindev.eldritch_end.client.entity.aberration.AberrationRenderer;
import elocindev.eldritch_end.client.entity.dendler.DendlerRenderer;
import elocindev.eldritch_end.client.entity.faceless.FacelessRenderer;
import elocindev.eldritch_end.client.entity.ominous_eye.OminousEyeRenderer;
import elocindev.eldritch_end.client.entity.tentacle.TentacleRenderer;
import elocindev.eldritch_end.client.entity.undead_tentacle.UndeadTentacleRenderer;
import elocindev.eldritch_end.client.render.screen.CorruptionOverlay;
import elocindev.eldritch_end.compat.LegendaryTooltipsIntegration;
import elocindev.eldritch_end.config.ConfigLoader;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import elocindev.eldritch_end.registry.PacketRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class EldritchEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigLoader.initClient();

        HudRenderCallback.EVENT.register(new CorruptionOverlay());
        PacketRegistry.registerS2CPackets();

        EntityRendererRegistry.register(EntityRegistry.OMINOUS_EYE, OminousEyeRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ABERRATION, AberrationRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.TENTACLE, TentacleRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.UNDEAD_TENTACLE, UndeadTentacleRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.DENDLER, DendlerRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.THE_FACELESS, FacelessRenderer::new);

        // EntityRendererRegistry.register(EntityRegistry.HASTUR, HasturRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PRIMORDIAL_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ETYR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DECADENT_ETYR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PERTURBED_ETYR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CORRUPTED_ETYR_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ABYSMAL_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ELDRITCH_PEDESTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ETYR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DECADENT_ETYR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PERTURBED_ETYR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CORRUPTED_ETYR_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.HASTURIAN_GRASS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ETYR_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DECADENT_ETYR_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PERTURBED_ETYR_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CORRUPTED_ETYR_BARS, RenderLayer.getCutout());

        if (FabricLoader.getInstance().isModLoaded("legendarytooltips")) {
            LegendaryTooltipsIntegration.init();
        }

        ModelLoadingPlugin.register(context -> context.addModels(
            new Identifier(EldritchEnd.MODID, "entity/ominous_eye")
        ));
    }
}
