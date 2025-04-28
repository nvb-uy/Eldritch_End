package elocindev.eldritch_end.compat;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.client.entity.eye_remastered.EyeModel;
import elocindev.eldritch_end.client.entity.eye_remastered.EyeRenderer;
import elocindev.eldritch_end.entity.crystal_entity.CrystalEntity;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_power.api.SpellSchools;

import static net.minecraft.registry.Registries.ENTITY_TYPE;

public class SpellEngineCompat {
    public static EntityType<EyeEntity> EYE;
    public static EntityType<CrystalEntity> CRYSTAL;

    public static void register(){
        EYE  = Registry.register(
                ENTITY_TYPE,
                Identifier.of(EldritchEnd.MODID, "eye"),
                FabricEntityTypeBuilder.<EyeEntity>create(SpawnGroup.MONSTER, EyeEntity::new)
                        .dimensions(EntityDimensions.fixed(4F, 4F)) // dimensions in Minecraft units of the render
                        .trackRangeBlocks(128)
                        .trackedUpdateRate(1)
                        .build()
        );
        CRYSTAL  = Registry.register(
                ENTITY_TYPE,
                Identifier.of(EldritchEnd.MODID, "crystal"),
                FabricEntityTypeBuilder.<CrystalEntity>create(SpawnGroup.MONSTER, CrystalEntity::new)
                        .dimensions(EntityDimensions.fixed(2F, 3F)) // dimensions in Minecraft units of the render
                        .trackRangeBlocks(128)
                        .trackedUpdateRate(1)
                        .build()
        );
        FabricDefaultAttributeRegistry.register(EYE,EyeEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,600).add(SpellSchools.ARCANE.attribute,8).add(EntityAttributes.GENERIC_FOLLOW_RANGE,128).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,10));
        FabricDefaultAttributeRegistry.register(CRYSTAL,EyeEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,100).add(SpellSchools.ARCANE.attribute,8).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,10));

    }

}
