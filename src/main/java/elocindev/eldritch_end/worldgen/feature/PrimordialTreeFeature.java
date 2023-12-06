package elocindev.eldritch_end.worldgen.feature;

import java.util.Optional;

import com.mojang.serialization.Codec;

import elocindev.eldritch_end.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PrimordialTreeFeature extends Feature<TreeConfig> {
    public PrimordialTreeFeature(Codec<TreeConfig> configCodec) {
      super(configCodec);
    }

    public static boolean canBePlaced(StructureWorldAccess world, BlockPos position, String type) {
        if (type == "small") {
            if (world.getBlockState(position).getBlock() != BlockRegistry.ABYSMAL_FRONDS) {
                return false;
            }
        }
        
        else  {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (world.getBlockState(position.add(x, 0, z)).getBlock() != BlockRegistry.ABYSMAL_FRONDS) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    @Override
    public boolean generate(FeatureContext<TreeConfig> context) {
        boolean generated = false;
        String type;
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        int variation = context.getRandom().nextInt(2) + 1;
        if(context.getRandom().nextBoolean()) type = "big"; else type = "small";

        Identifier TREE_TYPE = new Identifier("eldritch_end", "primordial_tree_"+type+"/variation_"+variation);
    
        StructureTemplateManager structureManager = world.getServer().getStructureTemplateManager();
                    
        Optional<StructureTemplate> template = structureManager.getTemplate(TREE_TYPE);
        if(template.isEmpty())
            return false;
        
        BlockRotation rotation = BlockRotation.random(context.getRandom());

        BlockPos halfLengths = new BlockPos(
                template.get().getSize().getX() / 2,
                template.get().getSize().getY() / 2,
                template.get().getSize().getZ() / 2);

        BlockPos.Mutable mutable = new BlockPos.Mutable().set(origin);

        BlockPos position = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, new BlockPos(origin.getX(), origin.getY(), origin.getZ()));

        StructurePlacementData placementsettings = (new StructurePlacementData()).setRotation(rotation).setPosition(halfLengths).setIgnoreEntities(false);
    
        mutable.set(position).move(-halfLengths.getX(), 0, -halfLengths.getZ()); // pivot
        
        if (canBePlaced(world, position.down(), type)) {
            template.get().place(world, mutable, mutable, placementsettings, context.getRandom(), Block.NO_REDRAW);

            generated = true;
        }
        
        return generated;
    }
    
}