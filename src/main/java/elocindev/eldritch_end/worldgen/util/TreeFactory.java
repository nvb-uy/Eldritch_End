package elocindev.eldritch_end.worldgen.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;

public class TreeFactory {
    // *------------------------*
    //        DEAD TREES
    // *------------------------*

    // -- SMALL --
    public static void placeSmallDeadTree(StructureWorldAccess world, BlockPos pos, BlockState block) {
        
        for (int i = 0; i < 4; i++) {
            if (world.getRandom().nextBoolean() && i == 4) {
                world.setBlockState(pos.up(i), block, 3);
                break;
            }

            world.setBlockState(pos.up(i), block, 3);
        }

        world.setBlockState(randomSouthNorth(pos.up(world.getRandom().nextBetween(1, 2)), world), block.with(PillarBlock.AXIS, Direction.SOUTH.getAxis()), 3);
        if (world.getRandom().nextBoolean()) {
            world.setBlockState(randomEastWest(pos.up(world.getRandom().nextBetween(2, 3)), world), block.with(PillarBlock.AXIS, Direction.EAST.getAxis()), 3);
        }
    }

    // -- MEDIUM 1 --
    public static void placeMediumDeadTree1(StructureWorldAccess world, BlockPos pos, BlockState block) {
        BlockPos[] branches_vertical = {
            pos.south().up(4),
            pos.south().east().up(5),
            pos.south().east().up(6),
            pos.west(2).up(5),
            pos.west(2).up(6),
            pos.west(2).up(7),
            pos.north(2).east().up(3),
            pos.north(2).east().up(4)
        };        
        for (int i = 0; i < 4; i++) {
            world.setBlockState(pos.up(i), block, 3);
        }

        for (int i = 0; i < branches_vertical.length; i++) {
            world.setBlockState(branches_vertical[i], block, 3);
        }
        
        world.setBlockState(pos.north().up(2), block.with(PillarBlock.AXIS, Direction.NORTH.getAxis()), 3);
        world.setBlockState(pos.south().west().up(4), block.with(PillarBlock.AXIS, Direction.WEST.getAxis()), 3);
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
