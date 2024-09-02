package elocindev.eldritch_end.config.entries.entities.boss;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class FacelessConfig {
    @NecConfig
    public static FacelessConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("the_faceless.json5", "bosses");
    }

    public Attributes attributes = new Attributes();

    public class Attributes {
        public double MAX_HEALTH = 800;
        public double MOVEMENT_SPEED = 0.25;
        public double ATTACK_DAMAGE = 12;
        public double ATTACK_SPEED = 0.7;
    }
}
