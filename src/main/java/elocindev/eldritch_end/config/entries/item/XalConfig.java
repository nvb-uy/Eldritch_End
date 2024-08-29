package elocindev.eldritch_end.config.entries.item;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class XalConfig {
    @NecConfig
    public static XalConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("xal.json", "items");
    }

    public float DAMAGE_PER_STRIKE = 13;
    public int COOLDOWN_TICKS = 800;
}
