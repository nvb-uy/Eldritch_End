package elocindev.eldritch_end.compat;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.client.entity.crystal.CrystalModel;
import elocindev.eldritch_end.client.entity.crystal.CrystalRenderer;
import elocindev.eldritch_end.client.entity.eye_remastered.EyeModel;
import elocindev.eldritch_end.client.entity.eye_remastered.EyeRenderer;
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

import static elocindev.eldritch_end.compat.SpellEngineCompat.CRYSTAL;
import static elocindev.eldritch_end.compat.SpellEngineCompat.EYE;
import static net.minecraft.registry.Registries.ENTITY_TYPE;

public class SpellEngineCompatClient {

    public static void registerClient(){
        EntityRendererRegistry.register(EYE, (context) ->  new EyeRenderer<>(context, new EyeModel<>()));
        EntityRendererRegistry.register(CRYSTAL, (context) ->  new CrystalRenderer<>(context, new CrystalModel<>()));

    }
}
