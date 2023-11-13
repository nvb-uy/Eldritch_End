package elocindev.eldritch_end.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.worldgen.util.TextUtils;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class Necronomicon extends Item {

    public Necronomicon(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.EPIC));
    }
    
    String[] TOOLTIP_NORMAL = {
        " ",
        "A forbidden tome unveiling eldritch secrets and cosmic horrors.",
        "Delve into its pages to grasp unimaginable power,",
        "but risk losing sanity to the abyss of the forbidden knowledge.",
        " "
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("item.eldritch_end.necronomicon.type"), 1, 0x5c3885, 0x446e5c, 1.0F);
        MutableText ability_icon = Text.empty().append("\uB996 ");
        MutableText ability = Text.empty().append("Forbidden Knowledge").setStyle(TextUtils.Styles.CORRUPTION_ABILITY);

        tooltip.add(emptyLine());

        tooltip.add(type.fillStyle(type.getStyle().withUnderline(true)));

        tooltip.add(emptyLine());

        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.literal(line).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        }

        tooltip.add(ability_icon.append(ability));
    }

    private Text emptyLine() {
        return Text.empty();
    }
}
