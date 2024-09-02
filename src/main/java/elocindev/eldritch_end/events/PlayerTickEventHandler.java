package elocindev.eldritch_end.events;

import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.corruption.corruption_effect.CEEyeSpawn;
import elocindev.eldritch_end.corruption.corruption_effect.CETentacleSpawn;
import elocindev.eldritch_end.utils.ServerUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class PlayerTickEventHandler implements ServerTickEvents.StartTick {
    private static CETentacleSpawn tentacle_config = CorruptionAPI.CONFIG.corruption_effects.tentacle_spawn;
    private static CEEyeSpawn eye_config = CorruptionAPI.CONFIG.corruption_effects.ominous_eye_spawn;

    private int counter = 0;

    @Override
    public void onStartTick(MinecraftServer server) {
        counter++;

        if (counter % 20 == 0)
            ServerUtils.healthDrainCheck(server);

        if (counter % tentacle_config.getEffectRateTicks() == 0)
            ServerUtils.tentacleSummonCheck(server);

        if (counter % eye_config.getEffectRateTicks() == 0)
            ServerUtils.ominousEyeSummonCheck(server);
    }
}
