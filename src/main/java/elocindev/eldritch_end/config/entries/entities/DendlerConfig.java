package elocindev.eldritch_end.config.entries.entities;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class DendlerConfig {
    @NecConfig
    public static DendlerConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("dendler.json", "entities");
    }

    public double HEALTH_ATTRIBUTE = 40;
    public double MOVEMENT_SPEED_ATTRIBUTE = 0.3;
    public double WANDER_SPEED = 0.6;
}
