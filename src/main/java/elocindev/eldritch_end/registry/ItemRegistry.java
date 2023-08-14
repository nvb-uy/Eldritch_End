package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.item.Chorb;
import elocindev.eldritch_end.item.Necronomicon;
import elocindev.eldritch_end.item.SilverKey;
import elocindev.eldritch_end.item.spawneggs.AberrationEgg;
import elocindev.eldritch_end.item.spawneggs.TentacleEgg;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);

    public static final Item ABERRATION_SPAWN_EGG = new AberrationEgg(EntityRegistry.ABERRATION, 0x54496f, 0x726f76, new FabricItemSettings());    
    public static final Item TENTACLE_SPAWN_EGG = new TentacleEgg(EntityRegistry.TENTACLE, 0x54496f, 0x9a87b0, new FabricItemSettings());  

    public static final Item NECRONOMICON = new Necronomicon(new FabricItemSettings());
    public static final Item SILVER_KEY = new SilverKey(new FabricItemSettings());

    // Hello random person of the internet, if you are here after EE has come out publicly, you may be wondering why the chorb spawn egg is here.
    // Well that's because this is an easter egg. Please don't tell anyone and just enjoy it ingame :thisisthesunglassescat:
    public static final Item CHORB_SPAWN_EGG = new Chorb(EntityType.GIANT, 0xfcba03, 0xfce703, new FabricItemSettings());

    public static Item reg(Item instance, String id) {
        return Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register() {
        
        reg(ABERRATION_SPAWN_EGG, "aberration_spawn_egg");
        reg(TENTACLE_SPAWN_EGG, "tentacle_spawn_egg");

        reg(NECRONOMICON, "necronomicon");
        reg(SILVER_KEY, "silver_key");
        reg(CHORB_SPAWN_EGG, "chorb_spawn_egg");
        LOGGER.info("Registered Eldritch End's items");
    }
}
