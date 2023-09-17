package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import elocindev.eldritch_end.entity.hastur.HasturEntity;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    static {
        BoatEntity.Type.values();
    }

    public static final EntityType<AberrationEntity> ABERRATION = Registry.register(
        Registry.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "aberration"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AberrationEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<TentacleEntity> TENTACLE = Registry.register(
        Registry.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "tentacle"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TentacleEntity::new).dimensions(EntityDimensions.fixed(1, 3)).build());

    public static final EntityType<HasturEntity> HASTUR = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "hastur"),
                FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HasturEntity::new).dimensions(EntityDimensions.fixed(2, 3)).build());


    public static BoatEntity.Type PRIMORDIAL;

    public static void register() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.ABERRATION, AberrationEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.TENTACLE, TentacleEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.HASTUR, HasturEntity.setAttributes());
    }
}
