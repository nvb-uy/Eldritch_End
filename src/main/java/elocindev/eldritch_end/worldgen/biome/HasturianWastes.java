package elocindev.eldritch_end.worldgen.biome;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import elocindev.eldritch_end.registry.WorldgenRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;
import net.minecraft.world.gen.GenerationStep.Feature;

public class HasturianWastes {
	public static void load() {}

	public static Biome createHasturianWastes() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		return compose(builder);
	}

	private static Biome compose(GenerationSettings.Builder builder) {
		SpawnSettings.Builder settings = new SpawnSettings.Builder();
	
		if (Configs.Biome.HASTURIAN_WASTES.spawn_dendlers)
			settings.spawn(SpawnGroup.MONSTER, new SpawnEntry(EntityRegistry.DENDLER, 1, 1, 3));

		return (new Biome.Builder())
		.precipitation(false)
		.temperature(Configs.Biome.PRIMORDIAL_ABYSS.biome_temperature)
		.downfall(0.1F)
		
		.effects((new BiomeEffects.Builder())
			.waterColor(2367016).waterFogColor(2949228).fogColor(2758197).skyColor(1312788)
			.build())

		.spawnSettings(settings.build())
		.generationSettings(builder.build()).build();
	}

    public static void registerModifications() {
		BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeRegistry.HASTURIAN_WASTES),
            Feature.LOCAL_MODIFICATIONS,
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, WorldgenRegistry.HASTURIAN_WASTES_SURFACE_ID)
        );

		BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeRegistry.HASTURIAN_WASTES),
            Feature.SURFACE_STRUCTURES,
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, WorldgenRegistry.HASTURIAN_SPIKES_ID)
        );
    }
}
