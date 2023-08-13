package elocindev.eldritch_end.worldgen.util;

import elocindev.eldritch_end.block.AbysmalTendrils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;

public class TendrilFactory {
    public static void place(StructureWorldAccess world, BlockPos[] tendrilPos, BlockPos[] susPos, BlockState tendril, BlockState susfrond) {

        for (BlockPos position : tendrilPos) {
            if (world.getBlockState(position).getBlock() != Blocks.END_STONE) continue;

            if (world.getRandom().nextBoolean())
                world.setBlockState(position, tendril.with(AbysmalTendrils.FACING, Direction.EAST), 3);
            else
                world.setBlockState(position, tendril, 3);
        }

        for (BlockPos position : susPos) {
            if (world.getBlockState(position).getBlock() != Blocks.END_STONE) continue;

            world.setBlockState(position, susfrond, 3);
        }

    }

    public static void placeTendrilBig(StructureWorldAccess world, BlockPos pos, BlockState tendril, BlockState susfrond) {
        BlockPos[] tendrilsPos = {
            pos,
            pos.east(),
            pos.east(2),
            pos.east(2).north(2),
            pos.east(1).north(2),
            pos.east(1).north(1),
            pos.north(),
            pos.west(),
            pos.west(2),
            pos.west().north(),
            pos.west().north(2),
            pos.west(2).north(2),
            pos.west(2).north(3),
            pos.south(),
            pos.west().south(2)
        };
        // WHEN THE POS IS SUS ඞඞඞඞඞ
        BlockPos[] susPos = {
            pos.south().west(),
            pos.north().east(2)
        };

        place(world, tendrilsPos, susPos, tendril, susfrond);
    }

    public static void placeTendrilMedium(StructureWorldAccess world, BlockPos pos, BlockState tendril, BlockState susfrond) {
        BlockPos[] tendrilsPos = {
            pos,
            pos.east(),
            pos.east(2),
            pos.west(),
            pos.west(2),
            pos.north(),
            pos.north().west(),
            pos.south().east(),
            pos.south().west(2)

        };
        // WHEN THE POS IS SUS ඞඞඞඞඞ
        BlockPos[] susPos = {
            pos.north().east(),
            pos.south().east()
        };

        place(world, tendrilsPos, susPos, tendril, susfrond);
    }
}
