package elocindev.eldritch_end.config;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;



public class ConfigLoader {
    public static void init(boolean started) {
        
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success)
		->  {
			if (started) {
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
                    Configs.CLIENT_CONFIG = ConfigBuilder.loadClientConfig();

				Configs.BIOME_PRIMORDIAL_ABYSS = ConfigBuilder.loadPrimordialAbyss();
			}
			
		});
		
        EldritchEnd.LOGGER.info("Eldritch End's Config Loaded!");
    }
}