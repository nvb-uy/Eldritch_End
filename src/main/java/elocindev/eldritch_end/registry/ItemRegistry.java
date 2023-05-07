package elocindev.eldritch_end.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.EldritchEnd;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);
   
    public static Item reg(Item instance, String id) {
        return Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register() {
        LOGGER.info("Registered Prominent Items");
    }
}
