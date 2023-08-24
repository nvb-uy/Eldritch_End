package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    protected BlockLootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
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
        addDrop(BlockRegistry.PRIMORDIAL_DOOR, drops(BlockRegistry.PRIMORDIAL_DOOR_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_TRAPDOOR, drops(BlockRegistry.PRIMORDIAL_TRAPDOOR_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_SIGN, drops(BlockRegistry.PRIMORDIAL_WALL_SIGN));
        addDrop(BlockRegistry.PRIMORDIAL_FENCE, drops(BlockRegistry.PRIMORDIAL_FENCE_ITEM));
        addDrop(BlockRegistry.PRIMORDIAL_FENCE_GATE, drops(BlockRegistry.PRIMORDIAL_FENCE_GATE_ITEM));

        addDrop(BlockRegistry.ETYR_ORE, (blockx) -> oreDrops(blockx, ItemRegistry.RAW_ETYR));
        addDrop(BlockRegistry.ETYR_BLOCK, drops(BlockRegistry.ETYR_BLOCK_ITEM));
        addDrop(BlockRegistry.ETYR_BARS, drops(BlockRegistry.ETYR_BARS_ITEM));
        addDrop(BlockRegistry.ETYR_DOOR, drops(BlockRegistry.ETYR_DOOR_ITEM));
        addDrop(BlockRegistry.ETYR_PILLAR, drops(BlockRegistry.ETYR_PILLAR_ITEM));
        addDrop(BlockRegistry.ETYR_TILES, drops(BlockRegistry.ETYR_TILES_ITEM));
        addDrop(BlockRegistry.ETYR_TRAPDOOR, drops(BlockRegistry.ETYR_TRAPDOOR_ITEM));

        addDrop(BlockRegistry.HASTURIAN_DUNE_SAND, drops(BlockRegistry.HASTURIAN_DUNE_SAND_ITEM));
        addDrop(BlockRegistry.HASTURIAN_DUNE_SOIL, drops(BlockRegistry.HASTURIAN_DUNE_SOIL_ITEM));
        addDrop(BlockRegistry.HASTURIAN_CACTUS, drops(BlockRegistry.HASTURIAN_CACTUS_ITEM));
        addDrop(BlockRegistry.HASTURIAN_GRASS, grassDrops(BlockRegistry.HASTURIAN_GRASS));
    }
}
