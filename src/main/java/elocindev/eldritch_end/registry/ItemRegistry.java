package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.item.Necronomicon;
import elocindev.eldritch_end.item.SilverKey;
import elocindev.eldritch_end.item.spawneggs.AberrationEgg;
import elocindev.eldritch_end.item.spawneggs.HastursCrown;
import elocindev.eldritch_end.item.spawneggs.TentacleEgg;
import elocindev.eldritch_end.item.upgrades.EtyrPattern;
import elocindev.eldritch_end.item.Chorb;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);

    public static final Item ABERRATION_SPAWN_EGG = new AberrationEgg(EntityRegistry.ABERRATION, 0x54496f, 0x726f76, new FabricItemSettings());    
    public static final Item TENTACLE_SPAWN_EGG = new TentacleEgg(EntityRegistry.TENTACLE, 0x54496f, 0x9a87b0, new FabricItemSettings());
    public static final Item UNDEAD_TENTACLE_SPAWN_EGG = new TentacleEgg(EntityRegistry.UNDEAD_TENTACLE, 0x54496f, 0x9a87b0, new FabricItemSettings());
    public static final Item HASTUR_CROWN = new HastursCrown(EntityRegistry.HASTUR, 0xFFFFFF, 0xFFFFFF, new FabricItemSettings());

    public static final Item NECRONOMICON = new Necronomicon(new FabricItemSettings());
    public static final Item SILVER_KEY = new SilverKey(new FabricItemSettings());

    public static final Item PRIMORDIAL_BOAT = new BoatItem(false, EntityRegistry.PRIMORDIAL, new FabricItemSettings());
    public static final Item PRIMORDIAL_CHEST_BOAT = new BoatItem(true, EntityRegistry.PRIMORDIAL, new FabricItemSettings());

    // Hello random person of the internet, if you are here after EE has come out publicly, you may be wondering why the chorb spawn egg is here.
    // Well that's because this is an easter egg. Please don't tell anyone and just enjoy it ingame :thisisthesunglassescat:
    public static final Item CHORB_SPAWN_EGG = new Chorb(EntityType.GIANT, 0xfcba03, 0xfce703, new FabricItemSettings());

    public static final Item RAW_ETYR = new Item(new FabricItemSettings());
    public static final Item ETYR_INGOT = new Item(new FabricItemSettings());
    public static final Item ETYR_UPGRADE_PATTERN = new EtyrPattern(new FabricItemSettings());

    public static Item reg(Item instance, String id) {
        return Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register() {
        
        reg(ABERRATION_SPAWN_EGG, "aberration_spawn_egg");
        reg(TENTACLE_SPAWN_EGG, "tentacle_spawn_egg");
        reg(UNDEAD_TENTACLE_SPAWN_EGG, "undead_tentacle_spawn_egg");
        reg(HASTUR_CROWN, "hastur_crown");

        reg(NECRONOMICON, "necronomicon");
        reg(SILVER_KEY, "silver_key");
        reg(CHORB_SPAWN_EGG, "chorb_spawn_egg");

        reg(PRIMORDIAL_BOAT, "primordial_boat");
        reg(PRIMORDIAL_CHEST_BOAT, "primordial_chest_boat");

        reg(RAW_ETYR, "raw_etyr");
        reg(ETYR_INGOT, "etyr_ingot");
        reg(ETYR_UPGRADE_PATTERN, "etyr_upgrade_pattern");
        
        LOGGER.info("Registered Eldritch End's items");
    }
}
