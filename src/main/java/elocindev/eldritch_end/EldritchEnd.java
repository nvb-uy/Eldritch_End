package elocindev.eldritch_end;

import elocindev.eldritch_end.events.PlayerTickEventHandler;
import elocindev.eldritch_end.registry.*;
import mod.azure.azurelib.AzureLib;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.compat.EtyrAttributeBuilder;
import elocindev.eldritch_end.config.ConfigLoader;

public class EldritchEnd implements ModInitializer {
	public static final String MODID = "eldritch_end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	// Test

	@Override
	public void onInitialize() {
		ConfigLoader.register();
		ConfigLoader.initDatapack(true);

		AzureLib.initialize();

		AttributeRegistry.register();
		BiomeRegistry.register();
		ArmorRegistry.register();
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		EffectRegistry.register();
		ItemGroupRegistry.register();
		WorldgenRegistry.register();
		StructureRegistry.register();
		SoundEffectRegistry.register();

		EtyrAttributeBuilder.buildItemTag();
		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickEventHandler());
	}
}
