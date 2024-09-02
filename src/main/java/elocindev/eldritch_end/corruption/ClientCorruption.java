package elocindev.eldritch_end.corruption;

import elocindev.eldritch_end.api.CorruptionAPI;
import net.minecraft.client.MinecraftClient;

public class ClientCorruption {
    public static int getTotalCorruptionLevel() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return -1;

        return (int)CorruptionAPI.getAffectedCorruptionLevel(client.player);
    }

    public static int getCorruptionLevel() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return -1;

        return (int)CorruptionAPI.getCorruptionLevel(client.player);
    }

    public static int getCorruptionResistanceLevel() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return -1;

        return (int)CorruptionAPI.getCorruptionResistanceLevel(client.player);
    }
}
