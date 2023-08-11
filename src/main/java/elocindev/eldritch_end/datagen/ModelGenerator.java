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

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.PRIMORDIAL_PLANKS);
        blockStateModelGenerator.registerDoor(BlockRegistry.PRIMORDIAL_DOOR);
        registerPrimordialSlab(blockStateModelGenerator.modelCollector, blockStateModelGenerator.blockStateCollector);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistry.SILVER_KEY, Models.GENERATED);
    }

    private void registerPrimordialSlab(BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> blockStateCollector) {
        TextureMap textureMap = TextureMap.all(BlockRegistry.PRIMORDIAL_PLANKS);
        TextureMap textureMap2 = TextureMap.sideEnd(TextureMap.getSubId(BlockRegistry.PRIMORDIAL_PLANKS, ""), textureMap.getTexture(TextureKey.TOP));

        Identifier identifier = Models.SLAB.upload(BlockRegistry.PRIMORDIAL_SLAB, textureMap2, modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(BlockRegistry.PRIMORDIAL_SLAB, textureMap2, modelCollector);
        Identifier identifier3 = Models.CUBE_COLUMN.uploadWithoutVariant(BlockRegistry.PRIMORDIAL_SLAB, "_double", textureMap2, modelCollector);

        blockStateCollector.accept(createSlabBlockState(BlockRegistry.PRIMORDIAL_SLAB, identifier, identifier2, identifier3));
    }

    public static BlockStateSupplier createSlabBlockState(Block slabBlock, Identifier bottomModelId, Identifier topModelId, Identifier fullModelId) {
        return VariantsBlockStateSupplier.create(slabBlock).coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE).register(SlabType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, bottomModelId)).register(SlabType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, topModelId)).register(SlabType.DOUBLE, BlockStateVariant.create().put(VariantSettings.MODEL, fullModelId)));
    }
}
