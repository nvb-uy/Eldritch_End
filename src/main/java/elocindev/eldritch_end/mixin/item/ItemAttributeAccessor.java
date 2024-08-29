package elocindev.eldritch_end.mixin.item;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.item.Item;

@Mixin(Item.class)
public interface ItemAttributeAccessor {
    @Accessor("ATTACK_DAMAGE_MODIFIER_ID")
    static UUID getAttackDamageModifierId() {
        throw new AssertionError();
    }

    @Accessor("ATTACK_SPEED_MODIFIER_ID")
    static UUID getAttackSpeedModifierId() {
        throw new AssertionError();
    }
}
