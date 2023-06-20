package elocindev.eldritch_end.config;

import elocindev.eldritch_end.config.entries.ClientConfig;
import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;

public class Configs {
    public static PrimordialAbyssConfig BIOME_PRIMORDIAL_ABYSS = ConfigBuilder.loadPrimordialAbyss();
    public static ClientConfig CLIENT_CONFIG = ConfigBuilder.loadClientConfig();

    public static void make() { ; }
}
