package elocindev.eldritch_end.events;

import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.corruption.corruption_effect.CETentacleSpawn;
import elocindev.eldritch_end.utils.ServerUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class PlayerTickEventHandler implements ServerTickEvents.StartTick {
    private static CETentacleSpawn tentacle_config = CorruptionAPI.CONFIG.corruption_effects.tentacle_spawn;

    @Override
    public void onStartTick(MinecraftServer server) {
        if (server.getTicks() % 20 != 0) return;
        ServerUtils.healthDrainCheck(server);

        if (server.getTicks() % tentacle_config.getEffectRateTicks() != 0) return;
        ServerUtils.tentacleSummonCheck(server);
    }
}
