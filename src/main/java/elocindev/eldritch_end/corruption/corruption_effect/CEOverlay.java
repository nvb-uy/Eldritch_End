package elocindev.eldritch_end.corruption.corruption_effect;

public class CEOverlay extends CorruptionEffectBase {

    private float strength;
    private int end_level;

    public CEOverlay(int starting_level, int end_level, float strength) {
        super(starting_level);
    
        this.end_level = end_level;
        this.strength = strength;
    }

    public int getEndLevel() {
        return end_level;
    }

    public float getStrength() {
        return strength;
    }
}
