package elocindev.eldritch_end.config.entries;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class CorruptionConfig {
    @NecConfig
    public static CorruptionConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("corruption.json", "mechanics");
    }

    public boolean show_icon_only_when_corrupted = false;
    public int temporary_corruption_amount_per_stack = 10;
    public int maximum_corruption = 200;
    public int maximum_etyr_resistance = 100;
    public int e_damageTaken_startingLevel = 10;
    public int e_tentacles_startingLevel = 25;
    public int e_vision_startingLevel = 40;
    public int e_damageDealt_startingLevel = 50;
    public int e_eyes_startingLevel = 70;
    public int e_lethal_startingLevel = 100;
}