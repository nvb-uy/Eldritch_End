package elocindev.eldritch_end.item.infusion_templates;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.api.infusion.InfusionTemplate;
import elocindev.eldritch_end.config.Configs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CorruptionTemplate extends SmithingTemplateItem implements InfusionTemplate {
    public CorruptionTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
    }

    @Override
    public boolean isEquipmentAllowed(ItemStack stack) {
        if (stack.getItem() instanceof ArmorItem && Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor
        || (stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem) && Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons) {
            return true;
        }

        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.literal(" ").append(Text.translatable("item.eldritch_end.aberration_heart")).setStyle(Style.EMPTY.withColor(Formatting.BLUE)).append(Text.literal(" (Tier II)").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY))));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.eldritch_end.xal")).setStyle(Style.EMPTY.withColor(Formatting.BLUE)).append(Text.literal(" (Tier III)").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY))));
    }
}