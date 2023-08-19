package elocindev.eldritch_end.registry;

import java.util.List;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.worldgen.feature.HasturianSpikeFeature;
import elocindev.eldritch_end.worldgen.feature.PrimordialTreeFeature;
import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import elocindev.eldritch_end.worldgen.feature.TreeConfig;
import elocindev.eldritch_end.worldgen.feature.surface.HasturianWastesSurface;
import elocindev.eldritch_end.worldgen.feature.surface.PrimordialAbyssSurface;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class FeatureRegistry {
    public static final Identifier PRIMORDIAL_ABYSS_SURFACE_ID = new Identifier(EldritchEnd.MODID, "abyssal_surface");
    public static Feature<SurfaceConfig> PRIMORDIAL_ABYSS_SURFACE = new PrimordialAbyssSurface(SurfaceConfig.CODEC);
    public static ConfiguredFeature<SurfaceConfig, PrimordialAbyssSurface> PRIMORDIAL_ABYSS_SURFACE_CONFIGURED = new ConfiguredFeature<>(
                    (PrimordialAbyssSurface) PRIMORDIAL_ABYSS_SURFACE,
                    new SurfaceConfig(new Identifier("eldritch_end", "abysmal_fronds"))
    );

    public static PlacedFeature PRIMORDIAL_ABYSS_SURFACE_PLACED = new PlacedFeature(
            RegistryEntry.of(
                PRIMORDIAL_ABYSS_SURFACE_CONFIGURED
            ), List.of(SquarePlacementModifier.of())
    );

    public static final Identifier HASTURIAN_WASTES_SURFACE_ID = new Identifier(EldritchEnd.MODID, "mossy_surface");
    public static Feature<SurfaceConfig> HASTURIAN_WASTES_SURFACE = new HasturianWastesSurface(SurfaceConfig.CODEC);

    public static ConfiguredFeature<SurfaceConfig, HasturianWastesSurface> HASTURIAN_WASTES_SURFACE_CONFIGURED = new ConfiguredFeature<>(
                    (HasturianWastesSurface) HASTURIAN_WASTES_SURFACE,
                    new SurfaceConfig(new Identifier("eldritch_end", "hasturian_moss"))
    );

    public static PlacedFeature HASTURIAN_WASTES_SURFACE_PLACED = new PlacedFeature(
            RegistryEntry.of(
                HASTURIAN_WASTES_SURFACE_CONFIGURED
            ), List.of(SquarePlacementModifier.of())
    );

    public static Identifier HASTURIAN_SPIKES_ID = new Identifier(EldritchEnd.MODID, "hasturian_spike");
    public static Feature<DefaultFeatureConfig> HASTURIAN_SPIKES = new HasturianSpikeFeature(DefaultFeatureConfig.CODEC);
    public static ConfiguredFeature<DefaultFeatureConfig, HasturianSpikeFeature> HASTURIAN_SPIKES_CONFIGURED = new ConfiguredFeature<>(
                    (HasturianSpikeFeature) HASTURIAN_SPIKES,
                    new DefaultFeatureConfig()
    );

    public static PlacedFeature HASTURIAN_SPIKES_PLACED = new PlacedFeature(
            RegistryEntry.of(
                HASTURIAN_SPIKES_CONFIGURED
            ), List.of(SquarePlacementModifier.of())
    );

    public static final Identifier PRIMORDIAL_TREES_ID = new Identifier(EldritchEnd.MODID, "primordial_tree");

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

        Registry.register(Registry.FEATURE, HASTURIAN_WASTES_SURFACE_ID, HASTURIAN_WASTES_SURFACE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, HASTURIAN_WASTES_SURFACE_ID, HASTURIAN_WASTES_SURFACE_CONFIGURED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, HASTURIAN_WASTES_SURFACE_ID, HASTURIAN_WASTES_SURFACE_PLACED);

        Registry.register(Registry.FEATURE, PRIMORDIAL_TREES_ID, PRIMORDIAL_TREES);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PRIMORDIAL_TREES_ID, PRIMORDIAL_TREE_CONFIGURED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PRIMORDIAL_TREES_ID, PRIMORDIAL_TREE_PLACED);

        Registry.register(Registry.FEATURE, HASTURIAN_SPIKES_ID, HASTURIAN_SPIKES);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, HASTURIAN_SPIKES_ID, HASTURIAN_SPIKES_CONFIGURED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, HASTURIAN_SPIKES_ID, HASTURIAN_SPIKES_PLACED);
    }
}