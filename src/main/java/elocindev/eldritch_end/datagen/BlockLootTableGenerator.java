package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    protected BlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(BlockRegistry.ABYSMAL_FRONDS, drops(BlockRegistry.ABYSMAL_FRONDS_ITEM));
        addDrop(BlockRegistry.ABYSMAL_FRONDS, drops(BlockRegistry.ABYSMAL_FRONDS_ITEM));
        addDrop(BlockRegistry.SUSPICIOUS_FRONDS, drops(BlockRegistry.SUSPICIOUS_FRONDS_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_LOG, drops(BlockRegistry.PRIMORDIAL_LOG_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_WOOD, drops(BlockRegistry.PRIMORDIAL_WOOD_ITEM));
        addDrop(BlockRegistry.STRIPPED_PRIMORDIAL_LOG, drops(BlockRegistry.STRIPPED_PRIMORDIAL_LOG_ITEM));
        addDrop(BlockRegistry.STRIPPED_PRIMORDIAL_WOOD, drops(BlockRegistry.STRIPPED_PRIMORDIAL_WOOD_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_PLANKS, drops(BlockRegistry.PRIMORDIAL_PLANKS_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_SLAB, drops(BlockRegistry.PRIMORDIAL_SLAB_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_PRESSURE_PLATE, drops(BlockRegistry.PRIMORDIAL_PRESSURE_PLATE_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_DOOR, doorDrops(BlockRegistry.PRIMORDIAL_DOOR));
        addDrop(BlockRegistry.PRIMORDIAL_TRAPDOOR, drops(BlockRegistry.PRIMORDIAL_TRAPDOOR_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_SIGN, drops(BlockRegistry.PRIMORDIAL_WALL_SIGN));
        addDrop(BlockRegistry.PRIMORDIAL_FENCE, drops(BlockRegistry.PRIMORDIAL_FENCE_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_FENCE_GATE, drops(BlockRegistry.PRIMORDIAL_FENCE_GATE_ITEM));

        addDrop(BlockRegistry.ETYR_ORE, (blockx) -> oreDrops(blockx, ItemRegistry.RAW_ETYR));
        addDrop(BlockRegistry.ETYR_BLOCK, drops(BlockRegistry.ETYR_BLOCK_ITEM));
        addDrop(BlockRegistry.ETYR_BARS, drops(BlockRegistry.ETYR_BARS_ITEM));
        addDrop(BlockRegistry.ETYR_DOOR, doorDrops(BlockRegistry.ETYR_DOOR));
        addDrop(BlockRegistry.ETYR_PILLAR, drops(BlockRegistry.ETYR_PILLAR_ITEM));
        addDrop(BlockRegistry.ETYR_TILES, drops(BlockRegistry.ETYR_TILES_ITEM));
        addDrop(BlockRegistry.ETYR_TRAPDOOR, drops(BlockRegistry.ETYR_TRAPDOOR_ITEM));

        addDrop(BlockRegistry.DECADENT_ETYR_BLOCK, drops(BlockRegistry.DECADENT_ETYR_BLOCK_ITEM));
        addDrop(BlockRegistry.DECADENT_ETYR_BARS, drops(BlockRegistry.DECADENT_ETYR_BARS_ITEM));
        addDrop(BlockRegistry.DECADENT_ETYR_DOOR, doorDrops(BlockRegistry.DECADENT_ETYR_DOOR));
        addDrop(BlockRegistry.DECADENT_ETYR_PILLAR, drops(BlockRegistry.DECADENT_ETYR_PILLAR_ITEM));
        addDrop(BlockRegistry.DECADENT_ETYR_TILES, drops(BlockRegistry.DECADENT_ETYR_TILES_ITEM));
        addDrop(BlockRegistry.DECADENT_ETYR_TRAPDOOR, drops(BlockRegistry.DECADENT_ETYR_TRAPDOOR_ITEM));
        addDrop(BlockRegistry.DECADENT_ETYR_STAIRS, drops(BlockRegistry.DECADENT_ETYR_STAIRS_ITEM));
        addDrop(BlockRegistry.DECADENT_ETYR_SLAB, drops(BlockRegistry.DECADENT_ETYR_SLAB_ITEM));

        addDrop(BlockRegistry.PERTURBED_ETYR_BLOCK, drops(BlockRegistry.PERTURBED_ETYR_BLOCK_ITEM));
        addDrop(BlockRegistry.PERTURBED_ETYR_BARS, drops(BlockRegistry.PERTURBED_ETYR_BARS_ITEM));
        addDrop(BlockRegistry.PERTURBED_ETYR_DOOR, doorDrops(BlockRegistry.PERTURBED_ETYR_DOOR));
        addDrop(BlockRegistry.PERTURBED_ETYR_PILLAR, drops(BlockRegistry.PERTURBED_ETYR_PILLAR_ITEM));
        addDrop(BlockRegistry.PERTURBED_ETYR_TILES, drops(BlockRegistry.PERTURBED_ETYR_TILES_ITEM));
        addDrop(BlockRegistry.PERTURBED_ETYR_TRAPDOOR, drops(BlockRegistry.PERTURBED_ETYR_TRAPDOOR_ITEM));
        addDrop(BlockRegistry.PERTURBED_ETYR_STAIRS, drops(BlockRegistry.PERTURBED_ETYR_STAIRS_ITEM));
        addDrop(BlockRegistry.PERTURBED_ETYR_SLAB, drops(BlockRegistry.PERTURBED_ETYR_SLAB_ITEM));

        addDrop(BlockRegistry.CORRUPTED_ETYR_BLOCK, drops(BlockRegistry.CORRUPTED_ETYR_BLOCK_ITEM));
        addDrop(BlockRegistry.CORRUPTED_ETYR_BARS, drops(BlockRegistry.CORRUPTED_ETYR_BARS_ITEM));
        addDrop(BlockRegistry.CORRUPTED_ETYR_DOOR, doorDrops(BlockRegistry.CORRUPTED_ETYR_DOOR));
        addDrop(BlockRegistry.CORRUPTED_ETYR_PILLAR, drops(BlockRegistry.CORRUPTED_ETYR_PILLAR_ITEM));
        addDrop(BlockRegistry.CORRUPTED_ETYR_TILES, drops(BlockRegistry.CORRUPTED_ETYR_TILES_ITEM));
        addDrop(BlockRegistry.CORRUPTED_ETYR_TRAPDOOR, drops(BlockRegistry.CORRUPTED_ETYR_TRAPDOOR_ITEM));
        addDrop(BlockRegistry.CORRUPTED_ETYR_STAIRS, drops(BlockRegistry.CORRUPTED_ETYR_STAIRS_ITEM));
        addDrop(BlockRegistry.CORRUPTED_ETYR_SLAB, drops(BlockRegistry.CORRUPTED_ETYR_SLAB_ITEM));

        addDrop(BlockRegistry.SPIRE_STONE, drops(BlockRegistry.SPIRE_STONE_ITEM));
        addDrop(BlockRegistry.POLISHED_SPIRE_STONE, drops(BlockRegistry.POLISHED_SPIRE_STONE_ITEM));
        addDrop(BlockRegistry.POLISHED_SPIRE_STONE_BRICKS, drops(BlockRegistry.POLISHED_SPIRE_STONE_BRICKS_ITEM));
        addDrop(BlockRegistry.POLISHED_SPIRE_STONE_PILLAR, drops(BlockRegistry.POLISHED_SPIRE_STONE_PILLAR_ITEM));
        addDrop(BlockRegistry.POLISHED_SPIRE_STONE_TILES, drops(BlockRegistry.POLISHED_SPIRE_STONE_TILES_ITEM));
        addDrop(BlockRegistry.CHISELED_POLISHED_SPIRE_STONE, drops(BlockRegistry.CHISELED_POLISHED_SPIRE_STONE_ITEM));
        addDrop(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_BRICKS, drops(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_BRICKS_ITEM));
        addDrop(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_TILES, drops(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_TILES_ITEM));

        addDrop(BlockRegistry.HASTURIAN_MOSS, drops(BlockRegistry.HASTURIAN_MOSS_ITEM));
        addDrop(BlockRegistry.HASTURIAN_DUNE_SAND, drops(BlockRegistry.HASTURIAN_DUNE_SAND_ITEM));
        addDrop(BlockRegistry.HASTURIAN_SAND, drops(BlockRegistry.HASTURIAN_SAND_ITEM));
        addDrop(BlockRegistry.HASTURIAN_CACTUS, drops(BlockRegistry.HASTURIAN_CACTUS_ITEM));
        addDrop(BlockRegistry.HASTURIAN_GRASS, dropsWithSilkTouch(BlockRegistry.HASTURIAN_GRASS));
        addDrop(BlockRegistry.ABYSMAL_ROOTS, dropsWithSilkTouch(BlockRegistry.ABYSMAL_ROOTS));

        addDrop(BlockRegistry.ETYR_STAIRS, drops(BlockRegistry.ETYR_STAIRS_ITEM));
        addDrop(BlockRegistry.ETYR_SLAB, drops(BlockRegistry.ETYR_SLAB_ITEM));
    }
}
