package elocindev.eldritch_end.registry;

import java.util.List;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.worldgen.BiomeSurfaceGeneration;
import elocindev.eldritch_end.worldgen.PrimordialTreeFeature;
import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import elocindev.eldritch_end.worldgen.feature.TreeConfig;
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
    public static Feature<SurfaceConfig> PRIMORDIAL_ABYSS_SURFACE = new BiomeSurfaceGeneration(SurfaceConfig.CODEC);
    public static ConfiguredFeature<SurfaceConfig, BiomeSurfaceGeneration> PRIMORDIAL_ABYSS_SURFACE_CONFIGURED = new ConfiguredFeature<>(
                    (BiomeSurfaceGeneration) PRIMORDIAL_ABYSS_SURFACE,
                    new SurfaceConfig(new Identifier("eldritch_end", "abysmal_fronds"))
    );

    public static PlacedFeature PRIMORDIAL_ABYSS_SURFACE_PLACED = new PlacedFeature(
            RegistryEntry.of(
                PRIMORDIAL_ABYSS_SURFACE_CONFIGURED
            ), List.of(SquarePlacementModifier.of())
    );

    public static final Identifier PRIMORDIAL_TREES_ID = new Identifier(EldritchEnd.MODID, "primordial_abyss_surface");

    public static Feature<TreeConfig> PRIMORDIAL_TREES = new PrimordialTreeFeature(TreeConfig.CODEC);
    public static ConfiguredFeature<TreeConfig, PrimordialTreeFeature> PRIMORDIAL_TREE_CONFIGURED = new ConfiguredFeature<>(
                    (PrimordialTreeFeature) PRIMORDIAL_TREES,
                    new TreeConfig(new Identifier("eldritch_end", "primordial_log"))
    );

    public static PlacedFeature PRIMORDIAL_TREE_PLACED = new PlacedFeature(
            RegistryEntry.of(
                PRIMORDIAL_TREE_CONFIGURED
            ), List.of(SquarePlacementModifier.of())
    );

    public static void register() {
        Registry.register(Registry.FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE_CONFIGURED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE_PLACED);
    }
}