package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;

public class LootRegistry {
    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                LootPool.Builder corrupted = LootPool.builder()
                    .with(ItemEntry.builder(ItemRegistry.CORRUPTION_UPGRADE_PATTERN).weight(3));

                LootPool.Builder etyr = LootPool.builder()
                    .with(ItemEntry.builder(ItemRegistry.ETYR_UPGRADE_TEMPLATE).weight(3));

                tableBuilder.pool(corrupted);
                tableBuilder.pool(etyr);
            }
        });
    }
}