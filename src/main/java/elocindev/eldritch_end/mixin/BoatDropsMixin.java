package elocindev.eldritch_end.mixin;

import elocindev.eldritch_end.registry.EntityRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatDropsMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void eldritch_end_asItem(CallbackInfoReturnable<Item> ci) {
        if (((BoatEntity)(Object)this).getBoatType() == EntityRegistry.PRIMORDIAL) {
            ci.setReturnValue(ItemRegistry.PRIMORDIAL_BOAT);
        }
    }
}