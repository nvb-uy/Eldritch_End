package elocindev.eldritch_end.item.spawneggs;

import elocindev.eldritch_end.config.Configs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EyeEgg extends SpawnEggItem {
    public EyeEgg(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }
    
    String[] TOOLTIP_NORMAL = {
        "",
        "\u00A7l\u2022 \u00A79Arcane Missile \u00A78(6s Cooldown)",
        "\u00A77Shoots three arcane missiles at every enemy in its field of vision.",
        "",
        "\u00A7l\u2022 \u00A79Flame Blast \u00A78(8s Cooldown)",
        "\u00A77The Eye fires three linear blasts at each enemy in its field of vision sequentially after a delay.",
        "",
        "\u00A7l\u2022 \u00A79Resonating Crystals \u00A78(20s Cooldown)",
        "\u00A77Sends crystals down onto up to seven targets, dealing damage in an area upon impact. These crystals can be damaged to deal",
        "\u00A77the damage dealt to them directly to The Eye. Destroying a crystal deals heavy damage to The Eye.",
        "\u00A77If not dealt with, the crystals explode, dealing the same damage on impact in an area."
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }
}
