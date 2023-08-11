package elocindev.eldritch_end.worldgen.biome;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.FeatureRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.EndPlacedFeatures;

public class PrimordialAbyss {
	public static void register() {
		Registry.register(BuiltinRegistries.BIOME, BiomeRegistry.PRIMORDIAL_ABYSS.getValue(), createPrimordialAbyss());
	}
	
	private static Biome createPrimordialAbyss() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder()
				.feature(GenerationStep.Feature.SURFACE_STRUCTURES, EndPlacedFeatures.END_GATEWAY_RETURN);
		return compose(builder);
	}

	private static Biome compose(GenerationSettings.Builder builder) {
		SpawnSettings.Builder settings = new SpawnSettings.Builder();
		
		DefaultBiomeFeatures.addEndMobs(settings);

		ParticleEffect ambientParticle = ParticleTypes.ASH;

		return (new Biome.Builder())
		.precipitation(Biome.Precipitation.NONE)
		.temperature(Configs.BIOME_PRIMORDIAL_ABYSS.biome_temperature)
		.downfall(0.1F)

		.effects((new BiomeEffects.Builder())
			.waterColor(2367016).waterFogColor(2949228).fogColor(2758197).skyColor(1312788)
			.moodSound(BiomeMoodSound.CAVE)
			.particleConfig(new BiomeParticleConfig(ambientParticle, 0.3f))
			.build())

		.spawnSettings(settings.build())	
		.generationSettings(builder.build()).build();
	}

    public static void registerModifications() {
		BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeRegistry.PRIMORDIAL_ABYSS),
            GenerationStep.Feature.TOP_LAYER_MODIFICATION,
            RegistryKey.of(Registry.PLACED_FEATURE_KEY, FeatureRegistry.PRIMORDIAL_ABYSS_SURFACE_ID)
        );

		BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeRegistry.PRIMORDIAL_ABYSS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            RegistryKey.of(Registry.PLACED_FEATURE_KEY, FeatureRegistry.PRIMORDIAL_TREES_ID)
        );
    }
}
