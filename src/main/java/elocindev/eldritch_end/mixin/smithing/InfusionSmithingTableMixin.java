package elocindev.eldritch_end.mixin.smithing;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import elocindev.eldritch_end.api.infusion.InfusionTemplate;
import elocindev.eldritch_end.mixin.item.ItemAttributeAccessor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.slot.Slot;

@Mixin(SmithingScreenHandler.class)
public class InfusionSmithingTableMixin {
    @Shadow 
    private List<ItemStack> getInputStacks() {
        return null;
    }

    @Inject(method = "canInsertIntoSlot", at = @At("HEAD"), cancellable = true)
    private void eldritch_end$canInsertIntoSlot(ItemStack stack, Slot slot, CallbackInfoReturnable<Boolean> cir) {
        switch(slot.getIndex()) {
            case SmithingScreenHandler.EQUIPMENT_ID:
                if (!(getMaterial().getItem() instanceof InfusableItemMaterial) || !(getTemplate().getItem() instanceof InfusionTemplate template)) break;

                if (template.isEquipmentAllowed(stack)) {
                    cir.setReturnValue(true);
                }
                
                break;
            case SmithingScreenHandler.MATERIAL_ID:
                if (stack.getItem() instanceof InfusableItemMaterial) {
                    cir.setReturnValue(true);
                }

                break;
            default:
                return;
        }        
    }

