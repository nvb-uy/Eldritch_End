package elocindev.prominent.fabric_quilt.biome;

import elocindev.prominent.fabric_quilt.registry.BiomeRegistry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
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

		return (new Biome.Builder())
		.precipitation(Biome.Precipitation.NONE).temperature(0.5F).downfall(0.5F)
		.effects((new BiomeEffects.Builder())
		.waterColor(5920890).waterFogColor(2949228).fogColor(8809921).skyColor(0)
		.moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(settings.build())
		.generationSettings(builder.build()).build();
	}
}
