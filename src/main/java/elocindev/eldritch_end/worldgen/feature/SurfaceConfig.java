package elocindev.eldritch_end.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

public record SurfaceConfig(Identifier blockID) implements FeatureConfig {
    public SurfaceConfig(Identifier blockID) {
        this.blockID = blockID;
    }
 
    public static Codec<SurfaceConfig> CODEC = RecordCodecBuilder.create(
        instance ->
                instance.group(
                        Identifier.CODEC.fieldOf("blockID").forGetter(SurfaceConfig::blockID))
                .apply(instance, SurfaceConfig::new));
 
    public Identifier blockID() {
        return blockID;
    }
}
