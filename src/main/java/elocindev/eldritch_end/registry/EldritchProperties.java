package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.mixin.SignTypeAccessor;
import net.minecraft.util.SignType;

public class EldritchProperties {
    public static SignType PRIMORDIAL_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("primordial"));
}
