package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.corruption.DummyCI;
import elocindev.eldritch_end.item.Necronomicon;
import elocindev.eldritch_end.item.SilverKey;
import elocindev.eldritch_end.item.artifacts.Xalarath;
import elocindev.eldritch_end.item.spawneggs.AberrationEgg;
import elocindev.eldritch_end.item.spawneggs.DendlerEgg;
import elocindev.eldritch_end.item.spawneggs.TentacleEgg;
import elocindev.necronomicon.api.text.TextAPI;
import elocindev.eldritch_end.item.Chorb;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);

    public static final Item ABERRATION_SPAWN_EGG = new AberrationEgg(EntityRegistry.ABERRATION, 0x54496f, 0xC76800, new FabricItemSettings());
    public static final Item TENTACLE_SPAWN_EGG = new TentacleEgg(EntityRegistry.TENTACLE, 0x54496f, 0x9a87b0, new FabricItemSettings());
    public static final Item UNDEAD_TENTACLE_SPAWN_EGG = new TentacleEgg(EntityRegistry.UNDEAD_TENTACLE, 0x29221D, 0x816445,  new FabricItemSettings());
    public static final Item DENDLER_SPAWN_EGG = reg(new DendlerEgg(EntityRegistry.DENDLER, 0x29221D, 0xcca64e, new FabricItemSettings()), "dendler_spawn_egg");
    
    // public static final Item HASTUR_CROWN = new HastursCrown(EntityRegistry.HASTUR, 0xFFFFFF, 0xFFFFFF, new FabricItemSettings());

    public static final Item NECRONOMICON = new Necronomicon(new FabricItemSettings());
    public static final Item SILVER_KEY = new SilverKey(new FabricItemSettings());
    public static final Item ABERRATION_LIMB = new Item(new FabricItemSettings());
    public static final Item ABERRATION_HEART = new Item(new FabricItemSettings());

    public static final Item XALARATH = new Xalarath(new FabricItemSettings().maxCount(1).fireproof());

    public static final Item PRIMORDIAL_BOAT = new BoatItem(false, EntityRegistry.PRIMORDIAL, new FabricItemSettings());
    public static final Item PRIMORDIAL_CHEST_BOAT = new BoatItem(true, EntityRegistry.PRIMORDIAL, new FabricItemSettings());

    // Hello random person of the internet, if you are here after EE has come out publicly, you may be wondering why the chorb spawn egg is here.
    // Well that's because this is an easter egg. Please don't tell anyone and just enjoy it ingame :thisisthesunglassescat:
    public static final Item CHORB_SPAWN_EGG = new Chorb(EntityType.GIANT, 0xfcba03, 0xfce703, new FabricItemSettings());

    public static final Item CORRUPTION_MENU = new DummyCI(new FabricItemSettings());

    public static final Item RAW_ETYR = new Item(new FabricItemSettings());
    public static final Item ETYR_INGOT = new Item(new FabricItemSettings());
    public static final Item ETYR_UPGRADE_TEMPLATE = new SmithingTemplateItem(
        Text.literal("Netherite Equipment").setStyle(Style.EMPTY.withColor(Formatting.BLUE)),
        Text.translatable("item.eldritch_end.etyr_ingot").setStyle(Style.EMPTY.withColor(Formatting.BLUE)),
        TextAPI.Styles.getStaticGradient(Text.literal("Etyr Upgrade"), 0x4c6956, 0x7aa187),
        Text.empty(),
        Text.empty(),
        List.of(new Identifier("minecraft:item/empty_slot_smithing_template_netherite_upgrade")),
        List.of()
        );

    public static Item reg(Item instance, String id) {
        return Registry.register(Registries.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register() {
        reg(NECRONOMICON, "necronomicon");
        reg(SILVER_KEY, "silver_key");
        reg(ABERRATION_LIMB, "aberration_limb");
        reg(ABERRATION_HEART, "aberration_heart");
        
        reg(XALARATH, "xalarath");

        reg(PRIMORDIAL_BOAT, "primordial_boat");
        reg(PRIMORDIAL_CHEST_BOAT, "primordial_chest_boat");

        reg(CORRUPTION_MENU, "corruption");

        reg(RAW_ETYR, "raw_etyr");
        reg(ETYR_INGOT, "etyr_ingot");
        reg(ETYR_UPGRADE_TEMPLATE, "etyr_upgrade_pattern");

        reg(ABERRATION_SPAWN_EGG, "aberration_spawn_egg");
        reg(TENTACLE_SPAWN_EGG, "tentacle_spawn_egg");
        reg(UNDEAD_TENTACLE_SPAWN_EGG, "undead_tentacle_spawn_egg");
        reg(CHORB_SPAWN_EGG, "chorb_spawn_egg");
        // reg(HASTUR_CROWN, "hastur_crown");
        
        LOGGER.info("Registered Eldritch End's items");
    }
}
