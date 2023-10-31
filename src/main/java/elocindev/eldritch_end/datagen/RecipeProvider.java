package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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
    }

    private void generateStairsRecipe(Block inputBlock, Block stairBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stairBlock).pattern("#  ").pattern("## ").pattern("###")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }
}
