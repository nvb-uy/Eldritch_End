package elocindev.eldritch_end.block;

import elocindev.eldritch_end.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class HasturianGrass extends PlantBlock {

    public HasturianGrass(Settings settings) {
        super(settings);
    }
    
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(BlockRegistry.HASTURIAN_MOSS);
    }
}
