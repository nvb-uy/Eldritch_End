package elocindev.eldritch_end.config;

import elocindev.eldritch_end.config.entries.ClientConfig;
import elocindev.eldritch_end.config.entries.HasturianWastesConfig;
import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;
import elocindev.eldritch_end.config.entries.entities.AberrationConfig;
import elocindev.eldritch_end.config.entries.entities.HasturConfig;
import elocindev.eldritch_end.config.entries.entities.TentacleConfig;

public class Configs {
    public static ClientConfig CLIENT_CONFIG = ConfigBuilder.loadClientConfig();

    public static PrimordialAbyssConfig BIOME_PRIMORDIAL_ABYSS = ConfigBuilder.loadPrimordialAbyss();
    public static HasturianWastesConfig BIOME_HASTURIAN_WASTES = ConfigBuilder.loadHasturianWastes();

    public static AberrationConfig ENTITY_ABERRATION = ConfigBuilder.loadAberration();
    public static TentacleConfig ENTITY_TENTACLE = ConfigBuilder.loadTentacle();
    public static HasturConfig BOSS_HASTUR = ConfigBuilder.loadHastur();
}
