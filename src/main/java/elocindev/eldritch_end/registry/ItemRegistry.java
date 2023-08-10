package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.EldritchEnd;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);

    public static final Item ABERRATION_SPAWN_EGG = new SpawnEggItem(EntityRegistry.ABERRATION, 0x54496f, 0x726f76, new FabricItemSettings());

    public static Item reg(Item instance, String id) {
        return Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register() {
        LOGGER.info("Registered Prominent Items");
        reg(ABERRATION_SPAWN_EGG, "aberration_spawn_egg");
    }
}
