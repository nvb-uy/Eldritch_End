package elocindev.eldritch_end.worldgen.feature.surface;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.block.HasturianMoss;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class HasturianWastesSurface extends Feature<SurfaceConfig> {
    public HasturianWastesSurface(Codec<SurfaceConfig> configCodec) {
      super(configCodec);
    }

    public boolean canPlace(StructureWorldAccess world, BlockPos position) {
        return world.getBlockState(position).getBlock() == Blocks.END_STONE || world.getBlockState(position).getBlock() == BlockRegistry.ABYSMAL_FRONDS;
    }
   
    @Override
    public boolean generate(FeatureContext<SurfaceConfig> context) {
        boolean generated = false;
        boolean isSand = false;

        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        SurfaceConfig config = context.getConfig();
        BlockState blockState = Registry.BLOCK.get(config.blockID()).getDefaultState();

        int centerX = origin.getX() + random.nextInt(4);
        int centerZ = origin.getZ() + random.nextInt(4);

        int radius = random.nextInt(10) + 6;
        int radiusX = radius; int radiusZ = radius;
        
        if (random.nextBoolean()) {
            isSand = true;
            blockState = BlockRegistry.HASTURIAN_DUNE_SAND.getDefaultState(); 
        }

        if (random.nextBoolean()) {
            radiusX += random.nextInt(4);
        } else {
            radiusZ += random.nextInt(4);
        }

        for (int x = centerX - radiusX; x <= centerX + radiusX; x++) {
            for (int z = centerZ - radiusZ; z <= centerZ + radiusZ; z++) {
                double distanceSq = (x - centerX) * (x - centerX) + (z - centerZ) * (z - centerZ);

                if (distanceSq <= radiusX * radiusX && distanceSq <= radiusZ * radiusZ) {
                    BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(x, 0, z));
                    BlockPos targetPos = topPos.down();

                    if (canPlace(world, targetPos)) {

                        if (!isSand) {
                            if (random.nextBoolean())
                                world.setBlockState(targetPos, blockState, 3);
                            else
                                world.setBlockState(targetPos, blockState.with(HasturianMoss.FACING, Direction.EAST), 3);
                        } else {
                            world.setBlockState(targetPos, blockState, 3);
                            for (int i = 0; i < 4; i++)
                                if (canPlace(world, targetPos.down(i))) world.setBlockState(targetPos.down(i), BlockRegistry.HASTURIAN_SAND.getDefaultState(), 3);
                        }
                        


                        if (world.getBlockState(targetPos.up()).getBlock() == BlockRegistry.ABYSMAL_ROOTS)
                            world.setBlockState(targetPos.up(), Blocks.AIR.getDefaultState(), 3);

                        generated = true;
                    }
                }
            }
        }

        return generated;
    }
}