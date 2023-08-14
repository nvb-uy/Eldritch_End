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

            // todo: stairs
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_DOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_TRAPDOOR_ITEM));
            stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_SIGN_ITEM));

            stacks.add(new ItemStack(BlockRegistry.HASTURIAN_MOSS_ITEM));

            // -- ARTIFACTS --
            stacks.add(new ItemStack(ItemRegistry.SILVER_KEY));

            // -- ENTITY EGGS --
            stacks.add(new ItemStack(ItemRegistry.ABERRATION_SPAWN_EGG));
            stacks.add(new ItemStack(ItemRegistry.TENTACLE_SPAWN_EGG));
            stacks.add(new ItemStack(ItemRegistry.CHORB_SPAWN_EGG));
    })
    .build().setTexture("eldritchend.png").hideName();

    public static void register() {}
}
