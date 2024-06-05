package elocindev.eldritch_end.corruption.corruption_effect;

public class CEEyeSpawn extends CorruptionEffectBase {
    private int effect_rate_seconds;
    private float spawn_chance;
    private int eye_amount;
    private int combat_duration_ticks;

    public CEEyeSpawn(int starting_level, float spawn_chance, int eye_amount, int combat_duration_ticks, int effect_rate_seconds) {
        super(starting_level);
    
        this.spawn_chance = spawn_chance;
        this.combat_duration_ticks = combat_duration_ticks;
        this.effect_rate_seconds = effect_rate_seconds;
        this.eye_amount = eye_amount;
    }

    public float getSpawnChance() {
        return spawn_chance;
    }

    public int getCombatDurationTicks() {
        return combat_duration_ticks;
    }

    public int getEffectRateSeconds() {
        return effect_rate_seconds;
    }

    public int getEffectRateTicks() {
        return effect_rate_seconds * 20;
    }

    public int getEyeAmount() {
        return eye_amount;
    }
}
