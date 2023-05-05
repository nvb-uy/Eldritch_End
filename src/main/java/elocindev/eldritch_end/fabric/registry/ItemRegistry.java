package elocindev.prominent.fabric_quilt.registry;

import elocindev.prominent.fabric_quilt.EldritchEnd;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);

    
    // private static Item.Settings discSettings = new Item.Settings().rarity(Rarity.RARE).maxCount(1).group(ProminentLoader.ProminentTab);
    
    


    public static Item reg(Item instance, String id) {
        
        return Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void registerItems() {
        LOGGER.info("Registered Prominent Items");
    }
}
