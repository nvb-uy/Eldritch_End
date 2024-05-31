package elocindev.eldritch_end.utils;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class ServerUtils {

    // Todo: Config; The maximum time window since last attack (in ticks)
    public static int COMBAT_DURATION = 100;
    public static float TENTACLE_CHANCE = 0.25f;
    public static int TENTACLE_THRESHOLD = 25;
    public static int EYE_THRESHOLD = 75;
    public static int HEALTH_DRAIN_THRESHOLD = 100;

    public static boolean hasValidCorruptionConditions(ServerPlayerEntity serverPlayer) {
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

    public static void healthDrainCheck(MinecraftServer server) {
        for (ServerPlayerEntity serverPlayer: server.getPlayerManager().getPlayerList()) {
            if (serverPlayer.getAttributeValue(AttributeRegistry.CORRUPTION) >= HEALTH_DRAIN_THRESHOLD) {
                serverPlayer.damage(serverPlayer.getDamageSources().generic(), serverPlayer.getMaxHealth() * 0.1f);
            }
        }
    }

    public static void tentacleSummonCheck(MinecraftServer server) {
        for (ServerPlayerEntity serverPlayer: server.getPlayerManager().getPlayerList()) {
            if (ServerUtils.hasValidCorruptionConditions(serverPlayer)) {
                ServerUtils.summonTentacle(serverPlayer, serverPlayer.getWorld());
            }
        }
    }
}
