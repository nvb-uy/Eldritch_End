package elocindev.eldritch_end.item.spawneggs;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class HastursCrown extends SpawnEggItem {
    public HastursCrown(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }
    
    String[] TOOLTIP_NORMAL = {
        "",
        "\u00A7l\u2022 \u00A76Cosmic Deity \u00A78(Passive)",
        "\u00A77Hastur is immune to all direct damage sources.",
        "",
        "\u00A7l\u2022 \u00A76Cursed Presence \u00A78(Passive)",
        "\u00A77All nearby players will get cursed by \u00A76Hastur's Presence\u00A77 until Hastur goes away.",
        "",
        "\u00A7l\u2022 \u00A76Death's Grip \u00A78(Passive)",
        "\u00A77If a cursed player dies close to Hastur, he will kill all nearby cursed players.",
        "",
        "\u00A7l\u2022 \u00A76Encarnated Lightning \u00A78(Active, below 50% health)",
        "\u00A77Hastur will summon a lightning bolt near a random cursed player that deals \u00A79100 corruption damage\u00A77."
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }
}
