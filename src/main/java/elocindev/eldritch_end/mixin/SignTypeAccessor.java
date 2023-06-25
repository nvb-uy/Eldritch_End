package elocindev.eldritch_end.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.SignType;

@Mixin(SignType.class)
public interface SignTypeAccessor {
    @Invoker("<init>")
    static SignType newSignType(String name) {
        throw new AssertionError();
    }

    @Invoker("register")
    static SignType registerNew(SignType type) {
        throw new AssertionError();
    }
}
