package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.worldgen.feature.PrimordialTreeFeature;
import elocindev.eldritch_end.worldgen.feature.TreeConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class WorldgenRegistry {
    public static final Identifier PRIMORDIAL_TREE_ID = new Identifier(EldritchEnd.MODID, "primordial_tree");
    public static Feature<TreeConfig> PRIMORDIAL_TREE_FEATURE = new PrimordialTreeFeature(TreeConfig.CODEC);

    public static void register() {
        Registry.register(Registries.FEATURE, PRIMORDIAL_TREE_ID, PRIMORDIAL_TREE_FEATURE);
    }
}
