package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.worldgen.WorldgenProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModelGenerator::new);
        pack.addProvider(BlockLootTableGenerator::new);
        pack.addProvider(TagGenerator::new);
        pack.addProvider(BiomeTagProvider::new);
        pack.addProvider(WorldgenProvider::new);
    }

    @Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.BIOME, BiomeRegistry::bootstrap);
	}
}
