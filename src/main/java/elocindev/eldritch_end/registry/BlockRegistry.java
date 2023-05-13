package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static final Block ABYSMAL_FRONDS = new Block(FabricBlockSettings
        .copyOf(Blocks.END_STONE)
        .sounds(BlockSoundGroup.NYLIUM));

    public static final BlockItem ABYSMAL_FRONDS_ITEM = new BlockItem(ABYSMAL_FRONDS, new FabricItemSettings());

    public static final Block PRIMORDIAL_LOG = new PillarBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_LOG)
        .sounds(BlockSoundGroup.STEM));
    
    public static final BlockItem PRIMORDIAL_LOG_ITEM = new BlockItem(PRIMORDIAL_LOG, new FabricItemSettings());

    public static void registerFullBlock(String identifier, Block blockInstance, BlockItem itemInstance) {
        Registry.register(Registry.BLOCK, new Identifier(EldritchEnd.MODID, identifier), blockInstance);
        ItemRegistry.reg(itemInstance, identifier);
    }

    public static void register() {
        registerFullBlock("abysmal_fronds", ABYSMAL_FRONDS, ABYSMAL_FRONDS_ITEM);
        registerFullBlock("primordial_log", PRIMORDIAL_LOG, PRIMORDIAL_LOG_ITEM);

        // Grass Overhaul Compatibility
        // if (FabricLoader.getInstance().isModLoaded("grassoverhaul") || FabricLoader.getInstance().isModLoaded("sod")) {
        //     // todo later
        // }
    }
}
