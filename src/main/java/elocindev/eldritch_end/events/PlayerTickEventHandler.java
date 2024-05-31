package elocindev.eldritch_end.events;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PlayerTickEventHandler implements ServerTickEvents.StartTick {

    // Todo: Config; The maximum time window since last attack (in ticks)
    public static int COMBAT_DURATION = 100;
    public static float TENTACLE_CHANCE = 0.25f;
    public static int TENTACLE_THRESHOLD = 25;
    public static int EYE_THRESHOLD = 75;
    private boolean hasValidCorruptionConditions(ServerPlayerEntity serverPlayer) {
        return serverPlayer.getAttributeValue(AttributeRegistry.CORRUPTION) >= TENTACLE_THRESHOLD
                && (serverPlayer.age - serverPlayer.getLastAttackTime() <= COMBAT_DURATION)
                && serverPlayer.getRandom().nextFloat() <= TENTACLE_CHANCE;
    }

    public static void summonTentacle(ServerPlayerEntity serverPlayer, World world) {
        TentacleEntity tentacle = EntityRegistry.TENTACLE.create(world);
        if (tentacle == null || serverPlayer == null || !serverPlayer.isOnGround()) return;

        tentacle.setPosition(serverPlayer.getPos());
        world.spawnEntity(tentacle);

        serverPlayer.setVelocity(0, 0.90, 0);
        serverPlayer.velocityModified = true;
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        if (server.getTicks() % 600 != 0) return;
        for (ServerPlayerEntity serverPlayer: server.getPlayerManager().getPlayerList()) {
            if (hasValidCorruptionConditions(serverPlayer)) {
                summonTentacle(serverPlayer, serverPlayer.getWorld());
            }
        }
    }
}
