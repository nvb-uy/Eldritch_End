package elocindev.eldritch_end.entity;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<AbberationEntity> ABBERATION = Registry.register(
        Registry.ENTITY_TYPE, new Identifier(EldritchEnd.MODID, "abberation"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AbberationEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build());

    // Initialize attributes here
    public static void register() {
        FabricDefaultAttributeRegistry.register(ModEntities.ABBERATION, AbberationEntity.setAttributes());
    }
}
