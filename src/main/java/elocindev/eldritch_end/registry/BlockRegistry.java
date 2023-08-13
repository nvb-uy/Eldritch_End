package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.block.AbysmalFronds;
import elocindev.eldritch_end.block.AbysmalTendrils;
import elocindev.eldritch_end.block.SuspiciousFronds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static final Block ABYSMAL_FRONDS = new AbysmalFronds();
    public static final BlockItem ABYSMAL_FRONDS_ITEM = new BlockItem(ABYSMAL_FRONDS, new FabricItemSettings());

    public static final Block SUSPICIOUS_FRONDS = new SuspiciousFronds();
    public static final BlockItem SUSPICIOUS_FRONDS_ITEM = new BlockItem(SUSPICIOUS_FRONDS, new FabricItemSettings());

    public static final Block ABYSMAL_TENDRILS = new AbysmalTendrils();
    public static final BlockItem ABYSMAL_TENDRILS_ITEM = new BlockItem(ABYSMAL_TENDRILS, new FabricItemSettings());

    public static final Block PRIMORDIAL_LOG = new PillarBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_LOG)
        .sounds(BlockSoundGroup.STEM));

    public static final BlockItem PRIMORDIAL_LOG_ITEM = new BlockItem(PRIMORDIAL_LOG, new FabricItemSettings());

    public static final Block STRIPPED_PRIMORDIAL_LOG = new PillarBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_LOG)
        .sounds(BlockSoundGroup.STEM));

    public static final BlockItem STRIPPED_PRIMORDIAL_LOG_ITEM = new BlockItem(STRIPPED_PRIMORDIAL_LOG, new FabricItemSettings());


    public static final Block PRIMORDIAL_WOOD = new PillarBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_LOG)
        .sounds(BlockSoundGroup.STEM));

    public static final BlockItem PRIMORDIAL_WOOD_ITEM = new BlockItem(PRIMORDIAL_WOOD, new FabricItemSettings());


    public static final Block STRIPPED_PRIMORDIAL_WOOD = new PillarBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_LOG)
        .sounds(BlockSoundGroup.STEM));

    public static final BlockItem STRIPPED_PRIMORDIAL_WOOD_ITEM = new BlockItem(STRIPPED_PRIMORDIAL_WOOD, new FabricItemSettings());

    //

    public static final Block PRIMORDIAL_PLANKS = new Block(FabricBlockSettings
        .copyOf(Blocks.OAK_PLANKS)
        .sounds(BlockSoundGroup.WOOD));

    public static final BlockItem PRIMORDIAL_PLANKS_ITEM = new BlockItem(PRIMORDIAL_PLANKS, new FabricItemSettings());

    public static final Block PRIMORDIAL_SLAB = new SlabBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_SLAB)
        .sounds(BlockSoundGroup.WOOD));

    public static final BlockItem PRIMORDIAL_SLAB_ITEM = new BlockItem(PRIMORDIAL_SLAB, new FabricItemSettings());

    public static Block PRIMORDIAL_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings
        .copyOf(Blocks.OAK_PRESSURE_PLATE)
        .sounds(BlockSoundGroup.WOOD));

    public static final BlockItem PRIMORDIAL_PRESSURE_PLATE_ITEM = new BlockItem(PRIMORDIAL_PRESSURE_PLATE, new FabricItemSettings());

    // 

    public static final Block PRIMORDIAL_DOOR = new DoorBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_DOOR)
        .sounds(BlockSoundGroup.WOOD));

    public static final BlockItem PRIMORDIAL_DOOR_ITEM = new BlockItem(PRIMORDIAL_DOOR, new FabricItemSettings());

    public static final Block PRIMORDIAL_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_TRAPDOOR)
        .sounds(BlockSoundGroup.WOOD));

    public static final BlockItem PRIMORDIAL_TRAPDOOR_ITEM = new BlockItem(PRIMORDIAL_TRAPDOOR, new FabricItemSettings());

    public static final SignBlock PRIMORDIAL_SIGN = new SignBlock(FabricBlockSettings
        .copyOf(Blocks.OAK_SIGN)
        .sounds(BlockSoundGroup.WOOD), EldritchProperties.PRIMORDIAL_SIGN_TYPE);
    
    public static final WallSignBlock PRIMORDIAL_WALL_SIGN = new WallSignBlock(Settings.copy(PRIMORDIAL_SIGN), EldritchProperties.PRIMORDIAL_SIGN_TYPE);

    public static final BlockItem PRIMORDIAL_SIGN_ITEM = new SignItem(new Item.Settings().maxCount(16), PRIMORDIAL_SIGN, PRIMORDIAL_WALL_SIGN);

    //



    public static void register() {
        registerFullBlock("abysmal_fronds", ABYSMAL_FRONDS, ABYSMAL_FRONDS_ITEM);
        registerFullBlock("suspicious_fronds", SUSPICIOUS_FRONDS, SUSPICIOUS_FRONDS_ITEM);
        registerFullBlock("abysmal_tendrils", ABYSMAL_TENDRILS, ABYSMAL_TENDRILS_ITEM);

        // Primordial woodset
        registerFullBlock("primordial_log", PRIMORDIAL_LOG, PRIMORDIAL_LOG_ITEM);
        registerFullBlock("primordial_wood", PRIMORDIAL_WOOD, PRIMORDIAL_WOOD_ITEM);
        registerFullBlock("stripped_primordial_log", STRIPPED_PRIMORDIAL_LOG, STRIPPED_PRIMORDIAL_LOG_ITEM);
        registerFullBlock("stripped_primordial_wood", STRIPPED_PRIMORDIAL_WOOD, STRIPPED_PRIMORDIAL_WOOD_ITEM);
        registerFullBlock("primordial_planks", PRIMORDIAL_PLANKS, PRIMORDIAL_PLANKS_ITEM);
        registerFullBlock("primordial_slab", PRIMORDIAL_SLAB, PRIMORDIAL_SLAB_ITEM);
        registerFullBlock("primordial_pressure_plate", PRIMORDIAL_PRESSURE_PLATE, PRIMORDIAL_PRESSURE_PLATE_ITEM);
        registerFullBlock("primordial_door", PRIMORDIAL_DOOR, PRIMORDIAL_DOOR_ITEM);
        registerFullBlock("primordial_trapdoor", PRIMORDIAL_TRAPDOOR, PRIMORDIAL_TRAPDOOR_ITEM);
        registerSign("primordial", PRIMORDIAL_SIGN, PRIMORDIAL_WALL_SIGN);

    }

    public static void registerFullBlock(String identifier, Block blockInstance, BlockItem itemInstance) {
        Registry.register(Registry.BLOCK, new Identifier(EldritchEnd.MODID, identifier), blockInstance);
        ItemRegistry.reg(itemInstance, identifier);
    }

    public static void registerSign(String sign_name, Block SIGN, Block WALL_SIGN) {
        Registry.register(Registry.BLOCK, new Identifier(EldritchEnd.MODID, sign_name+"_sign"), SIGN);
        Registry.register(Registry.BLOCK, new Identifier(EldritchEnd.MODID, sign_name+"_wall_sign"), WALL_SIGN);
        Registry.register(Registry.ITEM, new Identifier(EldritchEnd.MODID, sign_name+"_sign"), PRIMORDIAL_SIGN_ITEM);
    }
}
