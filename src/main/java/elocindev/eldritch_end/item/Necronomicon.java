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
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("\u00A77A forbidden tome unveiling eldritch secrets and cosmic horrors."));
        tooltip.add(Text.of("\\u00A77Delve into its pages to grasp unimaginable power,"));
        tooltip.add(Text.of("\\u00A77but risk losing sanity to the abyss of the forbidden knowledge."));
        tooltip.add(Text.of("\u00A74Not implemented in survival yet!"));
    }

}
