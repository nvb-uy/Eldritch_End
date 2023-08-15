package elocindev.eldritch_end.entity.primordial_boat;

import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;

public class PrimordialBoatEntity extends BoatEntity {
    public PrimordialBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
        super(EntityRegistry.PRIMORDIAL_BOAT, world);
    }

    public PrimordialBoatEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
}
