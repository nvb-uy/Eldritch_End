package elocindev.eldritch_end.fabric.registry;

import elocindev.eldritch_end.fabric.EldritchEnd;
import elocindev.eldritch_end.fabric.feature.GrassPatch;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry {
    public static final Feature<DefaultFeatureConfig> GRASS_PATCH_FEATURE = Registry.register(
        Registry.FEATURE, new Identifier(EldritchEnd.MODID, "grass_patch"), new GrassPatch(DefaultFeatureConfig.CODEC));
}
