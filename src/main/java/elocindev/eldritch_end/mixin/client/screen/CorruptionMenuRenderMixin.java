package elocindev.eldritch_end.mixin.client.screen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Mixin(InventoryScreen.class)
public abstract class CorruptionMenuRenderMixin extends AbstractInventoryScreen<PlayerScreenHandler> {
    public CorruptionMenuRenderMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    public ItemStack dummy = new ItemStack(ItemRegistry.CORRUPTION_MENU);

    @Inject(method = "drawBackground", at = @At("TAIL"), cancellable = true)
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info) {  
        String texturePath = "textures/icon/corruption_menu/";
        long currentTime = System.currentTimeMillis();
        long animationCycle = 200;
        long delay = 3000;
        long timeInCycle = currentTime % (animationCycle + delay);

        int animIndex;

        if (timeInCycle < animationCycle / 2)
            animIndex = (int) (timeInCycle / (animationCycle / 8)) + 1;
        else if (timeInCycle < animationCycle)
            animIndex = 4 - (int) ((timeInCycle - animationCycle / 2) / (animationCycle / 8));
        else
            animIndex = 1;

        if (client.player == null || 
        CorruptionAPI.CONFIG.show_icon_only_when_corrupted && CorruptionAPI.getCorruptionLevel(client.player) == 0 ) 
            return;
        
        texturePath = texturePath+animIndex+".png";

        context.drawTexture(new Identifier(EldritchEnd.MODID, texturePath), x + 66, y + 69, 0, 0, 11, 11, 11, 11);
        
        if (mouseX >= x + 66 && mouseX <= x + 77 && mouseY >= y + 69 && mouseY <= y + 80) {
            context.drawItemTooltip(textRenderer, dummy, mouseX, mouseY);
        }
    }
}
