package elocindev.eldritch_end.api;

import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class CorruptionAPI {
    public static double getTotalCorruptionLevel(LivingEntity entity) {
        double corruption = getCorruptionLevel(entity);
        return corruption / getCorruptionResistanceMult(entity);
    }

    public static double getCorruptionResistanceLevel(LivingEntity entity) {
        return entity.getAttributeInstance(AttributeRegistry.CORRUPTION_RESISTANCE).getValue();
    }

    public static double getCorruptionResistanceMult(LivingEntity entity) {
        return 1 + (entity.getAttributeInstance(AttributeRegistry.CORRUPTION_RESISTANCE).getValue() / 100);
    }

    public static double getCorruptionLevel(LivingEntity entity) {
        return entity.getAttributeInstance(AttributeRegistry.CORRUPTION).getValue();
    }

    public static MutableText getCMenuTitle() {
        return TextAPI.Styles.getStaticGradient(Text.translatable("eldritch_end.corruption.gui.player"), 0x484682, 0x654682);
    }

    public class Icons {
        public static final MutableText RIGHT_CLICK_ABILITY = Text.literal("\uA996 ");
        public static final MutableText CORRUPTION_SCALING = Text.literal("\uA997 ");
        public static final MutableText CORRUPTION_PLUS = Text.literal("\uA998 ");
        public static final MutableText CORRUPTION = Text.literal("\uA999 ");
    }
}
