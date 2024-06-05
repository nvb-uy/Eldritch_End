package elocindev.eldritch_end.config.entries;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.eldritch_end.corruption.corruption_effect.CEDamageTaken;
import elocindev.eldritch_end.corruption.corruption_effect.CEEyeSpawn;
import elocindev.eldritch_end.corruption.corruption_effect.CELethalThreshold;
import elocindev.eldritch_end.corruption.corruption_effect.CENonCorruptionReduction;
import elocindev.eldritch_end.corruption.corruption_effect.CEOverlay;
import elocindev.eldritch_end.corruption.corruption_effect.CETentacleSpawn;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class CorruptionConfig {
    @NecConfig
    public static CorruptionConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("corruption.json5", "mechanics");
    }

    public boolean show_icon_only_when_corrupted = false;
    public int temporary_corruption_amount_per_stack = 10;
    
    public class CorruptionEffects {
        public CEDamageTaken received_damage_increment = new CEDamageTaken(10, 0.10f);
        public CETentacleSpawn tentacle_spawn = new CETentacleSpawn(25, 0.25f, 0.9f, 100, 30 /* Seconds */);
        public CEOverlay madness_vision = new CEOverlay(40, 100, 1.0f);
        public CENonCorruptionReduction non_corruption_damage_reduction = new CENonCorruptionReduction(50, 0.3f);
        public CEEyeSpawn ominous_eye_spawn = new CEEyeSpawn(70, 0.25f, 2, 100, 40);
        public CELethalThreshold madness_consumed = new CELethalThreshold(100, 0.1f);
    }

    @Comment("Effects caused by corruption")
    @Comment("You can set the starting corruption level for each effect, setting it to -1 will disable the effect.")
    public CorruptionEffects corruption_effects = new CorruptionEffects();
}