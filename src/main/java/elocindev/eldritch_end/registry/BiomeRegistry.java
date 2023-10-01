package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.worldgen.biome.HasturianWastes;
import elocindev.eldritch_end.worldgen.biome.PrimordialAbyss;

import com.google.common.base.Preconditions;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry {
    public static final RegistryKey<Biome> PRIMORDIAL_ABYSS = RegistryKey.of(RegistryKeys.BIOME,
        new Identifier(EldritchEnd.MODID, "primordial_abyss"));
    
    public static final RegistryKey<Biome> HASTURIAN_WASTES = RegistryKey.of(RegistryKeys.BIOME,
        new Identifier(EldritchEnd.MODID, "hasturian_wastes"));

    public static void bootstrap(Registerable<Biome> biomeRegisterable) {
		biomeRegisterable.register(BiomeRegistry.HASTURIAN_WASTES, HasturianWastes.createHasturianWastes());
        biomeRegisterable.register(BiomeRegistry.PRIMORDIAL_ABYSS, PrimordialAbyss.createPrimordialAbyss());
	}

    public static void register() {
        HasturianWastes.load(); PrimordialAbyss.load();

        TheEndBiomes.addHighlandsBiome(PRIMORDIAL_ABYSS, Configs.BIOME_PRIMORDIAL_ABYSS.biome_weight);
        TheEndBiomes.addMidlandsBiome(PRIMORDIAL_ABYSS, PRIMORDIAL_ABYSS, Configs.BIOME_PRIMORDIAL_ABYSS.biome_weight);

        TheEndBiomes.addHighlandsBiome(HASTURIAN_WASTES, Configs.BIOME_HASTURIAN_WASTES.biome_weight);
        
        // PrimordialAbyss.registerModifications();
        // HasturianWastes.registerModifications();
    }
}
