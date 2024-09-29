package elocindev.eldritch_end.item.utils;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagUtils {
    public static final TagKey<Item> WEAPONS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "weapons"));
    public static final TagKey<Item> ARMOR = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "armor"));

    public static boolean isWeapon(ItemStack item) {
        return item.isIn(WEAPONS) || isWeapon(item.getItem());
    }

    public static boolean isWeapon(Item item) {
        return (item instanceof SwordItem || item instanceof AxeItem || item instanceof ToolItem || item instanceof RangedWeaponItem);
    }

    public static boolean isArmor(ItemStack item) {
        return item.isIn(ARMOR) || isArmor(item.getItem());
    }

    public static boolean isArmor(Item item) {
        return item instanceof ArmorItem || item instanceof Equipment;
    }
}
