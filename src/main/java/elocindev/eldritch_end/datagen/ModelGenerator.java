package elocindev.eldritch_end.datagen;

import com.google.gson.JsonElement;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(BlockRegistry.PRIMORDIAL_PLANKS);
        generator.registerDoor(BlockRegistry.PRIMORDIAL_DOOR);
        generator.registerTrapdoor(BlockRegistry.PRIMORDIAL_TRAPDOOR);

        registerFronds(BlockRegistry.ABYSMAL_FRONDS, generator.modelCollector, generator.blockStateCollector);
        registerSlab(BlockRegistry.PRIMORDIAL_SLAB, BlockRegistry.PRIMORDIAL_PLANKS, generator.modelCollector, generator.blockStateCollector);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ItemRegistry.SILVER_KEY, Models.GENERATED);
        generator.register(ItemRegistry.NECRONOMICON, Models.GENERATED);
    }

    /** Takes in the slab block as well as a plank block which acts as the texture. */
    private void registerSlab(Block slabBlock, Block plankBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> blockStateCollector) {
        TextureMap textureMap = TextureMap.all(plankBlock);
        TextureMap textureMap2 = TextureMap.sideEnd(TextureMap.getSubId(plankBlock, ""), textureMap.getTexture(TextureKey.TOP));

        Identifier identifier = Models.SLAB.upload(slabBlock, textureMap2, modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(slabBlock, textureMap2, modelCollector);
        Identifier identifier3 = Models.CUBE_COLUMN.uploadWithoutVariant(slabBlock, "_double", textureMap2, modelCollector);

        blockStateCollector.accept(createSlabBlockState(slabBlock, identifier, identifier2, identifier3));
    }

    public final void registerFronds(Block block, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> blockStateCollector) {
        TextureMap textureMap = (new TextureMap()).put(TextureKey.BOTTOM, TextureMap.getId(Blocks.END_STONE)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.SIDE, TextureMap.getSubId(block, ""));
        blockStateCollector.accept(createSingletonBlockState(block, Models.CUBE_BOTTOM_TOP.upload(block, textureMap, modelCollector)));
    }

    public static VariantsBlockStateSupplier createSingletonBlockState(Block block, Identifier modelId) {
        return VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, modelId));
    }

    public static BlockStateSupplier createSlabBlockState(Block slabBlock, Identifier bottomModelId, Identifier topModelId, Identifier fullModelId) {
        return VariantsBlockStateSupplier.create(slabBlock).coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE).register(SlabType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, bottomModelId)).register(SlabType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, topModelId)).register(SlabType.DOUBLE, BlockStateVariant.create().put(VariantSettings.MODEL, fullModelId)));
    }
}
