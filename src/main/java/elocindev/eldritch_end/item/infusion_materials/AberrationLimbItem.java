package elocindev.eldritch_end.item.infusion_materials;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.api.infusion.InfusionAttributeHolder;
import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class AberrationLimbItem extends Item implements InfusableItemMaterial {

    public AberrationLimbItem(Settings settings) {
        super(settings);
    }

    @Override
    public List<InfusionAttributeHolder> getInfusionAttributes() {
        return List.of(new InfusionAttributeHolder(AttributeRegistry.CORRUPTION, 10, InfusionAttributeHolder.Presets.CORRUPTION));
    }

    @Override
    public Item getInfusionTemplate() {
        return ItemRegistry.CORRUPTION_UPGRADE_PATTERN;
    }

    @Override
    public boolean applyToArmor() {
        return Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor;
    }

    @Override
    public boolean applyToWeapons() {
        return Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons;
    }

    @Override
    public List<String> canSwapInfusionTo() {
        return List.of(
            "eldritch_end:aberration_heart",
            "eldritch_end:xal"
        );
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        
        var appliesto = Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor && Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons ? 
            "infusion.eldritch_end.applies_to_all" : 
            Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor ? 
                "infusion.eldritch_end.applies_to_armor" : 
                "infusion.eldritch_end.applies_to_weapons";

        tooltip.add(Text.translatable("infusion.eldritch_end.infusable").append(Text.translatable(appliesto)).append(" (Tier I)").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
    }
}
