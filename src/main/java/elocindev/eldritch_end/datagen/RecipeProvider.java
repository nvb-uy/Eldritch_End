package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        generateStairsRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.PRIMORDIAL_PLANKS, BlockRegistry.PRIMORDIAL_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.SPIRE_STONE, BlockRegistry.SPIRE_STONE_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.POLISHED_SPIRE_STONE_TILES, BlockRegistry.POLISHED_SPIRE_STONE_TILE_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.POLISHED_SPIRE_STONE_BRICKS, BlockRegistry.POLISHED_SPIRE_STONE_BRICK_STAIRS, exporter);
        generateStairsRecipe(BlockRegistry.POLISHED_SPIRE_STONE, BlockRegistry.POLISHED_SPIRE_STONE_STAIRS, exporter);

        generateSlabRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.PRIMORDIAL_PLANKS, BlockRegistry.PRIMORDIAL_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.SPIRE_STONE, BlockRegistry.SPIRE_STONE_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.POLISHED_SPIRE_STONE_TILES, BlockRegistry.POLISHED_SPIRE_STONE_TILE_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.POLISHED_SPIRE_STONE_BRICKS, BlockRegistry.POLISHED_SPIRE_STONE_BRICK_SLAB, exporter);
        generateSlabRecipe(BlockRegistry.POLISHED_SPIRE_STONE, BlockRegistry.POLISHED_SPIRE_STONE_SLAB, exporter);

        generatePillarRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_PILLAR, exporter);
        generatePillarRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_PILLAR, exporter);
        generatePillarRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_PILLAR, exporter);
        generatePillarRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_PILLAR, exporter);
        generatePillarRecipe(BlockRegistry.POLISHED_SPIRE_STONE, BlockRegistry.POLISHED_SPIRE_STONE_PILLAR, exporter);

        generateTrapdoorRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_TRAPDOOR, exporter);
        generateTrapdoorRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_TRAPDOOR, exporter);
        generateTrapdoorRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_TRAPDOOR, exporter);
        generateTrapdoorRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_TRAPDOOR, exporter);
        generateTrapdoorRecipe(BlockRegistry.PRIMORDIAL_PLANKS, BlockRegistry.PRIMORDIAL_TRAPDOOR, exporter);

        generateDoorRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_DOOR, exporter);
        generateDoorRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_DOOR, exporter);
        generateDoorRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_DOOR, exporter);
        generateDoorRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_DOOR, exporter);
        generateDoorRecipe(BlockRegistry.PRIMORDIAL_PLANKS, BlockRegistry.PRIMORDIAL_DOOR, exporter);

        generateEtyrBarsRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_BARS, exporter);
        generateEtyrBarsRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_BARS, exporter);
        generateEtyrBarsRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_BARS, exporter);
        generateEtyrBarsRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_BARS, exporter);

        generateTileRecipe(BlockRegistry.ETYR_BLOCK, BlockRegistry.ETYR_TILES, exporter);
        generateTileRecipe(BlockRegistry.DECADENT_ETYR_BLOCK, BlockRegistry.DECADENT_ETYR_TILES, exporter);
        generateTileRecipe(BlockRegistry.PERTURBED_ETYR_BLOCK, BlockRegistry.PERTURBED_ETYR_TILES, exporter);
        generateTileRecipe(BlockRegistry.CORRUPTED_ETYR_BLOCK, BlockRegistry.CORRUPTED_ETYR_TILES, exporter);
        generateTileRecipe(BlockRegistry.POLISHED_SPIRE_STONE, BlockRegistry.POLISHED_SPIRE_STONE_TILES, exporter);
        generateTileRecipe(BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_BRICKS, BlockRegistry.CRACKED_POLISHED_SPIRE_STONE_TILES, exporter);
    }

    private void generateStairsRecipe(Block inputBlock, Block stairBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stairBlock).pattern("#  ").pattern("## ").pattern("###")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }

    private void generateSlabRecipe(Block inputBlock, Block slabBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, slabBlock).pattern("###")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }

    private void generatePillarRecipe(Block inputBlock, Block pillarBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, pillarBlock).pattern("#").pattern("#")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }

    private void generateTrapdoorRecipe(Block inputBlock, Block trapdoorBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, trapdoorBlock).pattern("###").pattern("###")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }

    private void generateDoorRecipe(Block inputBlock, Block doorBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, doorBlock).pattern("##").pattern("##").pattern("##")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }

    private void generateEtyrBarsRecipe(Block inputBlock, Block barBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, barBlock).pattern("-#-").pattern("#-#")
                .input('#', inputBlock)
                .input('-', ItemRegistry.ETYR_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ItemRegistry.ETYR_INGOT),
                        FabricRecipeProvider.conditionsFromItem(ItemRegistry.ETYR_INGOT))
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }

    private void generateTileRecipe(Block inputBlock, Block tileBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, tileBlock).pattern("##").pattern("##")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }
}
