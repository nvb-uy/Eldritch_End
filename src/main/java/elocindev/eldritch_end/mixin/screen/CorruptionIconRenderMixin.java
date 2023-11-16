package elocindev.eldritch_end.mixin.screen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.eldritch_end.api.CorruptionAPI;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Mixin(InventoryScreen.class)
public abstract class CorruptionIconRenderMixin extends AbstractInventoryScreen<PlayerScreenHandler> {
    public CorruptionIconRenderMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(method = "drawBackground", at = @At("TAIL"), cancellable = true)
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info) {       
        context.drawTexture(new Identifier("eldritch_end", "textures/icon/corruption.png"), x + 66, y + 69, 0, 0, 11, 11, 11, 11);
        
        if (mouseX >= x + 68 && mouseX <= x + 79 && mouseY >= y + 70 && mouseY <= y + 81) {
            context.drawTooltip(textRenderer, CorruptionAPI.getTooltip(), mouseX, mouseY);
        }
    }
}
