package elocindev.eldritch_end.api.particles.packets;

import com.google.gson.Gson;
import elocindev.eldritch_end.api.particles.ParticleBatch;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticlePackets {
    public ParticlePackets() {
    }

    public static record ParticleBatches(SourceType sourceType, List<Spawn> spawns) {
        public static Identifier ID = new Identifier("eldritch_end", "particle_effects");

        public ParticleBatches(SourceType sourceType, List<Spawn> spawns) {
            this.sourceType = sourceType;
            this.spawns = spawns;
        }

        public PacketByteBuf write(float countMultiplier) {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(this.sourceType.ordinal());
            buffer.writeInt(this.spawns.size());
            Iterator var3 = this.spawns.iterator();

            while(var3.hasNext()) {
                Spawn spawn = (Spawn)var3.next();
                buffer.writeInt(spawn.sourceEntityId);
                buffer.writeFloat(spawn.yaw);
                buffer.writeFloat(spawn.pitch);
                buffer.writeDouble(spawn.sourceLocation.x);
                buffer.writeDouble(spawn.sourceLocation.y);
                buffer.writeDouble(spawn.sourceLocation.z);
                write(spawn.batch, buffer, countMultiplier);
            }

            return buffer;
        }

        private static void write(ParticleBatch batch, PacketByteBuf buffer, float countMultiplier) {
            buffer.writeString(batch.particle_id);
            buffer.writeInt(batch.shape.ordinal());
            buffer.writeInt(batch.origin.ordinal());
            buffer.writeInt(batch.rotation != null ? batch.rotation.ordinal() : -1);
            buffer.writeFloat(batch.roll);
            buffer.writeFloat(batch.roll_offset);
            buffer.writeFloat(batch.count * countMultiplier);
            buffer.writeFloat(batch.min_speed);
            buffer.writeFloat(batch.max_speed);
            buffer.writeFloat(batch.angle);
            buffer.writeFloat(batch.extent);
            buffer.writeFloat(batch.pre_spawn_travel);
            buffer.writeBoolean(batch.invert);
        }

        private static ParticleBatch readBatch(PacketByteBuf buffer) {
            return new ParticleBatch(buffer.readString(), ParticleBatch.Shape.values()[buffer.readInt()], ParticleBatch.Origin.values()[buffer.readInt()], ParticleBatch.Rotation.from(buffer.readInt()), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readBoolean());
        }

        public static ParticleBatches read(PacketByteBuf buffer) {
            SourceType sourceType = ParticleBatches.SourceType.values()[buffer.readInt()];
            int spawnCount = buffer.readInt();
            ArrayList<Spawn> spawns = new ArrayList();

            for(int i = 0; i < spawnCount; ++i) {
                spawns.add(new Spawn(buffer.readInt(), buffer.readFloat(), buffer.readFloat(), new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble()), readBatch(buffer)));
            }

            return new ParticleBatches(sourceType, spawns);
        }

        public SourceType sourceType() {
            return this.sourceType;
        }

        public List<Spawn> spawns() {
            return this.spawns;
        }

        public static enum SourceType {
            ENTITY,
            COORDINATE;

            private SourceType() {
            }
        }

        public static record Spawn(int sourceEntityId, float yaw, float pitch, Vec3d sourceLocation, ParticleBatch batch) {
            public Spawn(int sourceEntityId, float yaw, float pitch, Vec3d sourceLocation, ParticleBatch batch) {
                this.sourceEntityId = sourceEntityId;
                this.yaw = yaw;
                this.pitch = pitch;
                this.sourceLocation = sourceLocation;
                this.batch = batch;
            }

            public int sourceEntityId() {
                return this.sourceEntityId;
            }

            public float yaw() {
                return this.yaw;
            }

            public float pitch() {
                return this.pitch;
            }

            public Vec3d sourceLocation() {
                return this.sourceLocation;
            }

            public ParticleBatch batch() {
                return this.batch;
            }
        }
    }


}
