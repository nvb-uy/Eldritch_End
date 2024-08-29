package elocindev.eldritch_end.item.infusion_materials;

import java.util.List;

import elocindev.eldritch_end.api.infusion.InfusionAttributeHolder;
import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.minecraft.item.Item;

public class EtyrIngot extends Item implements InfusableItemMaterial {
    public EtyrIngot(Settings settings) {
        super(settings);
    }

    @Override
    public List<InfusionAttributeHolder> getInfusionAttributes() {
        return List.of(new InfusionAttributeHolder(AttributeRegistry.CORRUPTION_RESISTANCE, 10.0, InfusionAttributeHolder.Presets.ETYR));
    }

    @Override
    public Item getInfusionTemplate() {
        return ItemRegistry.ETYR_UPGRADE_TEMPLATE;
    }

    @Override
    public boolean applyToArmor() {
        return Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_armor;
    }

    @Override
    public boolean applyToWeapons() {
        return Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_weapons;
    }

    @Override
    public List<String> canSwapInfusionTo() {
        return List.of();
    }
    
}
