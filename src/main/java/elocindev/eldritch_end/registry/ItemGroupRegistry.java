package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupRegistry {
    public static final ItemGroup EldritchEnd = Registry.register(Registries.ITEM_GROUP,
        new Identifier("eldritch_end", "tab"),
        FabricItemGroup.builder().displayName(Text.translatable("itemGroup.eldritch_end"))
        .icon(() -> new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM))
        .entries((displayContext, entries) -> {
            entries.add(new ItemStack(ItemRegistry.NECRONOMICON));

            entries.add(new ItemStack(ItemRegistry.ABERRATION_LIMB));
            entries.add(new ItemStack(ItemRegistry.ABERRATION_HEART));

            entries.add(new ItemStack(ItemRegistry.RAW_ETYR));
            entries.add(new ItemStack(ItemRegistry.ETYR_INGOT));
            entries.add(new ItemStack(ItemRegistry.ETYR_UPGRADE_TEMPLATE));
                        
            entries.add(new ItemStack(ArmorRegistry.ETYRITE_HELMET));
            entries.add(new ItemStack(ArmorRegistry.ETYRITE_CHESTPLATE));
            entries.add(new ItemStack(ArmorRegistry.ETYRITE_LEGGINGS));
            entries.add(new ItemStack(ArmorRegistry.ETYRITE_BOOTS));

            // -- ARTIFACTS --
            entries.add(new ItemStack(ItemRegistry.XALARATH));

            // -- BLOCKS --
            entries.add(new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM));
            entries.add(new ItemStack(BlockRegistry.SUSPICIOUS_FRONDS_ITEM));
            entries.add(new ItemStack(BlockRegistry.ABYSMAL_TENDRILS_ITEM));
            entries.add(new ItemStack(BlockRegistry.ABYSMAL_ROOTS_ITEM));
            entries.add(new ItemStack(BlockRegistry.ABYSMAL_PEDESTAL_ITEM));
            entries.add(new ItemStack(BlockRegistry.ELDRITCH_PEDESTAL_ITEM));

            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_LOG_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_LEAVES_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_WOOD_ITEM));
            entries.add(new ItemStack(BlockRegistry.STRIPPED_PRIMORDIAL_LOG_ITEM));
            entries.add(new ItemStack(BlockRegistry.STRIPPED_PRIMORDIAL_WOOD_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_PLANKS_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_STAIRS_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_PRESSURE_PLATE_ITEM));

            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_BUTTON_ITEM));

            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_DOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_TRAPDOOR_ITEM));
            entries.add(new ItemStack(ItemRegistry.PRIMORDIAL_BOAT));
            entries.add(new ItemStack(ItemRegistry.PRIMORDIAL_CHEST_BOAT));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_FENCE_ITEM));
            entries.add(new ItemStack(BlockRegistry.PRIMORDIAL_SIGN_ITEM));

            entries.add(new ItemStack(BlockRegistry.HASTURIAN_MOSS_ITEM));
            entries.add(new ItemStack(BlockRegistry.HASTURIAN_GRASS_ITEM));
            entries.add(new ItemStack(BlockRegistry.HASTURIAN_SAND_ITEM));
            entries.add(new ItemStack(BlockRegistry.HASTURIAN_DUNE_SAND_ITEM));
            //entries.add(new ItemStack(BlockRegistry.HASTURIAN_CACTUS_ITEM));
            
            entries.add(new ItemStack(BlockRegistry.SPIRE_STONE_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_BRICKS_ITEM));
            entries.add(new ItemStack(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_BRICKS_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_TILES_ITEM));
            entries.add(new ItemStack(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_TILES_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_PILLAR_ITEM));
            entries.add(new ItemStack(BlockRegistry.CHISELED_POLISHED_SPIRE_STONE_ITEM));

            entries.add(new ItemStack(BlockRegistry.SPIRE_STONE_STAIRS_ITEM));
            entries.add(new ItemStack(BlockRegistry.SPIRE_STONE_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_STAIRS_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_BRICK_STAIRS_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_BRICK_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_TILE_STAIRS_ITEM));
            entries.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_TILE_SLAB_ITEM));

            entries.add(new ItemStack(BlockRegistry.ETYR_ORE_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_BLOCK_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_BARS_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_DOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_PILLAR_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_TILES_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_TRAPDOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.ETYR_STAIRS_ITEM));

            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_BLOCK_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_BARS_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_DOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_PILLAR_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_TILES_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_TRAPDOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.DECADENT_ETYR_STAIRS_ITEM));

            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_BLOCK_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_BARS_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_DOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_PILLAR_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_TILES_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_TRAPDOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_STAIRS_ITEM));

            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_BLOCK_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_BARS_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_DOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_PILLAR_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_TILES_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_TRAPDOOR_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_SLAB_ITEM));
            entries.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_STAIRS_ITEM));

            // -- ENTITY EGGS --
            entries.add(new ItemStack(ItemRegistry.ABERRATION_SPAWN_EGG));
            entries.add(new ItemStack(ItemRegistry.TENTACLE_SPAWN_EGG));
            entries.add(new ItemStack(ItemRegistry.UNDEAD_TENTACLE_SPAWN_EGG));
            entries.add(new ItemStack(ItemRegistry.DENDLER_SPAWN_EGG));
            // entries.add(new ItemStack(ItemRegistry.HASTUR_CROWN));

    }).texture("eldritchend.png").noRenderedName().build());

    public static void register() {}
}
