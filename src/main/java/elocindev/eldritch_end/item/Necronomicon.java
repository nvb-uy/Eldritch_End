package elocindev.eldritch_end.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class Necronomicon extends Item {

    public Necronomicon(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.EPIC));
    }
    
    String[] TOOLTIP_NORMAL = {
        "\u00A77A forbidden tome unveiling eldritch secrets and cosmic horrors.",
        "\u00A77Delve into its pages to grasp unimaginable power,",
        "\u00A77but risk losing sanity to the abyss of the forbidden knowledge.",
        " ",
        "&8[SHIFT]",
        "",
        "\u00A74Not implemented in survival yet!"
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }

}