    @ModifyArg(
        method = "getForgingSlotsManager",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;",
            ordinal = 1
        ),
        index = 3
    )
    private Predicate<?> eldritch_end$modifyBaseSlotCondition(Predicate<?> original) {
        return original.or(stack -> {
            if (!(getMaterial().getItem() instanceof InfusableItemMaterial) || !(getTemplate().getItem() instanceof InfusionTemplate template)) return false;

            if (template.isEquipmentAllowed((ItemStack) stack)) {
                return true;
            }

            return false;
        });
    }

    @ModifyArg(
        method = "getForgingSlotsManager",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;",
            ordinal = 2
        ),
        index = 3
    )
    private Predicate<?> eldritch_end$modifyAdditionSlotCondition(Predicate<?> original) {
        return original.or(stack -> {
            if (((ItemStack) stack).getItem() instanceof InfusableItemMaterial) {
                return true;
            }

            return false;
        });
    }
    
    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void eldritch_end$updateResult(CallbackInfo ci) {
        SmithingScreenHandler ths = (SmithingScreenHandler) (Object) this;
    
        ItemStack base = getTemplate();
        ItemStack candidate = getEquipment();
        ItemStack addition = getMaterial();
    
        if (addition.isEmpty() || base.isEmpty() || candidate.isEmpty()) return;
    
        if (addition.getItem() instanceof InfusableItemMaterial material) {
            if (material.getInfusionTemplate() != base.getItem()) return;
            if (candidate.getItem() instanceof ArmorItem && !material.applyToArmor()
            || (candidate.getItem() instanceof SwordItem || candidate.getItem() instanceof AxeItem || candidate.getItem() instanceof ToolItem) && !material.applyToWeapons()
            || (!material.isInfusable())) return;
    
            boolean isAlreadyInfused = candidate.copy().getOrCreateSubNbt("eldritch_infusions").getBoolean("isInfused");
    
            if (isAlreadyInfused && (!isUpgradeable(material, candidate.copy()))) return;
    
            ItemStack potentialResult = candidate.copy();
    
            NbtCompound nbt = potentialResult.getOrCreateSubNbt("eldritch_infusions");
    
            EquipmentSlot slot;
    
            if (potentialResult.getItem() instanceof ArmorItem armorItem) {
                slot = armorItem.getSlotType();
            } else if (potentialResult.getItem() instanceof SwordItem || potentialResult.getItem() instanceof AxeItem) {
                slot = EquipmentSlot.MAINHAND;
            } else {
                slot = EquipmentSlot.MAINHAND;
            }
    
            nbt.putBoolean("isInfused", true);
            nbt.putString("currentInfusion", Registries.ITEM.getId(addition.getItem()).getPath());
            nbt.putString("materialIdentifier", Registries.ITEM.getId(addition.getItem()).toString());
            
            Multimap<EntityAttribute, EntityAttributeModifier> originalModifiers = HashMultimap.create();
            originalModifiers.putAll(potentialResult.getAttributeModifiers(slot));
            
            Multimap<EntityAttribute, EntityAttributeModifier> mergedModifiers = HashMultimap.create(originalModifiers);
            
            for (var holder : material.getInfusionAttributes()) {
                EntityAttributeModifier newModifier = new EntityAttributeModifier(
                    material.getInfusionUUID(addition),
                    "Infusion modifier",
                    holder.amount,
                    holder.operation
                );
    
                if (holder.attribute.equals(EntityAttributes.GENERIC_ATTACK_DAMAGE) || holder.attribute.equals(EntityAttributes.GENERIC_ATTACK_SPEED)) {
                    Collection<EntityAttributeModifier> existingModifiers = mergedModifiers.get(holder.attribute);
                    EntityAttributeModifier toReplace = null;
    
                    for (EntityAttributeModifier existingModifier : existingModifiers) {
                        if (existingModifier.getId().equals(newModifier.getId())) {
                            toReplace = existingModifier;
                            break;
                        }
                    }
    
                    if (toReplace != null) {
                        mergedModifiers.remove(holder.attribute, toReplace);
                        mergedModifiers.put(holder.attribute, new EntityAttributeModifier(
                            toReplace.getId(),
                            toReplace.getName(),
                            toReplace.getValue() + newModifier.getValue(),
                            toReplace.getOperation()
                        ));
                    } else {
                        mergedModifiers.put(holder.attribute, newModifier);
                    }
                } else {
                    mergedModifiers.put(holder.attribute, newModifier);
                }
            }
    
            boolean useWeaponModifiers = !(potentialResult.getItem() instanceof ArmorItem);
    
            for (var entry : mergedModifiers.entries()) {
                EntityAttribute attribute = entry.getKey();
                EntityAttributeModifier modifier = entry.getValue();
    
                if (attribute.equals(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
                    modifier = new EntityAttributeModifier(
                        useWeaponModifiers ? ItemAttributeAccessor.getAttackDamageModifierId() : modifier.getId(),
                        modifier.getName(),
                        modifier.getValue(),
                        modifier.getOperation()
                    );
                } else if (attribute.equals(EntityAttributes.GENERIC_ATTACK_SPEED)) {
                    modifier = new EntityAttributeModifier(
                        useWeaponModifiers ? ItemAttributeAccessor.getAttackSpeedModifierId() : modifier.getId(),
                        modifier.getName(),
                        modifier.getValue(),
                        modifier.getOperation()
                    );
                }
    
                potentialResult.addAttributeModifier(attribute, modifier, slot);
            }
    
            ((ForgingScreenHandlerAccessor) ths).getOutput().setStack(3, potentialResult);
    
            ci.cancel();
        }
    }
    



    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    private void canTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
        if (isValidInfusion()) {
            cir.setReturnValue(true);
        }
    }

    private boolean isUpgradeable(InfusableItemMaterial material, ItemStack stack) {
        return material.canSwapInfusionTo().contains(stack.getOrCreateSubNbt("eldritch_infusions").getString("materialIdentifier"));
    }

    private boolean isValidInfusion() {
        return getMaterial().getItem() instanceof InfusableItemMaterial && getTemplate().getItem() instanceof InfusionTemplate && !getEquipment().isEmpty();
    }

    private ItemStack getTemplate() {
        return getInputStacks().get(0);
    }

    private ItemStack getEquipment() {
        return getInputStacks().get(1);
    }

    private ItemStack getMaterial() {
        return getInputStacks().get(2);
    }
}
