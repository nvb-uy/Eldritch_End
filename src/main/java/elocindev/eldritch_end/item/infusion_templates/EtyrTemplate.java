package elocindev.eldritch_end.item.infusion_templates;

import java.util.List;

import elocindev.eldritch_end.api.infusion.InfusionTemplate;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.item.utils.TagUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EtyrTemplate extends SmithingTemplateItem implements InfusionTemplate {
    public EtyrTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
    }

    @Override
    public boolean isEquipmentAllowed(ItemStack stack) {
        if (
            TagUtils.isArmor(stack) && Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_armor
                ||
            TagUtils.isWeapon(stack) && Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_weapons) {
            return true;
        }

        return false;
    }
}