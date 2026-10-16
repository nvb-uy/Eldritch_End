package elocindev.eldritch_end.entity;

import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import net.minecraft.entity.Entity;

/**
 * Helpers for driving AzureLib 3.x animations from entity code.
 *
 * Each entity animator registers a "base" controller for locomotion and idle;
 * one-shot actions (attacks, falls) use their own named controllers.
 */
public final class EntityAnimations {
    public static final String BASE_CONTROLLER = "base";

    private EntityAnimations() {
    }

    /**
     * Sends the command only when its key differs from the one already sent.
     * Returns the key now in effect, so callers can store it between ticks.
     */
    public static String sendIfChanged(Entity entity, String current, String key, AzCommand command) {
        if (key.equals(current)) {
            return current;
        }
        command.sendForEntity(entity);
        return key;
    }

    /**
     * True when the entity moved horizontally since the previous tick.
     * Replaces GeckoLib's event.isMoving().
     */
    public static boolean isMovingHorizontally(Entity entity) {
        double dx = entity.getX() - entity.prevX;
        double dz = entity.getZ() - entity.prevZ;
        return dx * dx + dz * dz > 1.0E-6;
    }
}
