package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;

public class LootRegistry {
    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                LootPool corruption = LootPool.builder().with(ItemEntry.builder(ItemRegistry.CORRUPTION_INFUSION_TEMPLATE).build()).rolls(BinomialLootNumberProvider.create(1, 0.10F)).build();
                LootPool etyr = LootPool.builder().with(ItemEntry.builder(ItemRegistry.ETYR_INFUSION_TEMPLATE).build()).rolls(BinomialLootNumberProvider.create(1, 0.10F)).build();

                tableBuilder.pool(etyr);
                tableBuilder.pool(corruption);
            }
        });
    }
}