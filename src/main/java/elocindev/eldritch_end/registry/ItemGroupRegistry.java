package elocindev.eldritch_end.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroupRegistry {
    public static final ItemGroup EldritchEnd = FabricItemGroupBuilder.create(
        new Identifier("eldritch_end", "tab"))
        .icon(() -> new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM))
        .appendItems(stacks -> { 
            stacks.add(new ItemStack(ItemRegistry.NECRONOMICON));

            stacks.add(new ItemStack(ItemRegistry.RAW_ETYR));
            stacks.add(new ItemStack(ItemRegistry.ETYR_INGOT));

            // -- ARTIFACTS --

            // -- BLOCKS --
            stacks.add(new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM)); 
            stacks.add(new ItemStack(BlockRegistry.SUSPICIOUS_FRONDS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ABYSMAL_TENDRILS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ABYSMAL_ROOTS_ITEM));

            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_LOG_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_WOOD_ITEM));
            stacks.add(new ItemStack(BlockRegistry.STRIPPED_PRIMORDIAL_LOG_ITEM));
            stacks.add(new ItemStack(BlockRegistry.STRIPPED_PRIMORDIAL_WOOD_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_PLANKS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_STAIRS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_SLAB_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_PRESSURE_PLATE_ITEM));

            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_BUTTON_ITEM));

            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_DOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_TRAPDOOR_ITEM));
            stacks.add(new ItemStack(ItemRegistry.PRIMORDIAL_BOAT));
            stacks.add(new ItemStack(ItemRegistry.PRIMORDIAL_CHEST_BOAT));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_FENCE_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_SIGN_ITEM));

            stacks.add(new ItemStack(BlockRegistry.HASTURIAN_MOSS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.HASTURIAN_GRASS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.HASTURIAN_SAND_ITEM));
            stacks.add(new ItemStack(BlockRegistry.HASTURIAN_DUNE_SAND_ITEM));
            //stacks.add(new ItemStack(BlockRegistry.HASTURIAN_CACTUS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.SPIRE_STONE_ITEM));            

            stacks.add(new ItemStack(BlockRegistry.ETYR_ORE_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ETYR_BLOCK_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ETYR_BARS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ETYR_DOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ETYR_PILLAR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ETYR_TILES_ITEM));
            stacks.add(new ItemStack(BlockRegistry.ETYR_TRAPDOOR_ITEM));

            stacks.add(new ItemStack(BlockRegistry.DECADENT_ETYR_BLOCK_ITEM));
            stacks.add(new ItemStack(BlockRegistry.DECADENT_ETYR_BARS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.DECADENT_ETYR_DOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.DECADENT_ETYR_PILLAR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.DECADENT_ETYR_TILES_ITEM));
            stacks.add(new ItemStack(BlockRegistry.DECADENT_ETYR_TRAPDOOR_ITEM));

            stacks.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_BLOCK_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_BARS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_DOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_PILLAR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_TILES_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PERTURBED_ETYR_TRAPDOOR_ITEM));

            stacks.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_BLOCK_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_BARS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_DOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_PILLAR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_TILES_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CORRUPTED_ETYR_TRAPDOOR_ITEM));

            stacks.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_ITEM));
            stacks.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_BRICKS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_PILLAR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.POLISHED_SPIRE_STONE_TILES_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CHISELED_POLISHED_SPIRE_STONE_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_BRICKS_ITEM));
            stacks.add(new ItemStack(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_TILES_ITEM));

            // -- ENTITY EGGS --
            stacks.add(new ItemStack(ItemRegistry.ABERRATION_SPAWN_EGG));
            stacks.add(new ItemStack(ItemRegistry.TENTACLE_SPAWN_EGG));

    })
    .build().setTexture("eldritchend.png").hideName();

    public static void register() {}
}
