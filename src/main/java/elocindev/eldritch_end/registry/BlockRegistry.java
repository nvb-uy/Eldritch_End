package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.block.AbysmalFronds;
import elocindev.eldritch_end.block.AbysmalRoots;
import elocindev.eldritch_end.block.AbysmalTendrils;
import elocindev.eldritch_end.block.HasturianGrass;
import elocindev.eldritch_end.block.HasturianMoss;
import elocindev.eldritch_end.block.SuspiciousFronds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
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

    public static final Block ABYSMAL_ROOTS = new AbysmalRoots(FabricBlockSettings.copyOf(Blocks.GRASS).sounds(BlockSoundGroup.ROOTS));

    public static final BlockItem ABYSMAL_ROOTS_ITEM = new BlockItem(ABYSMAL_ROOTS, new FabricItemSettings());

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


    public static final StairsBlock PRIMORDIAL_STAIRS = new StairsBlock(PRIMORDIAL_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS));
    public static final BlockItem PRIMORDIAL_STAIRS_ITEM = new BlockItem(PRIMORDIAL_STAIRS, new FabricItemSettings());

    public static final WoodenButtonBlock PRIMORDIAL_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_STAIRS));
    public static final BlockItem PRIMORDIAL_BUTTON_ITEM = new BlockItem(PRIMORDIAL_BUTTON, new FabricItemSettings());

    public static final Block HASTURIAN_MOSS = new HasturianMoss();
    public static final BlockItem HASTURIAN_MOSS_ITEM = new BlockItem(HASTURIAN_MOSS, new FabricItemSettings());

    public static final Block SPIRE_STONE = new Block(FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.STONE));
    public static final BlockItem SPIRE_STONE_ITEM = new BlockItem(SPIRE_STONE, new FabricItemSettings());

    public static final Block PRIMORDIAL_FENCE = new FenceBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD));
    public static final BlockItem PRIMORDIAL_FENCE_ITEM = new BlockItem(PRIMORDIAL_FENCE, new FabricItemSettings());

    public static final Block PRIMORDIAL_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD));
    public static final BlockItem PRIMORDIAL_FENCE_GATE_ITEM = new BlockItem(PRIMORDIAL_FENCE_GATE, new FabricItemSettings());

    public static final Block HASTURIAN_DUNE_SAND = new FallingBlock(FabricBlockSettings.copyOf(Blocks.SAND));
    public static final BlockItem HASTURIAN_DUNE_SAND_ITEM = new BlockItem(HASTURIAN_DUNE_SAND, new FabricItemSettings());

    public static final Block HASTURIAN_SAND = new FallingBlock(FabricBlockSettings.copyOf(Blocks.SAND));
    public static final BlockItem HASTURIAN_SAND_ITEM = new BlockItem(HASTURIAN_SAND, new FabricItemSettings());

    public static final Block HASTURIAN_CACTUS = new CactusBlock(FabricBlockSettings.copyOf(Blocks.CACTUS));
    public static final BlockItem HASTURIAN_CACTUS_ITEM = new BlockItem(HASTURIAN_CACTUS, new FabricItemSettings());

    public static final Block HASTURIAN_GRASS = new HasturianGrass(FabricBlockSettings.copyOf(BlockRegistry.ABYSMAL_ROOTS).sounds(BlockSoundGroup.ROOTS));
    public static final BlockItem HASTURIAN_GRASS_ITEM = new BlockItem(HASTURIAN_GRASS, new FabricItemSettings());

    // Default etyr
    public static final Block ETYR_ORE = new OreBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
    public static final BlockItem ETYR_ORE_ITEM = new BlockItem(ETYR_ORE, new FabricItemSettings());

    public static final Block ETYR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem ETYR_BLOCK_ITEM = new BlockItem(ETYR_BLOCK, new FabricItemSettings());

    public static final Block ETYR_BARS = new PaneBlock(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final BlockItem ETYR_BARS_ITEM = new BlockItem(ETYR_BARS, new FabricItemSettings());

    public static final Block ETYR_DOOR = new DoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_DOOR)
            .sounds(BlockSoundGroup.METAL));
    public static final BlockItem ETYR_DOOR_ITEM = new BlockItem(ETYR_DOOR, new FabricItemSettings());

    public static final Block ETYR_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR));
    public static final BlockItem ETYR_PILLAR_ITEM = new BlockItem(ETYR_PILLAR, new FabricItemSettings());

    public static final Block ETYR_TILES = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem ETYR_TILES_ITEM = new BlockItem(ETYR_TILES, new FabricItemSettings());

    public static final Block ETYR_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_TRAPDOOR)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem ETYR_TRAPDOOR_ITEM = new BlockItem(ETYR_TRAPDOOR, new FabricItemSettings());

    public static final Block ETYR_SLAB = new SlabBlock(FabricBlockSettings
            .copyOf(Blocks.STONE_SLAB)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem ETYR_SLAB_ITEM = new BlockItem(ETYR_SLAB, new FabricItemSettings());

    public static final StairsBlock ETYR_STAIRS = new StairsBlock(ETYR_TILES.getDefaultState(), FabricBlockSettings.copyOf(Blocks.STONE_STAIRS));
    public static final BlockItem ETYR_STAIRS_ITEM = new BlockItem(ETYR_STAIRS, new FabricItemSettings());

    // Etyr variant #1
    public static final Block DECADENT_ETYR_ORE = new OreBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
    public static final BlockItem DECADENT_ETYR_ORE_ITEM = new BlockItem(DECADENT_ETYR_ORE, new FabricItemSettings());

    public static final Block DECADENT_ETYR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem DECADENT_ETYR_BLOCK_ITEM = new BlockItem(DECADENT_ETYR_BLOCK, new FabricItemSettings());

    public static final Block DECADENT_ETYR_BARS = new PaneBlock(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final BlockItem DECADENT_ETYR_BARS_ITEM = new BlockItem(DECADENT_ETYR_BARS, new FabricItemSettings());

    public static final Block DECADENT_ETYR_DOOR = new DoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_DOOR)
            .sounds(BlockSoundGroup.METAL));
    public static final BlockItem DECADENT_ETYR_DOOR_ITEM = new BlockItem(DECADENT_ETYR_DOOR, new FabricItemSettings());

    public static final Block DECADENT_ETYR_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR));
    public static final BlockItem DECADENT_ETYR_PILLAR_ITEM = new BlockItem(DECADENT_ETYR_PILLAR, new FabricItemSettings());

    public static final Block DECADENT_ETYR_TILES = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem DECADENT_ETYR_TILES_ITEM = new BlockItem(DECADENT_ETYR_TILES, new FabricItemSettings());

    public static final Block DECADENT_ETYR_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_TRAPDOOR)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem DECADENT_ETYR_TRAPDOOR_ITEM = new BlockItem(DECADENT_ETYR_TRAPDOOR, new FabricItemSettings());

    public static final Block DECADENT_ETYR_SLAB = new SlabBlock(FabricBlockSettings
            .copyOf(Blocks.STONE_SLAB)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem DECADENT_ETYR_SLAB_ITEM = new BlockItem(DECADENT_ETYR_SLAB, new FabricItemSettings());

    public static final StairsBlock DECADENT_ETYR_STAIRS = new StairsBlock(DECADENT_ETYR_TILES.getDefaultState(), FabricBlockSettings.copyOf(Blocks.STONE_STAIRS));
    public static final BlockItem DECADENT_ETYR_STAIRS_ITEM = new BlockItem(DECADENT_ETYR_STAIRS, new FabricItemSettings());

    // Etyr variant #2
    public static final Block PERTURBATED_ETYR_ORE = new OreBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
    public static final BlockItem PERTURBATED_ETYR_ORE_ITEM = new BlockItem(PERTURBATED_ETYR_ORE, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem PERTURBATED_ETYR_BLOCK_ITEM = new BlockItem(PERTURBATED_ETYR_BLOCK, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_BARS = new PaneBlock(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final BlockItem PERTURBATED_ETYR_BARS_ITEM = new BlockItem(PERTURBATED_ETYR_BARS, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_DOOR = new DoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_DOOR)
            .sounds(BlockSoundGroup.METAL));
    public static final BlockItem PERTURBATED_ETYR_DOOR_ITEM = new BlockItem(PERTURBATED_ETYR_DOOR, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR));
    public static final BlockItem PERTURBATED_ETYR_PILLAR_ITEM = new BlockItem(PERTURBATED_ETYR_PILLAR, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_TILES = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem PERTURBATED_ETYR_TILES_ITEM = new BlockItem(PERTURBATED_ETYR_TILES, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_TRAPDOOR)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem PERTURBATED_ETYR_TRAPDOOR_ITEM = new BlockItem(PERTURBATED_ETYR_TRAPDOOR, new FabricItemSettings());

    public static final Block PERTURBATED_ETYR_SLAB = new SlabBlock(FabricBlockSettings
            .copyOf(Blocks.STONE_SLAB)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem PERTURBATED_ETYR_SLAB_ITEM = new BlockItem(PERTURBATED_ETYR_SLAB, new FabricItemSettings());

    public static final StairsBlock PERTURBATED_ETYR_STAIRS = new StairsBlock(PERTURBATED_ETYR_TILES.getDefaultState(), FabricBlockSettings.copyOf(Blocks.STONE_STAIRS));
    public static final BlockItem PERTURBATED_ETYR_STAIRS_ITEM = new BlockItem(PERTURBATED_ETYR_STAIRS, new FabricItemSettings());

    // Etyr variant #3
    public static final Block CORRUPTED_ETYR_ORE = new OreBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
    public static final BlockItem CORRUPTED_ETYR_ORE_ITEM = new BlockItem(CORRUPTED_ETYR_ORE, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem CORRUPTED_ETYR_BLOCK_ITEM = new BlockItem(CORRUPTED_ETYR_BLOCK, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_BARS = new PaneBlock(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final BlockItem CORRUPTED_ETYR_BARS_ITEM = new BlockItem(CORRUPTED_ETYR_BARS, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_DOOR = new DoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_DOOR)
            .sounds(BlockSoundGroup.METAL));
    public static final BlockItem CORRUPTED_ETYR_DOOR_ITEM = new BlockItem(CORRUPTED_ETYR_DOOR, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR));
    public static final BlockItem CORRUPTED_ETYR_PILLAR_ITEM = new BlockItem(CORRUPTED_ETYR_PILLAR, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_TILES = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final BlockItem CORRUPTED_ETYR_TILES_ITEM = new BlockItem(CORRUPTED_ETYR_TILES, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings
            .copyOf(Blocks.IRON_TRAPDOOR)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem CORRUPTED_ETYR_TRAPDOOR_ITEM = new BlockItem(CORRUPTED_ETYR_TRAPDOOR, new FabricItemSettings());

    public static final Block CORRUPTED_ETYR_SLAB = new SlabBlock(FabricBlockSettings
            .copyOf(Blocks.STONE_SLAB)
            .sounds(BlockSoundGroup.METAL));

    public static final BlockItem CORRUPTED_ETYR_SLAB_ITEM = new BlockItem(CORRUPTED_ETYR_SLAB, new FabricItemSettings());

    public static final StairsBlock CORRUPTED_ETYR_STAIRS = new StairsBlock(CORRUPTED_ETYR_TILES.getDefaultState(), FabricBlockSettings.copyOf(Blocks.STONE_STAIRS));
    public static final BlockItem CORRUPTED_ETYR_STAIRS_ITEM = new BlockItem(CORRUPTED_ETYR_STAIRS, new FabricItemSettings());



    //
    public static void register() {
        registerFullBlock("abysmal_fronds", ABYSMAL_FRONDS, ABYSMAL_FRONDS_ITEM);
        registerFullBlock("suspicious_fronds", SUSPICIOUS_FRONDS, SUSPICIOUS_FRONDS_ITEM);
        registerFullBlock("abysmal_tendrils", ABYSMAL_TENDRILS, ABYSMAL_TENDRILS_ITEM);
        registerFullBlock("abysmal_roots", ABYSMAL_ROOTS, ABYSMAL_ROOTS_ITEM);
        registerFullBlock("primordial_button", PRIMORDIAL_BUTTON, PRIMORDIAL_BUTTON_ITEM);

        // Primordial woodset
        registerFullBlock("primordial_log", PRIMORDIAL_LOG, PRIMORDIAL_LOG_ITEM);
        registerFullBlock("primordial_wood", PRIMORDIAL_WOOD, PRIMORDIAL_WOOD_ITEM);
        registerFullBlock("stripped_primordial_log", STRIPPED_PRIMORDIAL_LOG, STRIPPED_PRIMORDIAL_LOG_ITEM);
        registerFullBlock("stripped_primordial_wood", STRIPPED_PRIMORDIAL_WOOD, STRIPPED_PRIMORDIAL_WOOD_ITEM);
        registerFullBlock("primordial_planks", PRIMORDIAL_PLANKS, PRIMORDIAL_PLANKS_ITEM);
        registerFullBlock("primordial_stairs", PRIMORDIAL_STAIRS, PRIMORDIAL_STAIRS_ITEM);
        registerFullBlock("primordial_slab", PRIMORDIAL_SLAB, PRIMORDIAL_SLAB_ITEM);
        registerFullBlock("primordial_pressure_plate", PRIMORDIAL_PRESSURE_PLATE, PRIMORDIAL_PRESSURE_PLATE_ITEM);
        registerFullBlock("primordial_door", PRIMORDIAL_DOOR, PRIMORDIAL_DOOR_ITEM);
        registerFullBlock("primordial_trapdoor", PRIMORDIAL_TRAPDOOR, PRIMORDIAL_TRAPDOOR_ITEM);
        registerFullBlock("primordial_fence", PRIMORDIAL_FENCE, PRIMORDIAL_FENCE_ITEM);
        registerFullBlock("primordial_fence_gate", PRIMORDIAL_FENCE_GATE, PRIMORDIAL_FENCE_GATE_ITEM);
        registerSign("primordial", PRIMORDIAL_SIGN, PRIMORDIAL_WALL_SIGN);
    
        registerFullBlock("hasturian_moss", HASTURIAN_MOSS, HASTURIAN_MOSS_ITEM);
        registerFullBlock("spire_stone", SPIRE_STONE, SPIRE_STONE_ITEM);

        registerFullBlock("etyr_ore", ETYR_ORE, ETYR_ORE_ITEM);
        registerFullBlock("etyr_block", ETYR_BLOCK, ETYR_BLOCK_ITEM);
        registerFullBlock("etyr_bars", ETYR_BARS, ETYR_BARS_ITEM);
        registerFullBlock("etyr_door", ETYR_DOOR, ETYR_DOOR_ITEM);
        registerFullBlock("etyr_pillar", ETYR_PILLAR, ETYR_PILLAR_ITEM);
        registerFullBlock("etyr_tiles", ETYR_TILES, ETYR_TILES_ITEM);
        registerFullBlock("etyr_trapdoor", ETYR_TRAPDOOR, ETYR_TRAPDOOR_ITEM);
        registerFullBlock("etyr_stairs", ETYR_STAIRS, ETYR_STAIRS_ITEM);
        registerFullBlock("etyr_slab", ETYR_SLAB, ETYR_SLAB_ITEM);

        registerFullBlock("decadent_etyr_ore", DECADENT_ETYR_ORE, DECADENT_ETYR_ORE_ITEM);
        registerFullBlock("decadent_etyr_block", DECADENT_ETYR_BLOCK, DECADENT_ETYR_BLOCK_ITEM);
        registerFullBlock("decadent_etyr_bars", DECADENT_ETYR_BARS, DECADENT_ETYR_BARS_ITEM);
        registerFullBlock("decadent_etyr_door", DECADENT_ETYR_DOOR, DECADENT_ETYR_DOOR_ITEM);
        registerFullBlock("decadent_etyr_pillar", DECADENT_ETYR_PILLAR, DECADENT_ETYR_PILLAR_ITEM);
        registerFullBlock("decadent_etyr_tiles", DECADENT_ETYR_TILES, DECADENT_ETYR_TILES_ITEM);
        registerFullBlock("decadent_etyr_trapdoor", DECADENT_ETYR_TRAPDOOR, DECADENT_ETYR_TRAPDOOR_ITEM);
        registerFullBlock("decadent_etyr_stairs", DECADENT_ETYR_STAIRS, DECADENT_ETYR_STAIRS_ITEM);
        registerFullBlock("decadent_etyr_slab", DECADENT_ETYR_SLAB, DECADENT_ETYR_SLAB_ITEM);

        registerFullBlock("perturbated_etyr_ore", PERTURBATED_ETYR_ORE, PERTURBATED_ETYR_ORE_ITEM);
        registerFullBlock("perturbated_etyr_block", PERTURBATED_ETYR_BLOCK, PERTURBATED_ETYR_BLOCK_ITEM);
        registerFullBlock("perturbated_etyr_bars", PERTURBATED_ETYR_BARS, PERTURBATED_ETYR_BARS_ITEM);
        registerFullBlock("perturbated_etyr_door", PERTURBATED_ETYR_DOOR, PERTURBATED_ETYR_DOOR_ITEM);
        registerFullBlock("perturbated_etyr_pillar", PERTURBATED_ETYR_PILLAR, PERTURBATED_ETYR_PILLAR_ITEM);
        registerFullBlock("perturbated_etyr_tiles", PERTURBATED_ETYR_TILES, PERTURBATED_ETYR_TILES_ITEM);
        registerFullBlock("perturbated_etyr_trapdoor", PERTURBATED_ETYR_TRAPDOOR, PERTURBATED_ETYR_TRAPDOOR_ITEM);
        registerFullBlock("perturbated_etyr_stairs", PERTURBATED_ETYR_STAIRS, PERTURBATED_ETYR_STAIRS_ITEM);
        registerFullBlock("perturbated_etyr_slab", PERTURBATED_ETYR_SLAB, PERTURBATED_ETYR_SLAB_ITEM);

        registerFullBlock("corrupted_etyr_ore", CORRUPTED_ETYR_ORE, CORRUPTED_ETYR_ORE_ITEM);
        registerFullBlock("corrupted_etyr_block", CORRUPTED_ETYR_BLOCK, CORRUPTED_ETYR_BLOCK_ITEM);
        registerFullBlock("corrupted_etyr_bars", CORRUPTED_ETYR_BARS, CORRUPTED_ETYR_BARS_ITEM);
        registerFullBlock("corrupted_etyr_door", CORRUPTED_ETYR_DOOR, CORRUPTED_ETYR_DOOR_ITEM);
        registerFullBlock("corrupted_etyr_pillar", CORRUPTED_ETYR_PILLAR, CORRUPTED_ETYR_PILLAR_ITEM);
        registerFullBlock("corrupted_etyr_tiles", CORRUPTED_ETYR_TILES, CORRUPTED_ETYR_TILES_ITEM);
        registerFullBlock("corrupted_etyr_trapdoor", CORRUPTED_ETYR_TRAPDOOR, CORRUPTED_ETYR_TRAPDOOR_ITEM);
        registerFullBlock("corrupted_etyr_stairs", CORRUPTED_ETYR_STAIRS, CORRUPTED_ETYR_STAIRS_ITEM);
        registerFullBlock("corrupted_etyr_slab", CORRUPTED_ETYR_SLAB, CORRUPTED_ETYR_SLAB_ITEM);

        registerFullBlock("hasturian_sand", HASTURIAN_SAND, HASTURIAN_SAND_ITEM);
        registerFullBlock("hasturian_dune_sand", HASTURIAN_DUNE_SAND, HASTURIAN_DUNE_SAND_ITEM);
        registerFullBlock("hasturian_cactus", HASTURIAN_CACTUS, HASTURIAN_CACTUS_ITEM);
        registerFullBlock("hasturian_grass", HASTURIAN_GRASS, HASTURIAN_GRASS_ITEM);
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
