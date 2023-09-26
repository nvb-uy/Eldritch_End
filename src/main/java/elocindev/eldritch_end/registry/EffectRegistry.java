package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.effects.Corruption;
import elocindev.eldritch_end.effects.HasturPresence;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectRegistry {
    public static final StatusEffect CORRUPTION = new Corruption();

    public static final StatusEffect HASTUR_PRESENCE = new HasturPresence();

    public static void register() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EldritchEnd.MODID, "corruption"), CORRUPTION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EldritchEnd.MODID, "hastur_presence"), HASTUR_PRESENCE);
    }
}