package elocindev.eldritch_end.item.armor;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import elocindev.eldritch_end.registry.AttributeRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EtyriteArmorPiece extends ArmorItem {
    public EtyriteArmorPiece(ArmorMaterial material, ArmorItem.Type slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    }    

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(super.getAttributeModifiers(slot));
        
        if (slot == this.getSlotType())
            modifiers.put(
                AttributeRegistry.CORRUPTION_RESISTANCE,
                new EntityAttributeModifier(
                    UUID.randomUUID(), 
                    "Corruption resistance modifier", 
                    10.0, 
                    EntityAttributeModifier.Operation.ADDITION
                )
            );
        
        return modifiers;
    }
}
