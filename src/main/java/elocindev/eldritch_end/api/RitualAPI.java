package elocindev.eldritch_end.api;

import elocindev.eldritch_end.block.dark_magic.pedestal.GreatSummoningPedestal;
import elocindev.eldritch_end.block.dark_magic.pedestal.LesserSummoningPedestal;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualAPI {
    public enum RitualStructure {
        INVALID,
        BASIC
    }

    public enum RitualType {
        INVALID,
        SUMMONING
    }

    /**
     * Checks if there's a valid ritual structure at the given BlockPos
     * 
     * @param center The BlockPos of the Great Summoning Pedestal. (e.g. Eldritch Pedestal)
     * @param world The World where the ritual is happening.
     * 
     * @return The RitualStructure of the ritual.
     * @see RitualStructure
     */
    public static RitualStructure getRitualStructure(BlockPos center, World world) {
        if (isBasicRitual(center, world)) return RitualStructure.BASIC;

        return RitualStructure.INVALID;
    }

    /**
     * Checks if there's a basic ritual structure at the given BlockPos.
     * 
     * @param center The BlockPos of the Great Summoning Pedestal. (e.g. Eldritch Pedestal)
     * @param world The World where the ritual is happening.
     * 
     * @return True if the ritual structure is valid.
     */
    public static boolean isBasicRitual(BlockPos center, World world) {
        if (world.getBlockState(center).getBlock() instanceof GreatSummoningPedestal) {
            int lesserPedestals = 0;

            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    if (world.getBlockState(center.add(x, 0, z)).getBlock() instanceof LesserSummoningPedestal) {
                        lesserPedestals++;
                    }
                }
            }

            if (lesserPedestals == 4) return true;
        }
        
        return false;
    }

    /**
     * Gets the summoning power of the ritual structure.
     * 
     * @param center                The BlockPos of the Great Summoning Pedestal. (e.g. Eldritch Pedestal)
     * @param type                  The RitualStructure of the ritual.
     * @param world                 The World where the ritual is happening.
     * @param power_block           The TagKey of the power block(s), e.g. faceless_summon_blocks for The Faceless.
     * @param power_per_block       The summoning power given per block.
     * 
     * @return                      The summoning power of the ritual structure.
     */
    public static int getSummoningPower(BlockPos center, RitualStructure type, World world, TagKey<Block> power_block, int power_per_block) {
        if (type == RitualStructure.BASIC) {
            int power = 0;

            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    if (world.getBlockState(center.add(x, 1, z)).isIn(power_block)) {                        
                        power =+ power_per_block;
                    }
                }
            }

            return power;
        }

        return 0;
    }
}
