package elocindev.eldritch_end.mixin.armor;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

@Mixin(Item.class)
public class EtyrTooltip {
    @Inject(method = "appendTooltip", at = @At("TAIL"), cancellable = true)
    public void eldritch_end$add_etyr_tooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        
    }
}
