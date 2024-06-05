package elocindev.eldritch_end.mixin.entity.attribute;

import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.effects.Corruption;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class CorruptionDamageMixin {
    @Shadow
    public abstract double getAttributeValue(EntityAttribute attribute);

    @Inject(method = "modifyAppliedDamage", at = @At(value = "TAIL"), cancellable = true)
    protected void eldritch_end$damageReceived(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        var cfg = CorruptionAPI.CONFIG.corruption_effects.received_damage_increment;

        if (cfg.getStartingLevel() == -1) return;

        LivingEntity entity = (LivingEntity) (Object) this;

        if (CorruptionAPI.getTotalCorruptionLevel(entity) < cfg.getStartingLevel() || (source.isOf(Corruption.DAMAGE))) return;

        cir.setReturnValue(cir.getReturnValue() * 
            cfg.getDamagePercentage());
    }

    @Inject(method = "modifyAppliedDamage", at = @At(value = "TAIL"), cancellable = true)
    protected void eldritch_end$inflictedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        var cfg = CorruptionAPI.CONFIG.corruption_effects.non_corruption_damage_reduction;

        if (cfg.getStartingLevel() == -1) return;

        if (!(source.getAttacker() instanceof PlayerEntity player) ||
                (CorruptionAPI.getTotalCorruptionLevel(player) < cfg.getStartingLevel()) ||
                source.isOf(Corruption.DAMAGE)) return;

        cir.setReturnValue(cir.getReturnValue() * cfg.getDamagePercentage());
    }
}
