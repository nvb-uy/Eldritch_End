package elocindev.eldritch_end.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Corruption extends StatusEffect {
    public static final DamageSource DAMAGE = new DamageSource("corruption").setBypassesArmor().setUnblockable().setUsesMagic();

    public Corruption() {
        super(StatusEffectCategory.NEUTRAL,
        0x330066); 
    }
    
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
