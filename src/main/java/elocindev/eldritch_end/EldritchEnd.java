package elocindev.eldritch_end;

import elocindev.eldritch_end.registry.*;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.config.ConfigBuilder;
import elocindev.eldritch_end.config.ConfigLoader;
import software.bernie.geckolib3.GeckoLib;

public class EldritchEnd implements ModInitializer {
	public static final String MODID = "eldritch_end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	private static boolean config = ConfigBuilder.hasStarted();

	@Override
	public void onInitialize() {
		ConfigLoader.init(config);

		GeckoLib.initialize();

		ArmorRegistry.register();
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		FeatureRegistry.register();
		BiomeRegistry.register();
		EffectRegistry.register();
		ItemGroupRegistry.register();
	}
}
