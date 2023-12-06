package elocindev.eldritch_end.config.entries.biomes;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class PrimordialAbyssConfig {
    @NecConfig
    public static PrimordialAbyssConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("primordial_abyss.json", "biomes");
    }

    public boolean enabled = true;
    public boolean enable_midlands = false;
    public boolean spawn_endermen = false;
    public boolean spawn_aberrations = true;
    public float biome_weight = 4.0F;
    public float biome_temperature = 1.0F;
    public boolean enable_tendril_patches = true;
    public int tendril_patch_chance = 10;
    public boolean enable_roots_generation = true;
    public int roots_generation_chance = 10;
    public boolean enable_leaves_generation = true;
}
