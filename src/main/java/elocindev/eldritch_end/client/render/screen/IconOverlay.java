package elocindev.eldritch_end.client.render.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.corruption.corruption_effect.CEOverlay;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.joml.Math;

public class IconOverlay implements HudRenderCallback {
    private static final Identifier CRYSTAL_ICON = new Identifier(EldritchEnd.MODID, "textures/icon/icon_crystal.png");
    private static final Identifier CRYSTAL_BAR = new Identifier(EldritchEnd.MODID, "textures/icon/crystal_bar.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        int crystals = 0;
        int crystalTime = 0;
        for (Entity entity : MinecraftClient.getInstance().world.getEntities()) {
            if(entity instanceof EyeEntity eye){
                crystals = eye.getDataTracker().get(EyeEntity.NUMBER_OF_CRYSTALS);
                crystalTime = eye.getDataTracker().get(EyeEntity.CRYSTAL_TIME);
            }
        }
        for(int ii = 0; ii < crystals; ii++) {
            drawContext.drawTexture(CRYSTAL_ICON, (int) (drawContext.getScaledWindowWidth() / 2F - (crystals*20)/2 + ii * 20),20,0,0,16,16,16,16);
            drawContext.drawTexture(CRYSTAL_BAR, (int) (drawContext.getScaledWindowWidth() / 2F - (crystals*20)/2 + ii * 20), 38,0, 0,16-(int)((crystalTime * 16f) / (float)Configs.Entity.EYE.cooldowns.CRYSTALS_TIME),3,32,32);

        }

    }
}