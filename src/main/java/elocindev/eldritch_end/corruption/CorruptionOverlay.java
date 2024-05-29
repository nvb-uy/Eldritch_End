package elocindev.eldritch_end.corruption;

import com.mojang.blaze3d.systems.RenderSystem;
import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.registry.AttributeRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.joml.Math;

public class CorruptionOverlay implements HudRenderCallback {
    private static final Identifier CORRUPTION_OVERLAY = new Identifier(EldritchEnd.MODID, "overlay.png");
    private static final float MAX_CORRUPTION = 100f;
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.player != null && client.player.getAttributeValue(AttributeRegistry.CORRUPTION) >= 40) {
            double attributeIntensity = client.player.getAttributeValue(AttributeRegistry.CORRUPTION) - 39;
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);

            drawContext.setShaderColor(
                    (float) Math.lerp(0.475, 0.212, attributeIntensity / MAX_CORRUPTION),
                    (float) Math.lerp(0.188, 0.11, attributeIntensity / MAX_CORRUPTION),
                    (float) Math.lerp(0.722, 0.459, attributeIntensity / MAX_CORRUPTION), 0.3f);

            drawContext.drawTexture(CORRUPTION_OVERLAY, 0, 0, -90, 0.0f, 0.0f, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight());
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            drawContext.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();
        }
    }
}