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

public class TentacleEgg extends SpawnEggItem {
    public TentacleEgg(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }
    
    String[] TOOLTIP_NORMAL = {
        "",
        "\u00A7l\u2022 \u00A79Swirl \u00A78(Basic Attack)",
        "\u00A77Damages all creatures in a 2 block radius for \u00A79"+ (int)Configs.Entity.TENTACLE.ATTACK_DAMAGE_ATTRIBUTE + "\u00A77 damage.",
        "",
        "\u00A7l\u2022 \u00A79Azathoth's Gift \u00A78(Passive)",
        "\u00A77Grants immunity to \u00A79corruption\u00A77 damage."
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }
}
