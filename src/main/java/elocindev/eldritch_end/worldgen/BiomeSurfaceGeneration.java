package elocindev.eldritch_end.worldgen;


import com.mojang.serialization.Codec;

import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
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
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
    
        SurfaceConfig config = context.getConfig();
        Identifier blockID = config.blockID();
        BlockState blockState = Registry.BLOCK.get(blockID).getDefaultState();
    
        if (blockState == null) throw new IllegalStateException(blockID + " could not be parsed to a valid block identifier!");
    
        boolean replaced = false;
    
        int centerX = world.getRandom().nextInt(16); int centerZ = world.getRandom().nextInt(16);
        int radius = world.getRandom().nextInt(10) + 4;
        BlockPos center = new BlockPos(origin.getX() + centerX, 0, origin.getZ() + centerZ);
    
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                BlockPos testPos = new BlockPos(origin.getX() + x, 0, origin.getZ() + z);
    
                double distance = center.getSquaredDistance(testPos.getX(), center.getY(), testPos.getZ());
                if (distance > radius * radius) continue;
    
                for (int y = 0; y < world.getHeight(); y++) {
                    testPos = testPos.up();
                    if (world.getBlockState(testPos.up()).getBlock() != Blocks.AIR) continue;
    
                    if (canPlace(world, testPos)) {
                        world.setBlockState(testPos, blockState, 0x10);
                        replaced = true;
                    }
                }
            }
        }
    
        return replaced;
    }
    
  }