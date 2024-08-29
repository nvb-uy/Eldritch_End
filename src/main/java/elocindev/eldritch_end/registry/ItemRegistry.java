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
import elocindev.eldritch_end.api.RitualAPI.RitualStructure;
import elocindev.eldritch_end.corruption.CorruptionDisplayTooltip;
import elocindev.eldritch_end.item.Necronomicon;
import elocindev.eldritch_end.item.SilverKey;
import elocindev.eldritch_end.item.dark_magic.SummonPartItem;
import elocindev.eldritch_end.item.relics.Xal;
import elocindev.eldritch_end.item.spawneggs.AberrationEgg;
import elocindev.eldritch_end.item.spawneggs.DendlerEgg;
import elocindev.eldritch_end.item.spawneggs.TentacleEgg;
import elocindev.necronomicon.api.text.TextAPI;
import elocindev.eldritch_end.item.Chorb;

public class ItemRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EldritchEnd.MODID);

    public static final Item ABERRATION_SPAWN_EGG = reg(new AberrationEgg(EntityRegistry.ABERRATION, 0x54496f, 0xC76800, new FabricItemSettings()), "aberration_spawn_egg");
    public static final Item TENTACLE_SPAWN_EGG = reg(new TentacleEgg(EntityRegistry.TENTACLE, 0x54496f, 0x9a87b0, new FabricItemSettings()), "tentacle_spawn_egg");
    public static final Item UNDEAD_TENTACLE_SPAWN_EGG = reg(new TentacleEgg(EntityRegistry.UNDEAD_TENTACLE, 0x29221D, 0x816445, new FabricItemSettings()), "undead_tentacle_spawn_egg");
    public static final Item DENDLER_SPAWN_EGG = reg(new DendlerEgg(EntityRegistry.DENDLER, 0x29221D, 0xcca64e, new FabricItemSettings()), "dendler_spawn_egg");

    // public static final Item HASTUR_CROWN = reg(new HastursCrown(EntityRegistry.HASTUR, 0xFFFFFF, 0xFFFFFF, new FabricItemSettings()), "hastur_crown");

    public static final Item NECRONOMICON = reg(new Necronomicon(new FabricItemSettings()), "necronomicon");
    public static final Item SILVER_KEY = reg(new SilverKey(new FabricItemSettings()), "silver_key");
    public static final Item ABERRATION_LIMB = reg(new Item(new FabricItemSettings()), "aberration_limb");

    public static final Item ABERRATION_HEART = reg(new SummonPartItem(new FabricItemSettings(), RitualStructure.BASIC, BlockRegistry.ELDRITCH_PEDESTAL, BlockRegistry.ABYSMAL_PEDESTAL.getDefaultState(), EntityRegistry.THE_FACELESS), "aberration_heart");

    public static final Item XAL = reg(new Xal(new FabricItemSettings().maxCount(1).fireproof()), "xal");

    public static final Item PRIMORDIAL_BOAT = reg(new BoatItem(false, EntityRegistry.PRIMORDIAL, new FabricItemSettings()), "primordial_boat");
    public static final Item PRIMORDIAL_CHEST_BOAT = reg(new BoatItem(true, EntityRegistry.PRIMORDIAL, new FabricItemSettings()), "primordial_chest_boat");

    public static final Item CHORB_SPAWN_EGG = reg(new Chorb(EntityType.GIANT, 0xfcba03, 0xfce703, new FabricItemSettings()), "chorb_spawn_egg");

    public static final Item CORRUPTION_MENU = reg(new CorruptionDisplayTooltip(new FabricItemSettings()), "corruption");

    public static final Item RAW_ETYR = reg(new Item(new FabricItemSettings()), "raw_etyr");
    public static final Item ETYR_INGOT = reg(new Item(new FabricItemSettings()), "etyr_ingot");
    public static final Item ETYR_UPGRADE_TEMPLATE = reg(new SmithingTemplateItem(
            Text.literal("Netherite Equipment").setStyle(Style.EMPTY.withColor(Formatting.BLUE)),
            Text.translatable("item.eldritch_end.etyr_ingot").setStyle(Style.EMPTY.withColor(Formatting.BLUE)),
            TextAPI.Styles.getStaticGradient(Text.literal("Etyr Upgrade"), 0x4c6956, 0x7aa187),
            Text.empty(),
            Text.empty(),
            List.of(new Identifier("minecraft:item/empty_slot_smithing_template_netherite_upgrade")),
            List.of()
        ), "etyr_upgrade_pattern");

    public static Item reg(Item instance, String id) {
        return Registry.register(Registries.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register(){
        LOGGER.info("Registered Eldritch End items");
    }
}
