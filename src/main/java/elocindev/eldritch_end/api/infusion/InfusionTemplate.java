package elocindev.eldritch_end.api.infusion;

import net.minecraft.item.ItemStack;

public interface InfusionTemplate {
    default boolean isEquipmentAllowed(ItemStack stack) {
        return true;
    }   
}