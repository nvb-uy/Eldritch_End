package elocindev.eldritch_end.corruption.corruption_effect;

public class CENonCorruptionReduction extends CorruptionEffectBase {

    private float damage_reduction;

    public CENonCorruptionReduction(int starting_level, float damage_reduction) {
        super(starting_level);
    
        this.damage_reduction = damage_reduction;
    }

    public float getDamagePercentage() {
        return 1f - damage_reduction;
    }
}
