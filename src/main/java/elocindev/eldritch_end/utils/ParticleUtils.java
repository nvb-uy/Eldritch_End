package elocindev.eldritch_end.utils;

import elocindev.eldritch_end.registry.PacketRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Math;

public class ParticleUtils {
    public static DustParticleEffect ORANGE_DUST = new DustParticleEffect(Vec3d.unpackRgb(0xFFA500).toVector3f(), 2);
    public static DustParticleEffect PURPLE_DUST = new DustParticleEffect(Vec3d.unpackRgb(0x6400FF).toVector3f(), 2);
    public static DustParticleEffect BIG_PURPLE_DUST = new DustParticleEffect(Vec3d.unpackRgb(0x6400FF).toVector3f(), 10);

    public static void sendParticlesToAll(Entity emitterEntity, String particleEffect) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(emitterEntity.getId());
        buffer.writeString(particleEffect);

        for (ServerPlayerEntity target : PlayerLookup.tracking((ServerWorld)emitterEntity.getWorld(), new ChunkPos((int)emitterEntity.getPos().x / 16, (int)emitterEntity.getPos().z / 16))) {
            ServerPlayNetworking.send((ServerPlayerEntity) target, PacketRegistry.PARTICLE_PACKET, buffer);
        }
    }

    public static void distanceWarningParticles(Entity targetEntity) {
        for (int i = 0; i < 8; i++) {
            targetEntity.getWorld().addParticle(PURPLE_DUST,
                    targetEntity.getX() + Math.sin(i) * 1.4,
                    targetEntity.getY() + (double) i / 2,
                    targetEntity.getZ() + Math.cos(i) * 1.4,
                    (float) (0.1f * Math.sin(i) * 2),
                    0,
                    (float) (0.1f * Math.cos(i) * 2));
        }
    }

    public static void teleportationRing(Entity targetEntity) {
        for (int i = 0; i < 16; i++) {
            targetEntity.getWorld().addParticle(ORANGE_DUST,
                    targetEntity.getX() + Math.sin(i) * 1.4,
                    targetEntity.getY(),
                    targetEntity.getZ() + Math.cos(i) * 1.4,
                    (float) (0.1f * Math.sin(i) * 2),
                    0,
                    (float) (0.1f * Math.cos(i) * 2));

            targetEntity.getWorld().addParticle(ORANGE_DUST,
                    targetEntity.getX() + Math.sin(i) * 3,
                    targetEntity.getY(),
                    targetEntity.getZ() + Math.cos(i) * 3,
                    (float) (0.1f * Math.sin(i) * 2),
                    0,
                    (float) (0.1f * Math.cos(i) * 2));
        }
    }
}
