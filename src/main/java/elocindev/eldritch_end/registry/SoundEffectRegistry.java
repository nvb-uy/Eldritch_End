package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundEffectRegistry {
    public static final Identifier GROWL_SOUND_ID = new Identifier(EldritchEnd.MODID, "faceless_growls");
    public static SoundEvent GROWL_EVENT = SoundEvent.of(GROWL_SOUND_ID);

    public static final Identifier PUNCH_SOUND_ID = new Identifier(EldritchEnd.MODID, "faceless_punches");
    public static SoundEvent PUNCH_EVENT = SoundEvent.of(PUNCH_SOUND_ID);

    public static final Identifier ORB_SOUND_ID = new Identifier(EldritchEnd.MODID, "orb_smash");
    public static SoundEvent ORB_EVENT = SoundEvent.of(ORB_SOUND_ID);

    public static void register() {
        Registry.register(Registries.SOUND_EVENT, GROWL_SOUND_ID, GROWL_EVENT);
        Registry.register(Registries.SOUND_EVENT, PUNCH_SOUND_ID, PUNCH_EVENT);
        Registry.register(Registries.SOUND_EVENT, ORB_SOUND_ID, ORB_EVENT);
    }
}
