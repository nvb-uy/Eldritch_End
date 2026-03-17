package elocindev.eldritch_end.mixin.recipe;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.registry.DynamicRegistryManager;

@Mixin(SmithingTransformRecipe.class)
public class SmithingTransformRecipeMixin {
    @Inject(method = "finishRecipe", at = @At("RETURN"))
    private void eldritch_end$keepInfusions(Inventory inventory, DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = cir.getReturnValue();
        ItemStack base = inventory.getStack(1); // SmithingTransformRecipe.base is at index 1 in the inventory

        if (base.hasNbt() && base.getNbt().contains("eldritch_infusions")) {
            NbtCompound infusions = base.getSubNbt("eldritch_infusions").copy();
            result.getOrCreateNbt().put("eldritch_infusions", infusions);
            
            if (base.getNbt().contains("AttributeModifiers")) {
                result.getNbt().put("AttributeModifiers", base.getNbt().get("AttributeModifiers").copy());
            }
        }
    }
}
