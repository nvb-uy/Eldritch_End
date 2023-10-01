package elocindev.eldritch_end.datagen;

import java.util.concurrent.CompletableFuture;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.registry.BiomeRegistry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class BiomeTagProvider extends FabricTagProvider<Biome> {
	public BiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, RegistryKeys.BIOME, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup registries) {
		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, new Identifier(EldritchEnd.MODID, "biomes")))
		.add(BiomeRegistry.PRIMORDIAL_ABYSS)		
        .add(BiomeRegistry.HASTURIAN_WASTES);
	}
}