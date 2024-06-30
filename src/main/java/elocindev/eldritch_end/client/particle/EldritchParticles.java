package elocindev.eldritch_end.client.particle;

import elocindev.eldritch_end.EldritchEnd;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;

public class EldritchParticles {

    public static Identifier get(String particleId) {
        return new Identifier(EldritchEnd.MODID, particleId);
    }

    public static void playEffek(String id, World world, Vec3d pos, boolean forced, float scale) {
        ParticleEmitterInfo instance = new ParticleEmitterInfo(get(id))
            .clone()
            .position(pos)
            .scale(scale);
        
        AAALevel.addParticle(world, forced, instance);
    }

    public static void playEffek(String id, World world, Vec3d pos) {
        playEffek(id, world, pos, true, 1.0F);
    }
}
