package elocindev.eldritch_end.api.infusion;

import java.util.List;

import net.minecraft.item.Item;

public interface InfusableItemMaterial {
    List<InfusionAttributeHolder> getInfusionAttributes();
    
    /**
     * List of item IDs that this material can swap its infusion to, please use the same UUID as these materials so the attributes can be swapped properly, if not they will stack.
     * @see InfusionAttributeHolder.Presets
     */
    List<String> canSwapInfusionTo();
    Item getInfusionTemplate();

    boolean applyToArmor();
    boolean applyToWeapons();

    default boolean isInfusable() {
        return applyToArmor() || applyToWeapons();
    }
}
