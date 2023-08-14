package elocindev.eldritch_end.item.spawneggs;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.config.Configs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class AberrationEgg extends SpawnEggItem {
    public AberrationEgg(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }
    
    String[] TOOLTIP_NORMAL = {
        "",
        "\u00A79Bite \u00A78(Basic Attack)",
        "\u00A77Bites the target and deals \u00A7c"+ (int)Configs.ENTITY_ABERRATION.ATTACK_DAMAGE_ATTRIBUTE + "\u00A77 + \u00A7925% \u00A77per \u00A7ncorruption stack\u00A77.",
        "\u00A77Applies \u00A79\u00A7nCorruption\u00A7r\u00A77 for "+Configs.ENTITY_ABERRATION.initital_corruption_duration_ticks/20+"s. Stacks don't reset its duration." ,
        "",
        "\u00A79Shadow's Grasp \u00A78(Passive, 1s Cooldown)",
        "\u00A77Heals 10% of max health if walking on Abysmal Fronds."
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }
}
