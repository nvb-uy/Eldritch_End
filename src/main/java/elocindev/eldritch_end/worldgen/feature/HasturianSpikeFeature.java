package elocindev.eldritch_end.worldgen.feature;

import com.mojang.serialization.Codec;
import elocindev.eldritch_end.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class HasturianSpikeFeature extends Feature<DefaultFeatureConfig> {
    public HasturianSpikeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    public static boolean canBePlaced(StructureWorldAccess world, BlockPos position) {
        return world.getBlockState(position).getBlock() == BlockRegistry.HASTURIAN_MOSS;
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();

        BlockState block = BlockRegistry.SPIRE_STONE.getDefaultState();

        StructureWorldAccess world = context.getWorld();
        
        blockPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, blockPos);

        if (!canBePlaced(world, blockPos.down()))
            return false;

        if (world.getBlockState(blockPos.down()).getBlock() == Blocks.AIR) return false;
        blockPos = blockPos.down(2);

        blockPos = blockPos.up(random.nextInt(4));
        int i = random.nextInt(4) + 7;
        int j = i / 4 + random.nextInt(2);
        if (j > 1 && random.nextInt(60) == 0) {
            blockPos = blockPos.up(10 + random.nextInt(30));
        }

        int k;
        int l;
        for(k = 0; k < i; ++k) {
            float f = (1.0F - (float)k / (float)i) * (float)j;
            l = MathHelper.ceil(f);

            for(int m = -l; m <= l; ++m) {
                float g = (float)MathHelper.abs(m) - 0.25F;

                for(int n = -l; n <= l; ++n) {
                    float h = (float)MathHelper.abs(n) - 0.25F;
                    if ((m == 0 && n == 0 || !(g * g + h * h > f * f)) && (m != -l && m != l && n != -l && n != l || !(random.nextFloat() > 0.75F))) {
                        BlockState blockState = world.getBlockState(blockPos.add(m, k, n));
                        if (blockState.isAir() || isSoil(blockState) || blockState.isOf(BlockRegistry.HASTURIAN_MOSS)) {
                            this.setBlockState(world, blockPos.add(m, k, n), block);
                        }

                        if (k != 0 && l > 1) {
                            blockState = world.getBlockState(blockPos.add(m, -k, n));
                            if (blockState.isAir()) {
                                this.setBlockState(world, blockPos.add(m, -k, n), block);
                            }
                        }
                    }
                }
            }
        }

        k = j - 1;
        if (k < 0) {
            k = 0;
        } else if (k > 1) {
            k = 1;
        }

        for(int o = -k; o <= k; ++o) {
            for(l = -k; l <= k; ++l) {
                BlockPos blockPos2 = blockPos.add(o, -1, l);
                int p = 50;
                if (Math.abs(o) == 1 && Math.abs(l) == 1) {
                    p = random.nextInt(5);
                }

                while(blockPos2.getY() > 50) {
                    BlockState blockState2 = world.getBlockState(blockPos2);
                    if (!blockState2.isAir() && !isSoil(blockState2) && !blockState2.isOf(BlockRegistry.HASTURIAN_MOSS) && !blockState2.isOf(BlockRegistry.SPIRE_STONE)) {
                        break;
                    }

                    this.setBlockState(world, blockPos2, block);
                    blockPos2 = blockPos2.down();
                    --p;
                    if (p <= 0) {
                        blockPos2 = blockPos2.down(random.nextInt(5) + 1);
                        p = random.nextInt(5);
                    }
                }
            }
        }

        return true;
        
    }

}
