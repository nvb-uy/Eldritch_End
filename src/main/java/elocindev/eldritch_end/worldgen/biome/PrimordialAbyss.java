package elocindev.eldritch_end.worldgen.biome;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;

public class PrimordialAbyss {
	public static void load() {}

	public static Biome createPrimordialAbyss() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		return compose(builder);
	}

	public static void addAberrations(SpawnSettings.Builder builder) {
    	builder.spawn(SpawnGroup.MONSTER, new SpawnEntry(EntityRegistry.ABERRATION, 1, 1, 3));
    }

	private static Biome compose(GenerationSettings.Builder builder) {
		SpawnSettings.Builder settings = new SpawnSettings.Builder();

		if (Configs.BIOME_PRIMORDIAL_ABYSS.spawn_aberrations)
			addAberrations(settings);

		ParticleEffect ambientParticle = ParticleTypes.ASH;

		return (new Biome.Builder())
		.precipitation(false)
		.temperature(Configs.BIOME_PRIMORDIAL_ABYSS.biome_temperature)
		.downfall(0.1F)
		
		.effects((new BiomeEffects.Builder())
			.waterColor(2367016).waterFogColor(2949228).fogColor(2758197).skyColor(1312788)
			.particleConfig(new BiomeParticleConfig(ambientParticle, 0.1f))
			.build())

		.spawnSettings(settings.build())	
		.generationSettings(builder.build()).build();
	}

    public static void registerModifications() {
		// BiomeModifications.addFeature(
        //     BiomeSelectors.includeByKey(BiomeRegistry.PRIMORDIAL_ABYSS),
        //     GenerationStep.Feature.RAW_GENERATION,
        //     RegistryKey.of(RegistryKeys.PLACED_FEATURE, FeatureRegistry.PRIMORDIAL_ABYSS_SURFACE_ID)
        // );

		// BiomeModifications.addFeature(
        //     BiomeSelectors.includeByKey(BiomeRegistry.PRIMORDIAL_ABYSS),
        //     GenerationStep.Feature.TOP_LAYER_MODIFICATION,
        //     RegistryKey.of(RegistryKeys.PLACED_FEATURE, FeatureRegistry.PRIMORDIAL_TREES_ID)
        // );
    }
}
