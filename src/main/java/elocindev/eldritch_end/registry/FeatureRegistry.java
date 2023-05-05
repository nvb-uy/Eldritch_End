package elocindev.eldritch_end.registry;

import java.util.List;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.worldgen.PrimordialAbyssSurface;
import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class FeatureRegistry {
    public static final Identifier PRIMORDIAL_ABYSS_SURFACE_ID = new Identifier(EldritchEnd.MODID, "primordial_abyss_surface");
    public static Feature<SurfaceConfig> PRIMORDIAL_ABYSS_SURFACE = new PrimordialAbyssSurface(SurfaceConfig.CODEC);
    public static ConfiguredFeature<SurfaceConfig, PrimordialAbyssSurface> PRIMORDIAL_ABYSS_SURFACE_CONFIGURED = new ConfiguredFeature<>(
                    (PrimordialAbyssSurface) PRIMORDIAL_ABYSS_SURFACE,
                    new SurfaceConfig(new Identifier("minecraft", "grass_block"))
    );

    public static PlacedFeature PRIMORDIAL_ABYSS_SURFACE_PLACED = new PlacedFeature(
            RegistryEntry.of(
                PRIMORDIAL_ABYSS_SURFACE_CONFIGURED
            ), List.of(SquarePlacementModifier.of())
    );

    public static void register() {
        Registry.register(Registry.FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE_CONFIGURED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE_PLACED);
    }
}