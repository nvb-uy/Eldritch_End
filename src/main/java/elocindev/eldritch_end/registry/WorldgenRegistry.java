package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.worldgen.biome.surface.HasturianWastesSurface;
import elocindev.eldritch_end.worldgen.biome.surface.PrimordialAbyssSurface;
import elocindev.eldritch_end.worldgen.feature.HasturianSpikeFeature;
import elocindev.eldritch_end.worldgen.feature.PrimordialTreeFeature;
import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import elocindev.eldritch_end.worldgen.feature.TreeConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class WorldgenRegistry {
    public class Rules {
        MaterialRules.MaterialRule HASTURIAN_MOSS = MaterialRules.condition(
                MaterialRules.biome(BiomeRegistry.HASTURIAN_WASTES),
                MaterialRules.condition(
                    MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.condition(
                                MaterialRules.aboveY(YOffset.aboveBottom(50), 2),
                                MaterialRules.block(BlockRegistry.HASTURIAN_MOSS.getDefaultState())
                        )
                )
        );
    }

    public static final Identifier PRIMORDIAL_TREE_ID = new Identifier(EldritchEnd.MODID, "primordial_tree");
    public static final Identifier PRIMORDIAL_ABYSS_SURFACE_ID = new Identifier(EldritchEnd.MODID, "primordial_abyss_surface");
    public static final Identifier HASTURIAN_WASTES_SURFACE_ID = new Identifier(EldritchEnd.MODID, "hasturian_wastes_surface");
    public static final Identifier HASTURIAN_SPIKES_ID = new Identifier(EldritchEnd.MODID, "hasturian_spikes");

    public static Feature<TreeConfig> PRIMORDIAL_TREE_FEATURE = new PrimordialTreeFeature(TreeConfig.CODEC);
    public static Feature<SurfaceConfig> PRIMORDIAL_ABYSS_SURFACE_FEATURE = new PrimordialAbyssSurface(SurfaceConfig.CODEC);
    public static Feature<SurfaceConfig> HASTURIAN_WASTES_SURFACE_FEATURE = new HasturianWastesSurface(SurfaceConfig.CODEC);
    public static Feature<DefaultFeatureConfig> HASTURIAN_SPIKES_FEATURE = new HasturianSpikeFeature(DefaultFeatureConfig.CODEC);


    public static void register() {
        Registry.register(Registries.FEATURE, PRIMORDIAL_ABYSS_SURFACE_ID, PRIMORDIAL_ABYSS_SURFACE_FEATURE);
        Registry.register(Registries.FEATURE, HASTURIAN_WASTES_SURFACE_ID, HASTURIAN_WASTES_SURFACE_FEATURE);
        Registry.register(Registries.FEATURE, HASTURIAN_SPIKES_ID, HASTURIAN_SPIKES_FEATURE);
        Registry.register(Registries.FEATURE, PRIMORDIAL_TREE_ID, PRIMORDIAL_TREE_FEATURE);
    }
}
