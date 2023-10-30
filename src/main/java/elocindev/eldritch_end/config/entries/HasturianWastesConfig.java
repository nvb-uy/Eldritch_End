package elocindev.eldritch_end.config.entries;

import elocindev.eldritch_end.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class HasturianWastesConfig {
    @NecConfig
    public static HasturianWastesConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getNestedFile("hasturian_wastes.json", "biomes");
    }

    public boolean enabled = true;
    public float biome_weight = 3.0F;
    public float biome_temperature = 5.0F;
    public boolean enable_spike_generation = true;
    public int spike_generation_chance = 100;
    public int etyr_spawn_chance_per_block = 2;
    public boolean enable_grass_generation = true;
    public int grass_generation_chance = 10;
}
