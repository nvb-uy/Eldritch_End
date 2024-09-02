package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
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
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.corruption.CorruptionDisplayTooltip;
import elocindev.eldritch_end.item.Necronomicon;
import elocindev.eldritch_end.item.SilverKey;
import elocindev.eldritch_end.item.infusion_materials.AberrationHeartItem;
import elocindev.eldritch_end.item.infusion_materials.AberrationLimbItem;
import elocindev.eldritch_end.item.infusion_materials.EtyrIngot;
import elocindev.eldritch_end.item.infusion_templates.CorruptionTemplate;
import elocindev.eldritch_end.item.infusion_templates.EtyrTemplate;
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
    public static final Item ABERRATION_LIMB = reg(new AberrationLimbItem(new FabricItemSettings()), "aberration_limb");
    public static final Item OMINOUS_EYE = reg(new Item(new FabricItemSettings()), "ominous_eye");

    public static final Item XAL = reg(new Xal(new FabricItemSettings().maxCount(1).fireproof()), "xal");

    public static final Item PRIMORDIAL_BOAT = reg(new BoatItem(false, EntityRegistry.PRIMORDIAL, new FabricItemSettings()), "primordial_boat");
    public static final Item PRIMORDIAL_CHEST_BOAT = reg(new BoatItem(true, EntityRegistry.PRIMORDIAL, new FabricItemSettings()), "primordial_chest_boat");

    public static final Item CHORB_SPAWN_EGG = reg(new Chorb(EntityType.GIANT, 0xfcba03, 0xfce703, new FabricItemSettings()), "chorb_spawn_egg");

    public static final Item CORRUPTION_MENU = reg(new CorruptionDisplayTooltip(new FabricItemSettings()), "corruption");

    public static final Item RAW_ETYR = reg(new Item(new FabricItemSettings()), "raw_etyr");

    // Infusion materials
    public static final Item ETYR_INGOT = reg(new EtyrIngot(new FabricItemSettings()), "etyr_ingot");
    public static final Item ABERRATION_HEART = reg(new AberrationHeartItem(new FabricItemSettings(), RitualStructure.BASIC, BlockRegistry.ELDRITCH_PEDESTAL, BlockRegistry.ABYSMAL_PEDESTAL.getDefaultState(), EntityRegistry.THE_FACELESS, true, List.of(new Identifier("eldritch_end:primordial_abyss"))), "aberration_heart");

    private static String etyr_appliesto = 
        Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_armor && Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_weapons ? 
        "all" : 
        (Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_armor ? "armor" : 
        (Configs.Mechanics.INFUSIONS.etyr_infusion.can_apply_to_weapons ? "weapons" : "none"));
    private static String corruption_appliesto = 
        Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor && Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons ? 
        "all" : 
        (Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor ? "armor" : 
        (Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons ? "weapons" : "none"));
    public static final Item ETYR_UPGRADE_TEMPLATE = reg(new EtyrTemplate(
            Text.translatable("infusion.eldritch_end.applies_to_"+etyr_appliesto).setStyle(Style.EMPTY.withColor(Formatting.BLUE)),
            Text.translatable("item.eldritch_end.etyr_ingot").setStyle(Style.EMPTY.withColor(Formatting.BLUE)).append(Text.literal(" (Tier I)").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY))),
            TextAPI.Styles.getStaticGradient(Text.literal("Etyr Infusion"), 0x728062, 0x7a966b),
            Text.empty(),
            Text.empty(),
            List.of(new Identifier("minecraft:item/empty_slot_smithing_template_netherite_upgrade")),
            List.of()
        ), "etyr_upgrade_pattern");
        
    public static final Item CORRUPTION_UPGRADE_PATTERN = reg(new CorruptionTemplate(
            Text.translatable("infusion.eldritch_end.applies_to_"+corruption_appliesto).setStyle(Style.EMPTY.withColor(Formatting.BLUE)),
            (Text.translatable("item.eldritch_end.aberration_limb")).setStyle(Style.EMPTY.withColor(Formatting.BLUE)).append(Text.literal(" (Tier I)").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY))),
            TextAPI.Styles.getStaticGradient(Text.literal("Corruption Infusion"), 0x604bb8, 0x6541bf),
            Text.empty(),
            Text.empty(),
            List.of(new Identifier("minecraft:item/empty_slot_smithing_template_netherite_upgrade")),
            List.of()
        ), "corruption_upgrade_pattern");

    public static Item reg(Item instance, String id) {
        return Registry.register(Registries.ITEM, new Identifier(EldritchEnd.MODID, id), instance);
    }

    public static void register(){
        LOGGER.info("Registered Eldritch End items");
    }
}
