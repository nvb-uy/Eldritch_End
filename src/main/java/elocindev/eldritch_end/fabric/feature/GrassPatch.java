package elocindev.eldritch_end.fabric.feature;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GrassPatch extends Feature<DefaultFeatureConfig> {
    public GrassPatch(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int count = 0;
    
        for (int i = 0; i < 64; i++) {
            mutable.set(context.getOrigin()).move(
                context.getRandom().nextInt(8) - context.getRandom().nextInt(8),
                context.getRandom().nextInt(4) - context.getRandom().nextInt(4),
                context.getRandom().nextInt(8) - context.getRandom().nextInt(8)
            );
    
            if (context.getWorld().getBlockState(mutable).isAir() && Blocks.GRASS.getDefaultState().canPlaceAt(context.getWorld(), mutable)) {
                context.getWorld().setBlockState(mutable, Blocks.GRASS.getDefaultState(), 2);
                count++;
            }
        }
    
        return count > 0;
    }    
}
