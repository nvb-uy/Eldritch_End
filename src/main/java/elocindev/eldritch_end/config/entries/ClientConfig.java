package elocindev.eldritch_end.config.entries;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class ClientConfig {
    @NecConfig
    public static ClientConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getFile("eldritch_end-client.json");
    }

    public boolean enable_fog = true;
}
