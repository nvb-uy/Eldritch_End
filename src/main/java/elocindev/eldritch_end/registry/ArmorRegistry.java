package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.item.armor.EtyriteArmorMaterial;
import elocindev.eldritch_end.item.armor.EtyriteArmorPiece;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArmorRegistry {
    public static final ArmorMaterial ETYRITE_ARMOR_MATERIAL = new EtyriteArmorMaterial();

    public static final Item ETYRITE_HELMET = new EtyriteArmorPiece(ETYRITE_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());
    public static final Item ETYRITE_CHESTPLATE = new EtyriteArmorPiece(ETYRITE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
    public static final Item ETYRITE_LEGGINGS = new EtyriteArmorPiece(ETYRITE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());
    public static final Item ETYRITE_BOOTS = new EtyriteArmorPiece(ETYRITE_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings());

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, "etyrite_helmet"), ETYRITE_HELMET);
        Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, "etyrite_chestplate"), ETYRITE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, "etyrite_leggings"), ETYRITE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, "etyrite_boots"), ETYRITE_BOOTS);
    }
}
