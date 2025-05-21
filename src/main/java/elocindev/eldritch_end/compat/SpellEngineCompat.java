package elocindev.eldritch_end.compat;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.client.entity.eye_remastered.EyeModel;
import elocindev.eldritch_end.client.entity.eye_remastered.EyeRenderer;
import elocindev.eldritch_end.entity.arcane_missile.ArcaneMissile;
import elocindev.eldritch_end.entity.crystal_entity.CrystalEntity;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import elocindev.eldritch_end.item.spawneggs.AberrationEgg;
import elocindev.eldritch_end.item.spawneggs.EyeEgg;
import elocindev.eldritch_end.registry.EntityRegistry;
import elocindev.eldritch_end.registry.ItemGroupRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static elocindev.eldritch_end.registry.ItemRegistry.reg;
import static net.minecraft.registry.Registries.ENTITY_TYPE;

public class SpellEngineCompat {
    public static EntityType<EyeEntity> EYE;
    public static EntityType<CrystalEntity> CRYSTAL;
    public static EntityType<ArcaneMissile> ARCANEMISSILE;

    public static  Item EYE_SPAWN_EGG ;

    public static RegistryKey<ItemGroup> KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),Identifier.of("eldritch_end","tab"));

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
        ARCANEMISSILE  = Registry.register(
                ENTITY_TYPE,
                Identifier.of(EldritchEnd.MODID, "arcane_missile"),
                FabricEntityTypeBuilder.<ArcaneMissile>create(SpawnGroup.MISC, ArcaneMissile::new)
                        .dimensions(EntityDimensions.fixed(0.5F, 0.5F)) // dimensions in Minecraft units of the render
                        .trackRangeBlocks(128)
                        .trackedUpdateRate(1)
                        .build()
        );
        EYE_SPAWN_EGG = reg(new EyeEgg(EYE, 0x54496f, 0xC76800, new FabricItemSettings()), "eye_spawn_egg");
        ItemGroupEvents.modifyEntriesEvent(KEY).register((content) -> {
            content.add(EYE_SPAWN_EGG);
        });
        FabricDefaultAttributeRegistry.register(EYE,EyeEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(CRYSTAL,EyeEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,100).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,8).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,10));

    }


}
