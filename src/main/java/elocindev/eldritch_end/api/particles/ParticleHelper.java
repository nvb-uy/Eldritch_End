package elocindev.eldritch_end.api.particles;

import elocindev.eldritch_end.api.particles.packets.ParticlePackets;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

import static elocindev.eldritch_end.api.targeting.TargetHelper.launchPoint;
import static elocindev.eldritch_end.api.targeting.VectorHelper.axisFromRotation;
import static elocindev.eldritch_end.api.targeting.VectorHelper.rotateAround;

public class ParticleHelper {
    private static Random rng = new Random();

    public ParticleHelper() {
    }

    public static void sendBatches(Entity trackedEntity, ParticleBatch[] batches) {
        sendBatches(trackedEntity, batches, true);
    }

    public static void sendBatches(Entity trackedEntity, ParticleBatch[] batches, boolean includeSourceEntity) {
        sendBatches(trackedEntity, batches, 1.0F, PlayerLookup.tracking(trackedEntity), includeSourceEntity);
    }

    public static void sendBatches(Entity trackedEntity, ParticleBatch[] batches, float countMultiplier, Collection<ServerPlayerEntity> trackers) {
        sendBatches(trackedEntity, batches, countMultiplier, trackers, true);
    }

    public static void sendBatches(Entity trackedEntity, ParticleBatch[] batches, float countMultiplier, Collection<ServerPlayerEntity> trackers, boolean includeSourceEntity) {
        if (batches != null && batches.length != 0) {
            int sourceEntityId = trackedEntity.getId();
            ParticlePackets.ParticleBatches.SourceType sourceType = ParticlePackets.ParticleBatches.SourceType.COORDINATE;
            ArrayList<ParticlePackets.ParticleBatches.Spawn> spawns = new ArrayList();
            ParticleBatch[] var8 = batches;
            int var9 = batches.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                ParticleBatch batch = var8[var10];
                Vec3d sourceLocation = Vec3d.ZERO;
                switch (sourceType) {
                    case ENTITY:
                    default:
                        break;
                    case COORDINATE:
                        sourceLocation = origin(trackedEntity, batch.origin);
                }

                spawns.add(new ParticlePackets.ParticleBatches.Spawn(includeSourceEntity ? sourceEntityId : 0, trackedEntity.getYaw(), trackedEntity.getPitch(), sourceLocation, batch));
            }

            PacketByteBuf packet = (new ParticlePackets.ParticleBatches(sourceType, spawns)).write(countMultiplier);
            if (trackedEntity instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity)trackedEntity;
                sendWrittenBatchesToPlayer(serverPlayer, packet);
            }

