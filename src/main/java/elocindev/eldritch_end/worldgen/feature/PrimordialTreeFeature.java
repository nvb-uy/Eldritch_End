package elocindev.eldritch_end.worldgen.feature;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.worldgen.util.TreeFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
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


        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(origin.getX(), 0, origin.getZ()));
        
        if (canPlace(world, topPos.down())) {
            TreeFactory.placeMediumDeadTree1(world, topPos, primordial_log); // REPLACE WITH MEDIUM TREE LATER

            int ranX = world.getRandom().nextBetween(-9, 9); int ranZ = world.getRandom().nextBetween(-9, 9);
            BlockPos smallTreePos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(origin.getX() + ranX, 0, origin.getZ() + ranZ));

            if (canPlace(world, smallTreePos.down()))
                TreeFactory.placeSmallDeadTree(world, smallTreePos, primordial_log);
            
            generated = true;
        }

        return generated;
    }
    
}