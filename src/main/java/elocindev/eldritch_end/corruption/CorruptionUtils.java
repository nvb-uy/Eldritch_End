package elocindev.eldritch_end.corruption;


import elocindev.eldritch_end.item.armor.EtyriteArmorPiece;
import elocindev.eldritch_end.registry.EffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class CorruptionUtils {
    public static float getDamageAmount(LivingEntity entity, float damage, boolean increasesWithCorruption) {
        float resistance = 0.0f;

        if (increasesWithCorruption) damage *= (entity.getStatusEffect(EffectRegistry.CORRUPTION).getAmplifier() + 1);

        for (ItemStack item : entity.getArmorItems()) {
            if (item.getItem() instanceof EtyriteArmorPiece) {
                resistance += 0.1f;
            }
        }
        
        return resistance > 0 ? damage - (damage * resistance) : damage;
    }
}