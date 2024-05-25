package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.ParticleS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class PacketRegistry {
    public static final Identifier PARTICLE_PACKET = new Identifier(EldritchEnd.MODID, "particle_packet");

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(PARTICLE_PACKET, ParticleS2CPacket::receive);
    }
}
