package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.registry.BlockRegistry;
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
    }
}
