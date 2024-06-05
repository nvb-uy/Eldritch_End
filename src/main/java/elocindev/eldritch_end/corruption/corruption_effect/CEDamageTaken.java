package elocindev.eldritch_end.corruption.corruption_effect;

public class CEDamageTaken extends CorruptionEffectBase {

    private float damage_amount;

    public CEDamageTaken(int starting_level, float damage_percentage) {
        super(starting_level);
    
        this.damage_amount = damage_percentage;
    }

    public float getDamagePercentage() {
        return 1f + damage_amount;
    }
}
