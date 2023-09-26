package elocindev.eldritch_end.worldgen.biome;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.FeatureRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.EndPlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

public class HasturianWastes {
	public static void register() {
		/* todo: fix
		Registry.register(BuiltinRegistries.BIOME, BiomeRegistry.HASTURIAN_WASTES.getValue(), createHasturianWastes());
		 */
	}
	
	private static Biome createHasturianWastes() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder()
				.feature(GenerationStep.Feature.SURFACE_STRUCTURES, (RegistryEntry<PlacedFeature>) EndPlacedFeatures.END_SPIKE);
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
		BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeRegistry.HASTURIAN_WASTES),
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, FeatureRegistry.HASTURIAN_WASTES_SURFACE_ID)
        );

		BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeRegistry.HASTURIAN_WASTES),
            GenerationStep.Feature.SURFACE_STRUCTURES,
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, FeatureRegistry.HASTURIAN_SPIKES_ID)
        );
    }
}
