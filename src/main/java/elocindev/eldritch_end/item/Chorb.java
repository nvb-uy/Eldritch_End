package elocindev.eldritch_end.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Chorb extends SpawnEggItem {
    
    public Chorb(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }
    
    String[] TOOLTIP_NORMAL = {
        "",
        "\u00A7l\u2022 \u00A76Chorbin' Time \u00A78(Basic Attack)",
        "\u00A77Chorb chorbs at the target, dealing \u00A7d93041 emotional damage\u00A77.",
        "",
        "\u00A7l\u2022 \u00A76Bladee's Blessing \u00A78(Active, 30s Cooldown)",
        "\u00A77Chorb starts listening to \u00A76BLADEE - WASTER\u00A77, increasing her damage 25% for the song's duration.",
        "",
        "\u00A7l\u2022 \u00A76Shrimp in the shower \u00A78(Ultimate, 120s Cooldown)",
        "\u00A77Chorb spawns a shower and starts eating shrimp for 10s,",
        "\u00A77healing \u00A7610%\u00A77 max health per second."
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }

}
