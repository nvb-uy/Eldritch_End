package elocindev.eldritch_end.api;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.config.entries.mechanics.CorruptionConfig;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class CorruptionAPI {
    public static CorruptionConfig CONFIG = Configs.Mechanics.CORRUPTION;

    /**
     * Gets the total corruption level of an entity, taking into account their corruption resistance.
     * 
     * @param   entity      The entity to get the total corruption level of.
     * @return              The total corruption level of the entity.
     */
    public static double getTotalCorruptionLevel(LivingEntity entity) {
        return getCorruptionLevel(entity) - getCorruptionResistanceLevel(entity);
    }

    /**
     * Gets the raw corruption resistance level (attribute) of an entity.
     * 
     * @param entity        The entity to get the corruption resistance level of.
     * @return              The corruption resistance level of the entity.
     */
    public static double getCorruptionResistanceLevel(LivingEntity entity) {
        return entity.getAttributeInstance(AttributeRegistry.CORRUPTION_RESISTANCE).getValue();
    }

    /**
     * Gets the raw corruption level (attribute) of an entity.
     * 
     * @param entity        The entity to get the corruption level of.
     * @return              The corruption level of the entity.
     */
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
