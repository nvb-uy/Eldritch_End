package elocindev.eldritch_end.worldgen.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

public class TreeFactory {
    // *------------------------*
    //        DEAD TREES
    // *------------------------*
    public static void placeSmallDeadTree(StructureWorldAccess world, BlockPos pos, BlockState block) {
        
        for (int i = 0; i < 4; i++) {
            if (world.getRandom().nextBoolean() && i == 5) {
                world.setBlockState(pos.up(i), block, 3);
                break;
            }

            world.setBlockState(pos.up(i), block, 3);
        }
    

        world.setBlockState(randomSouthNorth(pos.up(world.getRandom().nextBetween(1, 2)), world), block, 3);
        if (world.getRandom().nextBoolean()) world.setBlockState(randomEastWest(pos.up(world.getRandom().nextBetween(2, 3)), world), block.rotate(BlockRotation.CLOCKWISE_90), 3);
    }

    // Utils

    public static BlockPos randomAny(BlockPos pos, StructureWorldAccess world) {
        switch (world.getRandom().nextInt(4)) {
            case 0:
                return pos.east();
            case 1:
                return pos.west();
            case 2:
                return pos.north();
            case 3:
                return pos.south();
        }

        return pos;
    }


    public static BlockPos randomSouthNorth(BlockPos pos, StructureWorldAccess world) {
        switch (world.getRandom().nextInt(2)) {
            case 0:
                return pos.north();
            case 1:
                return pos.south();
        }

        return pos;
    }

    public static BlockPos randomEastWest(BlockPos pos, StructureWorldAccess world) {
        switch (world.getRandom().nextInt(2)) {
            case 0:
                return pos.east();
            case 1:
                return pos.west();
        }

        return pos;
    }
}
