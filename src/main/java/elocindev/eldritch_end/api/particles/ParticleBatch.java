package elocindev.eldritch_end.api.particles;

import org.jetbrains.annotations.Nullable;

public class ParticleBatch {
    public String particle_id;
    public Origin origin;
    public Rotation rotation;
    public float roll;
    public float roll_offset;
    public Shape shape;
    public float count;
    public float min_speed;
    public float max_speed;
    public float angle;
    public static final float EXTENT_TRESHOLD = 1000.0F;
    public float extent;
    public float pre_spawn_travel;
    public boolean invert;

    public ParticleBatch() {
        this.origin = ParticleBatch.Origin.CENTER;
        this.rotation = null;
        this.roll = 0.0F;
        this.roll_offset = 0.0F;
        this.count = 1.0F;
        this.min_speed = 0.0F;
        this.max_speed = 1.0F;
        this.angle = 0.0F;
        this.extent = 0.0F;
        this.pre_spawn_travel = 0.0F;
        this.invert = false;
    }

    public ParticleBatch(String particle_id, Shape shape, Origin origin, Rotation rotation, float roll, float roll_offset, float count, float min_speed, float max_speed, float angle, float extent, float pre_spawn_travel, boolean invert) {
        this.origin = ParticleBatch.Origin.CENTER;
        this.rotation = null;
        this.roll = 0.0F;
        this.roll_offset = 0.0F;
        this.count = 1.0F;
        this.min_speed = 0.0F;
        this.max_speed = 1.0F;
        this.angle = 0.0F;
        this.extent = 0.0F;
        this.pre_spawn_travel = 0.0F;
        this.invert = false;
        this.particle_id = particle_id;
        this.shape = shape;
        this.origin = origin;
        this.rotation = rotation;
        this.roll = roll;
        this.roll_offset = roll_offset;
        this.count = count;
        this.min_speed = min_speed;
        this.max_speed = max_speed;
        this.angle = angle;
        this.extent = extent;
        this.pre_spawn_travel = pre_spawn_travel;
        this.invert = invert;
    }

    /** @deprecated */
    @Deprecated
    public ParticleBatch(String particle_id, Shape shape, Origin origin, Rotation rotation, float count, float min_speed, float max_speed, float angle) {
        this(particle_id, shape, origin, rotation, count, min_speed, max_speed, angle, 0.0F);
    }

    /** @deprecated */
    @Deprecated
    public ParticleBatch(String particle_id, Shape shape, Origin origin, Rotation rotation, float count, float min_speed, float max_speed, float angle, float extent) {
        this(particle_id, shape, origin, rotation, 0.0F, 0.0F, count, min_speed, max_speed, angle, extent, 0.0F, false);
    }

    public ParticleBatch(ParticleBatch other) {
        this(other.particle_id, other.shape, other.origin, other.rotation, other.roll, other.roll_offset, other.count, other.min_speed, other.max_speed, other.angle, other.extent, other.pre_spawn_travel, other.invert);
    }

    public static enum Origin {
        FEET,
        CENTER,
        LAUNCH_POINT;

        private Origin() {
        }
    }

    public static enum Rotation {
        LOOK;

        private Rotation() {
        }

        @Nullable
        public static Rotation from(int ordinal) {
            return ordinal >= 0 && ordinal < values().length ? values()[ordinal] : null;
        }
    }

    public static enum Shape {
        CIRCLE,
        PILLAR,
        PIPE,
        SPHERE,
        CONE,
        LINE;

        private Shape() {
        }
    }
}