            trackers.forEach((serverPlayerx) -> {
                sendWrittenBatchesToPlayer(serverPlayerx, packet);
            });
        }
    }

    private static void sendWrittenBatchesToPlayer(ServerPlayerEntity serverPlayer, PacketByteBuf packet) {
        try {
            if (ServerPlayNetworking.canSend(serverPlayer, ParticlePackets.ParticleBatches.ID)) {
                ServerPlayNetworking.send(serverPlayer, ParticlePackets.ParticleBatches.ID, packet);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void play(World world, Entity source, ParticleBatch[] batches) {
        if (batches != null) {
            ParticleBatch[] var3 = batches;
            int var4 = batches.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                ParticleBatch batch = var3[var5];
                play(world, source, 0.0F, 0.0F, batch);
            }

        }
    }

    public static void play(World world, Entity source, ParticleBatch batch) {
        play(world, source, 0.0F, 0.0F, batch);
    }

    public static void play(World world, Entity entity, float yaw, float pitch, ParticleBatch batch) {
        play(world, (long)entity.age, origin(entity, batch.origin), entity.getWidth(), yaw, pitch, batch);
    }

    public static void play(World world, long time, Vec3d origin, float width, float yaw, float pitch, ParticleBatch batch) {
        try {
            Identifier id = new Identifier(batch.particle_id);
            ParticleEffect particle = (ParticleEffect) Registries.PARTICLE_TYPE.get(id);
            float count = batch.count;
            if (batch.count < 1.0F) {
                count = rng.nextFloat() < batch.count ? 1.0F : 0.0F;
            }

            for(int i = 0; (float)i < count; ++i) {
                Vec3d direction = direction(batch, time, yaw, pitch);
                Vec3d particleSpecificOrigin = origin.add(offset(width, batch.extent, batch.shape, direction.normalize(), batch.rotation, yaw, pitch));
                if (batch.pre_spawn_travel != 0.0F) {
                    particleSpecificOrigin = particleSpecificOrigin.add(direction.multiply((double)batch.pre_spawn_travel));
                }

                if (batch.invert) {
                    direction = direction.negate();
                }

                world.addParticle(particle, true, particleSpecificOrigin.x, particleSpecificOrigin.y, particleSpecificOrigin.z, direction.x, direction.y, direction.z);
            }
        } catch (Exception var14) {
            System.err.println("Failed to play particle batch - " + var14.getMessage());
            var14.printStackTrace();
        }

    }

    public static List<ParticleHelper.SpawnInstruction> convertToInstructions(World world, ParticlePackets.ParticleBatches packet) {
        ArrayList<ParticleHelper.SpawnInstruction> instructions = new ArrayList();
        ParticlePackets.ParticleBatches.SourceType sourceType = packet.sourceType();
        Iterator var4 = packet.spawns().iterator();

        while(var4.hasNext()) {
            ParticlePackets.ParticleBatches.Spawn spawn = (ParticlePackets.ParticleBatches.Spawn)var4.next();
            float yaw = spawn.yaw();
            float pitch = spawn.pitch();
            ParticleBatch batch = spawn.batch();
            Vec3d origin = Vec3d.ZERO;
            float width = 0.5F;
            switch (sourceType) {
                case ENTITY:
                    Entity entity = world.getEntityById(spawn.sourceEntityId());
                    origin = origin(entity, batch.origin);
                    break;
                case COORDINATE:
                    origin = spawn.sourceLocation();
            }

            Identifier id = new Identifier(batch.particle_id);
            ParticleEffect particle = (ParticleEffect)Registries.PARTICLE_TYPE.get(id);
            float count = batch.count;
            if (batch.count < 1.0F) {
                count = rng.nextFloat() < batch.count ? 1.0F : 0.0F;
            }

            for(int i = 0; (float)i < count; ++i) {
                Vec3d direction = direction(batch, world.getTime(), yaw, pitch);
                Vec3d particleSpecificOrigin = origin.add(offset(width, batch.extent, batch.shape, direction.normalize(), batch.rotation, yaw, pitch));
                if (batch.pre_spawn_travel != 0.0F) {
                    particleSpecificOrigin = particleSpecificOrigin.add(direction.multiply((double)batch.pre_spawn_travel));
                }

                if (batch.invert) {
                    direction = direction.negate();
                }

                instructions.add(new ParticleHelper.SpawnInstruction(particle, particleSpecificOrigin.x, particleSpecificOrigin.y, particleSpecificOrigin.z, direction.x, direction.y, direction.z));
            }
        }
        return instructions;
    }

    private static Vec3d origin(Entity entity, ParticleBatch.Origin origin) {
        switch (origin) {
            case FEET:
                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.1F), 0.0);
            case CENTER:
                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.5F), 0.0);
            case LAUNCH_POINT:
                if (entity instanceof LivingEntity livingEntity) {
                    return launchPoint(livingEntity);
                }

                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.5F), 0.0);
            default:
                return entity.getPos();
        }
    }

    private static Vec3d offset(float width, float extent, ParticleBatch.Shape shape, Vec3d direction, ParticleBatch.Rotation rotation, float yaw, float pitch) {
        Vec3d offset = Vec3d.ZERO;
        if (extent >= 1000.0F) {
            width = 0.0F;
            extent -= 1000.0F;
        }

        float size;
        float x;
        switch (shape) {
            case CIRCLE:
            case CONE:
            case SPHERE:
                if (extent > 0.0F) {
                    offset = direction.multiply((double)extent);
                }

                return offset;
            case PIPE:
                size = width + extent;
                x = (float)Math.toRadians((double)(rng.nextFloat() * 360.0F));
                offset = (new Vec3d((double)size, 0.0, 0.0)).rotateY(x);
                break;
            case PILLAR:
                size = width * 0.5F + extent;
                x = randomInRange(0.0F, size);
                float angle = (float)Math.toRadians((double)(rng.nextFloat() * 360.0F));
                offset = (new Vec3d((double)x, 0.0, 0.0)).rotateY(angle);
        }

        if (rotation != null) {
            switch (rotation) {
                case LOOK:
                    offset = offset.rotateX((float)Math.toRadians((double)(-1.0F * (pitch + 90.0F)))).rotateY((float)Math.toRadians((double)(-yaw)));
            }
        }

        return offset;
    }

    private static Vec3d direction(ParticleBatch batch, long time, float yaw, float pitch) {
        Vec3d direction = Vec3d.ZERO;
        float rotateAroundX = 0.0F;
        float rotateAroundY = 0.0F;
        switch (batch.shape) {
            case CIRCLE:
                direction = (new Vec3d(0.0, 0.0, (double)randomInRange(batch.min_speed, batch.max_speed))).rotateY((float)Math.toRadians((double)(rng.nextFloat() * 360.0F)));
                break;
            case CONE:
                direction = new Vec3d(0.0, (double)randomInRange(batch.min_speed, batch.max_speed), 0.0);
                rotateAroundX += rng.nextFloat() * batch.angle - batch.angle * 0.5F;
                rotateAroundY += rng.nextFloat() * batch.angle - batch.angle * 0.5F;
                break;
            case SPHERE:
                direction = (new Vec3d((double)randomInRange(batch.min_speed, batch.max_speed), 0.0, 0.0)).rotateZ((float)Math.toRadians((double)(rng.nextFloat() * 360.0F))).rotateY((float)Math.toRadians((double)(rng.nextFloat() * 360.0F)));
                break;
            case PIPE:
            case PILLAR:
                direction = new Vec3d(0.0, (double)randomInRange(batch.min_speed, batch.max_speed), 0.0);
                break;
            case LINE:
                direction = new Vec3d(0.0, 0.0, (double)randomInRange(batch.min_speed, batch.max_speed));
                pitch = -pitch;
        }

        float pRot;
        if (batch.rotation != null) {
            switch (batch.rotation) {
                case LOOK:
                    pRot = -pitch;
                    float yRot = yaw * -1.0F;
                    direction = direction.rotateX((float)Math.toRadians((double)(pRot - 90.0F + rotateAroundX))).rotateY((float)Math.toRadians((double)(yRot + rotateAroundY)));
                    if (batch.roll > 0.0F) {
                        Vec3d axis = axisFromRotation(yRot, pRot).negate();
                        float diff = (float)time * batch.roll % 360.0F + batch.roll_offset;
                        direction = rotateAround(direction, axis, (double)diff);
                    }
            }
        } else {
            direction = direction.rotateX((float)Math.toRadians((double)rotateAroundX)).rotateY((float)Math.toRadians((double)rotateAroundY));
            if (batch.roll > 0.0F) {
                pRot = (float)time * batch.roll % 360.0F + batch.roll_offset;
                direction = direction.rotateY((float)Math.toRadians((double)pRot));
            }
        }

        return direction;
    }

    private static float randomInRange(float min, float max) {
        float range = max - min;
        return min + range * rng.nextFloat();
    }

    private static float randomSignedInRange(float min, float max) {
        float rand = rng.nextFloat();
        float range = max - min;
        float sign = rand > 0.5F ? 1.0F : -1.0F;
        float base = sign * min;
        float varied = sign * range * rand;
        return base + varied;
    }

    public static record SpawnInstruction(ParticleEffect particle, double positionX, double positionY, double positionZ, double velocityX, double velocityY, double velocityZ) {
        public SpawnInstruction(ParticleEffect particle, double positionX, double positionY, double positionZ, double velocityX, double velocityY, double velocityZ) {
            this.particle = particle;
            this.positionX = positionX;
            this.positionY = positionY;
            this.positionZ = positionZ;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.velocityZ = velocityZ;
        }

        public void perform(World world) {
            try {
                world.addParticle(this.particle, true, this.positionX, this.positionY, this.positionZ, this.velocityX, this.velocityY, this.velocityZ);
            } catch (Exception var3) {
                System.err.println("Failed to perform particle SpawnInstruction");
            }

        }

        public ParticleEffect particle() {
            return this.particle;
        }

        public double positionX() {
            return this.positionX;
        }

        public double positionY() {
            return this.positionY;
        }

        public double positionZ() {
            return this.positionZ;
        }

        public double velocityX() {
            return this.velocityX;
        }

        public double velocityY() {
            return this.velocityY;
        }

        public double velocityZ() {
            return this.velocityZ;
        }
    }
}
