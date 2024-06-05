package elocindev.eldritch_end.corruption.corruption_effect;

public class CELethalThreshold extends CorruptionEffectBase {
    private float max_health_per_second;

    public CELethalThreshold(int starting_level, float max_health_per_second) {
        super(starting_level);
    
        this.max_health_per_second = max_health_per_second;
    }

    public float getMaxHealthPerSecond() {
        return max_health_per_second;
    }
}
