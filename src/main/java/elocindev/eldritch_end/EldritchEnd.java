package elocindev.eldritch_end;

import elocindev.eldritch_end.registry.*;
import mod.azure.azurelib.AzureLib;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.config.ConfigLoader;

public class EldritchEnd implements ModInitializer {
	public static final String MODID = "eldritch_end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		ConfigLoader.register();
		ConfigLoader.initDatapack(true);

		AzureLib.initialize();

		BiomeRegistry.register();
		ArmorRegistry.register();
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		// FeatureRegistry.register();
		EffectRegistry.register();
		ItemGroupRegistry.register();
		WorldgenRegistry.register();
	}
}
