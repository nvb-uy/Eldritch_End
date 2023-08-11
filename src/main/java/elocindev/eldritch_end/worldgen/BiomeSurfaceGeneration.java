package elocindev.eldritch_end.worldgen;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BiomeSurfaceGeneration extends Feature<SurfaceConfig> {
    public BiomeSurfaceGeneration(Codec<SurfaceConfig> configCodec) {
      super(configCodec);
    }

    public boolean canPlace(StructureWorldAccess world, BlockPos position) {
        return world.getBlockState(position).getBlock() == Blocks.END_STONE;
    }
   
    @Override
    public boolean generate(FeatureContext<SurfaceConfig> context) {
        boolean generated = false;
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        SurfaceConfig config = context.getConfig();
        BlockState blockState = Registry.BLOCK.get(config.blockID()).getDefaultState();

        int centerX = origin.getX() + world.getRandom().nextInt(6);
        int centerZ = origin.getZ() + world.getRandom().nextInt(6);
        int radius = world.getRandom().nextInt(6) + 6;

        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                double distanceSq = (x - centerX) * (x - centerX) + (z - centerZ) * (z - centerZ);

                if (distanceSq <= radius * radius) {
                    BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, new BlockPos(x, 0, z));
                    BlockPos targetPos = topPos.down();

                    if (canPlace(world, targetPos)) {
                        world.setBlockState(targetPos, blockState, 3);
                        generated = true;
                    }
                }
            }
        }

        return generated;
    }

}