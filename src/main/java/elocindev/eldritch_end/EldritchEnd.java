package elocindev.eldritch_end;

import elocindev.eldritch_end.registry.EntityRegistry;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.config.ConfigBuilder;
import elocindev.eldritch_end.config.ConfigLoader;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EffectRegistry;
import elocindev.eldritch_end.registry.FeatureRegistry;
import elocindev.eldritch_end.registry.ItemGroupRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;

public class EldritchEnd implements ModInitializer {
	public static final String MODID = "eldritch_end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	private static boolean config = ConfigBuilder.hasStarted();

	@Override
	public void onInitialize() {
		ConfigLoader.init(config);

		GeckoLib.initialize();
		
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		FeatureRegistry.register();
		BiomeRegistry.register();
		EffectRegistry.register();
		ItemGroupRegistry.register();
	}
}
