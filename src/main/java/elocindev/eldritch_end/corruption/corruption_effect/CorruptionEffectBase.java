package elocindev.eldritch_end.corruption.corruption_effect;

// Used to handle the configs
public class CorruptionEffectBase {
    public int starting_corruption_level;

    public CorruptionEffectBase(int starting_level) {
        this.starting_corruption_level = starting_level;
    }

    public int getStartingLevel() {
        return starting_corruption_level;
    }

    public void setStartingLevel(int starting_level) {
        this.starting_corruption_level = starting_level;
    }
}
