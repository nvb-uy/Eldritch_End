package elocindev.eldritch_end.item.infusion_materials;

import java.util.List;

import elocindev.eldritch_end.api.infusion.InfusionAttributeHolder;
import elocindev.eldritch_end.api.RitualAPI.RitualStructure;
import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.item.dark_magic.SummonPartItem;
import elocindev.eldritch_end.registry.AttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

public class AberrationHeartItem extends SummonPartItem implements InfusableItemMaterial {

    public AberrationHeartItem(Settings settings, RitualStructure ritualStructure, Block mainSummonBlock, BlockState aftermathBlock, EntityType<?> summon) {
        super(settings, ritualStructure, mainSummonBlock, aftermathBlock, summon);
    }

    @Override
    public List<InfusionAttributeHolder> getInfusionAttributes() {
        return List.of(new InfusionAttributeHolder(AttributeRegistry.CORRUPTION, 10.0, InfusionAttributeHolder.Presets.CORRUPTION));
    }

    @Override
    public Item getInfusionTemplate() {
        // TODO: CHANGE THIS TO ACTUAL TEMPLATE INSTEAD OF ITSELF
        return this;
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
            "eldritch_end:aberration_limb",
            "eldritch_end:xal"
        );
    }
    
}
