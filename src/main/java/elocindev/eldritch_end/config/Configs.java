package elocindev.eldritch_end.config;

import elocindev.eldritch_end.config.entries.ClientConfig;
import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;
import elocindev.eldritch_end.config.entries.entities.AberrationConfig;

public class Configs {
    public static ClientConfig CLIENT_CONFIG = ConfigBuilder.loadClientConfig();
    public static PrimordialAbyssConfig BIOME_PRIMORDIAL_ABYSS = ConfigBuilder.loadPrimordialAbyss();
    public static AberrationConfig ENTITY_ABERRATION = ConfigBuilder.loadAberration();
}
