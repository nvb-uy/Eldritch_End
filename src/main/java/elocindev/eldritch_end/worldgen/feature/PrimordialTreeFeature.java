package elocindev.eldritch_end.worldgen.feature;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.worldgen.util.TreeFactory;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PrimordialTreeFeature extends Feature<TreeConfig> {
    public PrimordialTreeFeature(Codec<TreeConfig> configCodec) {
      super(configCodec);
    }

    public static boolean canBePlaced(StructureWorldAccess world, BlockPos position) {
        // todo: fix
        // return world.getBlockState(position).getBlock() == BlockRegistry.ABYSMAL_FRONDS;
        return true;
    }
   
    @Override
    public boolean generate(FeatureContext<TreeConfig> context) {
        boolean generated = false;
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        TreeConfig config = context.getConfig();
        BlockState primordial_log = Registries.BLOCK.get(config.blockID()).getDefaultState();


        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(origin.getX(), origin.getY(), origin.getZ()));
        
        if (canBePlaced(world, topPos.down())) {
            if (world.getBlockState(topPos.down()).isOf(BlockRegistry.ABYSMAL_FRONDS))
                TreeFactory.addRandomMedium(world, topPos, primordial_log);

            int ranX = world.getRandom().nextBetween(-9, 9); int ranZ = world.getRandom().nextBetween(-9, 9);
            BlockPos smallTreePos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(origin.getX() + ranX, origin.getY(), origin.getZ() + ranZ));

            if (canBePlaced(world, smallTreePos.down()))
                TreeFactory.placeSmallDeadTree(world, smallTreePos, primordial_log);
            
            generated = true;
        }

        return generated;
    }
    
}