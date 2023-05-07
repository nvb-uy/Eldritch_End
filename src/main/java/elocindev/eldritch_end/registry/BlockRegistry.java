package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static final Block ABYSMAL_FRONDS = new Block(FabricBlockSettings.copyOf(Blocks.END_STONE));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(EldritchEnd.MODID, "abysmal_fronds"), ABYSMAL_FRONDS);
        ItemRegistry.reg(new BlockItem(ABYSMAL_FRONDS, new FabricItemSettings()), "abysmal_fronds");

        // Grass Overhaul Compatibility
        if (FabricLoader.getInstance().isModLoaded("grassoverhaul") || FabricLoader.getInstance().isModLoaded("sod")) {
            // todo later
        }
    }
}
