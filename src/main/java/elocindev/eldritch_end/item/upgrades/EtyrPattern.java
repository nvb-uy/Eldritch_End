package elocindev.eldritch_end.item.upgrades;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EtyrPattern extends Item {
    
    public EtyrPattern(Settings settings) {
        super(settings);
    }
    
    String[] TOOLTIP_NORMAL = {
        "\u00A72Etyr Pattern \u00A78(Upgrade)"
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }

}
