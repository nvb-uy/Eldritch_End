package elocindev.eldritch_end.client.render.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.api.CorruptionAPI;
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

        int START_CORRUPTION = 40;
        int END_CORRUPTION = 100;

        if (client != null && client.player != null) {
            double totalCorruptionLevel = CorruptionAPI.getTotalCorruptionLevel(client.player);

            if (totalCorruptionLevel < START_CORRUPTION ) return;
            
            double attributeIntensity = (totalCorruptionLevel - 40) / (END_CORRUPTION - 40);

            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);

            drawContext.setShaderColor(
                    (float) Math.lerp(0.475, 0.212, attributeIntensity / END_CORRUPTION),
                    (float) Math.lerp(0.188, 0.11, attributeIntensity / END_CORRUPTION),
                    (float) Math.lerp(0.722, 0.459, attributeIntensity / END_CORRUPTION), 0.3f);

            drawContext.drawTexture(CORRUPTION_OVERLAY, 0, 0, -90, 0.0f, 0.0f, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight());
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            drawContext.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();
        }
    }
}