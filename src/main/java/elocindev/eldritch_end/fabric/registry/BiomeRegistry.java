package elocindev.prominent.fabric_quilt.registry;

import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import elocindev.prominent.fabric_quilt.EldritchEnd;
import elocindev.prominent.fabric_quilt.biome.PrimordialAbyss;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry {
    public static final RegistryKey<Biome> PRIMORDIAL_ABYSS = RegistryKey.of(Registry.BIOME_KEY, 
        new Identifier(EldritchEnd.MODID, "primordial_abyss"));

    public static void register() {
        PrimordialAbyss.register();

        TheEndBiomes.addHighlandsBiome(PRIMORDIAL_ABYSS, 5.0);
    }
}
