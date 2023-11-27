package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import elocindev.eldritch_end.entity.dendler.DendlerEntity;
import elocindev.eldritch_end.entity.faceless.FacelessEntity;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import elocindev.eldritch_end.entity.tentacle.UndeadTentacleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityRegistry {
    static {
        BoatEntity.Type.values();
    }

    public static final EntityType<AberrationEntity> ABERRATION = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "aberration"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AberrationEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<TentacleEntity> TENTACLE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "tentacle"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TentacleEntity::new).dimensions(EntityDimensions.fixed(1, 3)).build());

    public static final EntityType<UndeadTentacleEntity> UNDEAD_TENTACLE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "undead_tentacle"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, UndeadTentacleEntity::new).dimensions(EntityDimensions.fixed(1, 3)).build());

    public static final EntityType<DendlerEntity> DENDLER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "dendler"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DendlerEntity::new).dimensions(EntityDimensions.fixed(1, 2)).build());


    public static final EntityType<FacelessEntity> THE_FACELESS = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "the_faceless"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FacelessEntity::new).dimensions(EntityDimensions.fixed(2, 3)).build());


//     public static final EntityType<HasturEntity> HASTUR = Registry.register(
//             Registries.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "hastur"),
//             FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HasturEntity::new).dimensions(EntityDimensions.fixed(2, 3)).build());


    public static BoatEntity.Type PRIMORDIAL;

    public static void register() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.ABERRATION, AberrationEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.TENTACLE, TentacleEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.UNDEAD_TENTACLE, UndeadTentacleEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DENDLER, AberrationEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.THE_FACELESS, FacelessEntity.setAttributes());
        //FabricDefaultAttributeRegistry.register(EntityRegistry.HASTUR, HasturEntity.setAttributes());
    }
}
