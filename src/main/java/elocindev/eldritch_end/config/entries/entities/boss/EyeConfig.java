package elocindev.eldritch_end.config.entries.entities.boss;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class EyeConfig {
    @NecConfig
    public static EyeConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("the_eye.json5", "bosses");
    }

    public EyeConfig.Attributes attributes = new EyeConfig.Attributes();
    public EyeConfig.Cooldowns cooldowns = new EyeConfig.Cooldowns();
    public EyeConfig.Misc misc = new EyeConfig.Misc();

    public class Attributes {
        public double MAX_HEALTH = 800;
        public double ATTACK_DAMAGE = 12;

    }

    public class Cooldowns {

        public int COOLDOWN_MISSILE = 120;
        public int COOLDOWN_FLAMEBLAST = 200;
        public int COOLDOWN_CRYSTALS = 400;
        public int COOLDOWN_TELEPORT = 200;
        public int RANDOM_COOLDOWN_REDUCTION_TELEPORT = 80;
    }

    public class Misc {
        public boolean REPLACE_ADVENTUREZ_EYE = true;

    }
}