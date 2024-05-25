package elocindev.eldritch_end;

import elocindev.eldritch_end.utils.ParticleUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;

public class ParticleS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {

        if (client.world == null) return;
        Entity targetEntity = client.world.getEntityById(buf.readInt());
        String particleEffect = buf.readString();
        if (particleEffect.equals("teleportationRing")) {
            ParticleUtils.teleportationRing(targetEntity);
        } else {
            ParticleUtils.distanceWarningParticles(targetEntity);
        }
    }
}
