package elocindev.eldritch_end.worldgen.biome;

import elocindev.eldritch_end.config.Configs;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class HasturianWastes {
	public static void load() {}

	public static Biome createHasturianWastes() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		return compose(builder);
	}

	private static Biome compose(GenerationSettings.Builder builder) {
		SpawnSettings.Builder settings = new SpawnSettings.Builder();

		return (new Biome.Builder())
		.precipitation(false)
		.temperature(Configs.BIOME_PRIMORDIAL_ABYSS.biome_temperature)
		.downfall(0.1F)
		
		.effects((new BiomeEffects.Builder())
			.waterColor(2367016).waterFogColor(2949228).fogColor(2758197).skyColor(1312788)
			.build())

		.spawnSettings(settings.build())	
		.generationSettings(builder.build()).build();
	}

    public static void registerModifications() {
		// BiomeModifications.addFeature(
        //     BiomeSelectors.includeByKey(BiomeRegistry.HASTURIAN_WASTES),
        //     GenerationStep.Feature.LOCAL_MODIFICATIONS,
        //     RegistryKey.of(RegistryKeys.PLACED_FEATURE, FeatureRegistry.HASTURIAN_WASTES_SURFACE_ID)
        // );

		// BiomeModifications.addFeature(
        //     BiomeSelectors.includeByKey(BiomeRegistry.HASTURIAN_WASTES),
        //     GenerationStep.Feature.SURFACE_STRUCTURES,
        //     RegistryKey.of(RegistryKeys.PLACED_FEATURE, FeatureRegistry.HASTURIAN_SPIKES_ID)
        // );
    }
}
