package elocindev.eldritch_end.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

public record TreeConfig(Identifier blockID) implements FeatureConfig {
    public TreeConfig(Identifier blockID) {
        this.blockID = blockID;
    }
 
    public static Codec<TreeConfig> CODEC = RecordCodecBuilder.create(
        instance ->
                instance.group(
                        Identifier.CODEC.fieldOf("blockID").forGetter(TreeConfig::blockID))
                .apply(instance, TreeConfig::new));
 
    public Identifier blockID() {
        return blockID;
    }
}
