package elocindev.eldritch_end.worldgen;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.worldgen.feature.TreeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PrimordialTreeFeature extends Feature<TreeConfig> {
    public PrimordialTreeFeature(Codec<TreeConfig> configCodec) {
      super(configCodec);
    }

    public boolean canPlace(StructureWorldAccess world, BlockPos position) {
        return world.getBlockState(position).getBlock() == BlockRegistry.ABYSMAL_FRONDS;
    }
   
    @Override
    public boolean generate(FeatureContext<TreeConfig> context) {
        boolean generated = false;
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        TreeConfig config = context.getConfig();
        BlockState primordial_log = Registry.BLOCK.get(config.blockID()).getDefaultState();

        int treeHeight = 5 + world.getRandom().nextInt(5);

        for (int y = 1; y <= treeHeight; y++) {
            BlockPos currentPos = origin.up(y);

            if (canPlace(world, currentPos)) {
                world.setBlockState(currentPos, primordial_log, 3);
                generated = true;

                if (y > 2 && world.getRandom().nextFloat() < 0.3f) {
                    int numBranches = world.getRandom().nextInt(2) + 1;
                    for (int i = 0; i < numBranches; i++) {
                        int xOffset = world.getRandom().nextInt(3) - 1;
                        int zOffset = world.getRandom().nextInt(3) - 1;

                        BlockPos branchPos = currentPos.add(xOffset, 0, zOffset);
                        
                        if (canPlace(world, branchPos)) {
                            world.setBlockState(branchPos, primordial_log, 3);
                        }
                    }
                }
            }
        }

        return generated;
    }
    
}