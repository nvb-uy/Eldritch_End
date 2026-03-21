package elocindev.eldritch_end.mixin.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class InfusionItemAttributeMixin {

    @Inject(method = "getAttributeModifiers", at = @At("RETURN"), cancellable = true)
    private void eldritch_end$applyInfusionAttributes(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir) {

        ItemStack self = (ItemStack) (Object) this;

        NbtCompound infusionNbt = self.getSubNbt("eldritch_infusions");
        if (infusionNbt == null || !infusionNbt.getBoolean("isInfused")) return;

        if (self.hasNbt() && self.getNbt().contains("AttributeModifiers")) return;

        String materialId = infusionNbt.getString("materialIdentifier");
        if (materialId == null || materialId.isEmpty()) return;

        Identifier id;
        try {
            id = new Identifier(materialId);
        } catch (Exception e) {
            return;
        }

        Item materialItem = Registries.ITEM.get(id);
        if (!(materialItem instanceof InfusableItemMaterial material)) return;

        EquipmentSlot itemSlot;
        if (self.getItem() instanceof ArmorItem armorItem) {
            itemSlot = armorItem.getSlotType();
        } else if (infusionNbt.contains("infusionSlot")) {
            itemSlot = EquipmentSlot.byName(infusionNbt.getString("infusionSlot"));
        } else {
            itemSlot = EquipmentSlot.MAINHAND;
        }

        if (slot != itemSlot) return;

        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(cir.getReturnValue());

        ItemStack dummyMaterialStack = new ItemStack(materialItem);

        for (var holder : material.getInfusionAttributes()) {
            EntityAttributeModifier modifier = new EntityAttributeModifier(
                    material.getInfusionUUID(dummyMaterialStack, slot),
                    "Infusion modifier",
                    holder.amount,
                    holder.operation
            );
            modifiers.put(holder.attribute, modifier);
        }

        cir.setReturnValue(modifiers);
    }
}
