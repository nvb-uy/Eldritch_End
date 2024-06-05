package elocindev.eldritch_end.client.render.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.corruption.corruption_effect.CEOverlay;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.joml.Math;

public class CorruptionOverlay implements HudRenderCallback {
    private static final Identifier CORRUPTION_OVERLAY = new Identifier(EldritchEnd.MODID, "overlay.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        CEOverlay cfg = CorruptionAPI.CONFIG.corruption_effects.madness_vision;

        int START_CORRUPTION = cfg.getStartingLevel();
        int END_CORRUPTION = cfg.getEndLevel();

        if (client != null && client.player != null) {
            double totalCorruptionLevel = CorruptionAPI.getTotalCorruptionLevel(client.player);

            if (totalCorruptionLevel < START_CORRUPTION ) return;
            
            double attributeIntensity = (totalCorruptionLevel - START_CORRUPTION) / (END_CORRUPTION - START_CORRUPTION);

            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);

            drawContext.setShaderColor(
                (float) Math.lerp(0.212, 0.475, attributeIntensity),
                (float) Math.lerp(0.11, 0.188, attributeIntensity),
                (float) Math.lerp(0.459, 0.722, attributeIntensity),
                0.3f * cfg.getStrength());

            drawContext.drawTexture(CORRUPTION_OVERLAY, 0, 0, -90, 0.0f, 0.0f, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight());
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            drawContext.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();
        }
    }
}