package elocindev.eldritch_end.config;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ConfigLoader {
    public static void init(boolean started) {
        
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success)
		->  {
			if (started) {                  
				Configs.BIOME_PRIMORDIAL_ABYSS = ConfigBuilder.loadPrimordialAbyss();
                Configs.BIOME_HASTURIAN_WASTES = ConfigBuilder.loadHasturianWastes();
                Configs.ENTITY_ABERRATION = ConfigBuilder.loadAberration();
                Configs.ENTITY_TENTACLE = ConfigBuilder.loadTentacle();
			}
		});
		
        EldritchEnd.LOGGER.info("Eldritch End's Config Loaded!");
    }

    public static void initClient() {
        Configs.CLIENT_CONFIG = ConfigBuilder.loadClientConfig();
        
        EldritchEnd.LOGGER.info("Eldritch End's Client Config Loaded!");
    }
}