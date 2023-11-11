package elocindev.eldritch_end.item.artifacts;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.necronomicon.api.text.AnimatedText;
import elocindev.necronomicon.api.text.TextAPI;
import elocindev.necronomicon.item.FancyItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Xalarath extends FancyItem {
    public Xalarath(Settings settings, AnimatedText animatedTextType) {
        super(settings, animatedTextType);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("item.eldritch_end.xalarath.type"), 1, 0x5c3885, 0xb8731a, 1.0F);
        
        tooltip.add(type);
    }
}
