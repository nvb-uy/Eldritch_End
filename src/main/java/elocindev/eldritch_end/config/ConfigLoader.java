package elocindev.eldritch_end.config;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;



public class ConfigLoader {
    public static void init(boolean started) {
        
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success)
		->  {
			if (started) {
                Configs.make();

				Configs.BIOME_PRIMORDIAL_ABYSS = ConfigBuilder.loadPrimordialAbyss();
                Configs.CLIENT_CONFIG = ConfigBuilder.loadClientConfig();
			}
			
		});
		
        EldritchEnd.LOGGER.info("Eldritch End's Config Loaded!");
    }
}