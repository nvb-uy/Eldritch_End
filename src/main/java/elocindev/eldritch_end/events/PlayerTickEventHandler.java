package elocindev.eldritch_end.events;

import elocindev.eldritch_end.utils.ServerUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class PlayerTickEventHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        if (server.getTicks() % 20 != 0) return;
        ServerUtils.healthDrainCheck(server);

        if (server.getTicks() % 600 != 0) return;
        ServerUtils.tentacleSummonCheck(server);
    }
}
