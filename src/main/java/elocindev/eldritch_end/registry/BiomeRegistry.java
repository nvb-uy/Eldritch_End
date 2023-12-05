package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.worldgen.biome.HasturianWastes;
import elocindev.eldritch_end.worldgen.biome.PrimordialAbyss;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

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

        TheEndBiomes.addHighlandsBiome(HASTURIAN_WASTES, Configs.Biome.HASTURIAN_WASTES.biome_weight);
        TheEndBiomes.addHighlandsBiome(PRIMORDIAL_ABYSS, Configs.Biome.PRIMORDIAL_ABYSS.biome_weight);
        
        if (Configs.Biome.PRIMORDIAL_ABYSS.enable_midlands)
            TheEndBiomes.addMidlandsBiome(PRIMORDIAL_ABYSS, PRIMORDIAL_ABYSS, Configs.Biome.PRIMORDIAL_ABYSS.biome_weight);

        PrimordialAbyss.registerModifications();
        HasturianWastes.registerModifications();
    }

    public class SurfaceRules {
        private static final MaterialRules.MaterialRule HASTURIAN_MOSS = MaterialRules.block(BlockRegistry.HASTURIAN_MOSS.getDefaultState());
        private static final MaterialRules.MaterialRule ABYSMAL_FRONDS = MaterialRules.block(BlockRegistry.ABYSMAL_FRONDS.getDefaultState());


        public static MaterialRules.MaterialRule createHasturianWastes() {
            return MaterialRules.condition(
                    MaterialRules.biome(BiomeRegistry.HASTURIAN_WASTES),
                    MaterialRules.condition(
                    MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.aboveBottom(50), 0),
                            HASTURIAN_MOSS
                        )
                    )
            );
        }

        public static MaterialRules.MaterialRule createPrimordialAbyss() {
			return MaterialRules.condition(
				MaterialRules.biome(BiomeRegistry.PRIMORDIAL_ABYSS),
				MaterialRules.condition(
					MaterialRules.STONE_DEPTH_FLOOR,
                    MaterialRules.condition(
                        MaterialRules.aboveY(YOffset.aboveBottom(50), 0),
                        ABYSMAL_FRONDS
                    )
				)
			);
        }
    }
}
