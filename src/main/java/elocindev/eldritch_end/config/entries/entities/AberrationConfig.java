package elocindev.eldritch_end.config.entries.entities;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class AberrationConfig {
    @NecConfig
    public static AberrationConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("aberration.json", "entities");
    }

    public double HEALTH_ATTRIBUTE = 20;
    public double MOVEMENT_SPEED_ATTRIBUTE = 0.3;
    public double ATTACK_DAMAGE_ATTRIBUTE = 4;
    public double ATTACK_SPEED_ATTRIBUTE = 1;
    public double CHASE_SPEED = 1.2;
    public double WANDER_SPEED = 1.0;
    public int initital_corruption_duration_ticks = 200;
}
