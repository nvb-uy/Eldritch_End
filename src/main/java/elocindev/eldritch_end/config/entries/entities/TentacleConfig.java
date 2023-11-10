package elocindev.eldritch_end.config.entries.entities;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class TentacleConfig {
    @NecConfig
    public static TentacleConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("tentacle.json", "entities");
    }

    public double HEALTH_ATTRIBUTE = 20;
    public double ATTACK_DAMAGE_ATTRIBUTE = 6;
    public double ATTACK_SPEED_ATTRIBUTE = 1;
}
