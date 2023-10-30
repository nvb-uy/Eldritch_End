package elocindev.eldritch_end.worldgen.feature.surface;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.worldgen.feature.PrimordialTreeFeature;
import elocindev.eldritch_end.worldgen.feature.SurfaceConfig;
import elocindev.eldritch_end.worldgen.util.TendrilFactory;
import elocindev.eldritch_end.worldgen.util.TreeFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PrimordialAbyssSurface extends Feature<SurfaceConfig> {
    public PrimordialAbyssSurface(Codec<SurfaceConfig> configCodec) {
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
        BlockState blockState = Registries.BLOCK.get(config.blockID()).getDefaultState();

        int centerX = origin.getX() + world.getRandom().nextInt(4);
        int centerZ = origin.getZ() + world.getRandom().nextInt(4);
        
        // Tendril Generation
        if (Configs.Biome.PRIMORDIAL_ABYSS.enable_tendril_patches && world.getRandom().nextInt(100) <= Configs.Biome.PRIMORDIAL_ABYSS.tendril_patch_chance) {
            BlockPos targetPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(centerX, 0, centerZ)).down();

            TendrilFactory.genRandomShape(world, targetPos, BlockRegistry.ABYSMAL_TENDRILS.getDefaultState(), BlockRegistry.SUSPICIOUS_FRONDS.getDefaultState());
        }

        int radius = world.getRandom().nextInt(11) + 6;
        int radiusX = radius; int radiusZ = radius;
        
        if (world.getRandom().nextBoolean()) {
            radiusX += world.getRandom().nextInt(4);
        } else {
            radiusZ += world.getRandom().nextInt(4);
        }

        for (int x = centerX - radiusX; x <= centerX + radiusX; x++) {
            for (int z = centerZ - radiusZ; z <= centerZ + radiusZ; z++) {
                double distanceSq = (x - centerX) * (x - centerX) + (z - centerZ) * (z - centerZ);

                if (distanceSq <= radiusX * radiusX && distanceSq <= radiusZ * radiusZ) {
                    BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(x, 0, z));
                    BlockPos targetPos = topPos.down();

                    if (world.getBiome(targetPos) == BiomeRegistry.HASTURIAN_WASTES) continue;

                    if (canPlace(world, targetPos)) {
                        
                        world.setBlockState(targetPos, blockState, 3);
                    
                        if (Configs.Biome.PRIMORDIAL_ABYSS.enable_roots_generation && world.getRandom().nextInt(100) <= Configs.Biome.PRIMORDIAL_ABYSS.roots_generation_chance)
                            world.setBlockState(targetPos.up(), BlockRegistry.ABYSMAL_ROOTS.getDefaultState(), 3);

                        generated = true;
                    }
                }
            }
        }

        int zOffset = world.getRandom().nextBetween(-4, 4); int xOffset = world.getRandom().nextBetween(-4, 4);

        BlockPos treeTarget = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(centerX, 0, centerZ)).down();
        
        if (PrimordialTreeFeature.canBePlaced(world, treeTarget)) TreeFactory.addRandomMedium(world, treeTarget.add(xOffset, 0, zOffset), BlockRegistry.PRIMORDIAL_LOG.getDefaultState());
    

        return generated;
    }
}