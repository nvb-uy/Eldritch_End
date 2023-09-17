package elocindev.eldritch_end.item.armor;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EtyriteArmorPiece extends ArmorItem {

    public EtyriteArmorPiece(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    String[] TOOLTIP_NORMAL = {
        "\u00A72+10% Corruption Resistance"
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }    
}
