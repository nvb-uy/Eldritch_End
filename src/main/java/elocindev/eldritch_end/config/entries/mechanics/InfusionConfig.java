package elocindev.eldritch_end.config.entries.mechanics;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class InfusionConfig {
    @NecConfig
    public static InfusionConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("infusions.json5", "mechanics");
    }

    public class EtyrInfusion {
        public boolean can_apply_to_weapons = true;
        public boolean can_apply_to_armor = true;
    }

    public class CorruptionInfusion {
        public boolean can_apply_to_weapons = true;
        public boolean can_apply_to_armor = true;
    }

    public EtyrInfusion etyr_infusion = new EtyrInfusion();
    public CorruptionInfusion corruption_infusion = new CorruptionInfusion();
}
