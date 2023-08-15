package elocindev.eldritch_end.entity.primordial_boat;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class PrimordialBoatEntity extends BoatEntity {
    private static final TrackedData<Integer> DAMAGE_WOBBLE_TICKS;
    private static final TrackedData<Integer> DAMAGE_WOBBLE_SIDE;
    private static final TrackedData<Float> DAMAGE_WOBBLE_STRENGTH;
    private static final TrackedData<Boolean> LEFT_PADDLE_MOVING;
    private static final TrackedData<Boolean> RIGHT_PADDLE_MOVING;
    private static final TrackedData<Integer> BUBBLE_WOBBLE_TICKS;
    private static final TrackedData<Integer> BOAT_TYPE;

    public PrimordialBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
        super(EntityRegistry.PRIMORDIAL_BOAT, world);
    }

    public PrimordialBoatEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public void setCustomBoatType(PrimordialBoatEntity.Type type) {
        this.dataTracker.set(BOAT_TYPE, type.ordinal());
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(DAMAGE_WOBBLE_TICKS, 0);
        this.dataTracker.startTracking(DAMAGE_WOBBLE_SIDE, 1);
        this.dataTracker.startTracking(DAMAGE_WOBBLE_STRENGTH, 0.0F);
        this.dataTracker.startTracking(BOAT_TYPE, PrimordialBoatEntity.Type.PRIMORDIAL.ordinal());
        this.dataTracker.startTracking(LEFT_PADDLE_MOVING, false);
        this.dataTracker.startTracking(RIGHT_PADDLE_MOVING, false);
        this.dataTracker.startTracking(BUBBLE_WOBBLE_TICKS, 0);
    }


    public PrimordialBoatEntity.Type getCustomType() {
        return PrimordialBoatEntity.Type.getType((Integer)this.dataTracker.get(BOAT_TYPE));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString("Type", this.getCustomType().getName());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Type", 8)) {
            this.setCustomBoatType(PrimordialBoatEntity.Type.getType(nbt.getString("Type")));
        }
    }

    public static enum Type {
        PRIMORDIAL(BlockRegistry.PRIMORDIAL_PLANKS, "primordial");

        private final String name;
        private final Block baseBlock;

        private Type(Block baseBlock, String name) {
            this.name = name;
            this.baseBlock = baseBlock;
        }

        public String getName() {
            return this.name;
        }

        public Block getBaseBlock() {
            return this.baseBlock;
        }

        public String toString() {
            return this.name;
        }

        public static Type getType(int type) {
            Type[] types = values();
            if (type < 0 || type >= types.length) {
                type = 0;
            }

            return types[type];
        }

        public static Type getType(String name) {
            Type[] types = values();

            for(int i = 0; i < types.length; ++i) {
                if (types[i].getName().equals(name)) {
                    return types[i];
                }
            }

            return types[0];
        }
    }

    static {
        DAMAGE_WOBBLE_TICKS = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
        DAMAGE_WOBBLE_SIDE = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
        DAMAGE_WOBBLE_STRENGTH = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.FLOAT);
        BOAT_TYPE = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
        LEFT_PADDLE_MOVING = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        RIGHT_PADDLE_MOVING = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        BUBBLE_WOBBLE_TICKS = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
