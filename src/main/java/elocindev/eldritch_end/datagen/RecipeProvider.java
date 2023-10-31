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
        generateStairsRecipe(BlockRegistry.ABYSMAL_FRONDS, BlockRegistry.ETYR_STAIRS, exporter);
    }

    private void generateStairsRecipe(Block inputBlock, Block stairBlock, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stairBlock).pattern("#  ").pattern("## ").pattern("###")
                .input('#', inputBlock)
                .criterion(FabricRecipeProvider.hasItem(inputBlock),
                        FabricRecipeProvider.conditionsFromItem(inputBlock))
                .offerTo(exporter);
    }
}
