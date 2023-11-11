package elocindev.eldritch_end.item.artifacts;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.item.artifacts.base.CorruptionArtifact;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Xalarath extends CorruptionArtifact {
    public Xalarath(Settings settings) {
        super(settings);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("item.eldritch_end.xalarath.type"), 1, 0x5c3885, 0x8e4ecf, 1.0F);
        
        tooltip.add(type);

        tooltip.add(emptyLine());

        tooltip.add(Text.of("- Obscure ability"));
    }

    private Text emptyLine() {
        return Text.empty();
    }
}
