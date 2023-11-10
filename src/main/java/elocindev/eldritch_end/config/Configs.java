package elocindev.eldritch_end.config;

import elocindev.eldritch_end.config.entries.ClientConfig;
import elocindev.eldritch_end.config.entries.HasturianWastesConfig;
import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;
import elocindev.eldritch_end.config.entries.entities.AberrationConfig;
import elocindev.eldritch_end.config.entries.entities.HasturConfig;
import elocindev.eldritch_end.config.entries.entities.TentacleConfig;

public class Configs {
    public class Client {
        public static ClientConfig CLIENT_CONFIG = ClientConfig.INSTANCE;
    }

    public class Biome {
        public static PrimordialAbyssConfig PRIMORDIAL_ABYSS = PrimordialAbyssConfig.INSTANCE;
        public static HasturianWastesConfig HASTURIAN_WASTES = HasturianWastesConfig.INSTANCE;
    }

    public class Entity {
        public static AberrationConfig ABERRATION = AberrationConfig.INSTANCE;
        public static TentacleConfig TENTACLE = TentacleConfig.INSTANCE;
        public static HasturConfig HASTUR = HasturConfig.INSTANCE;
    }
}
