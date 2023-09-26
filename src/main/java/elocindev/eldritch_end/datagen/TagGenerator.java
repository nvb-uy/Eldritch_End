package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider.BlockTagProvider {
    public TagGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(dataOutput, registriesFuture);
    }

    private static final TagKey<Block> PRIMORDIAL_LOGS = TagKey.of(RegistryKeys.BLOCK, new Identifier(EldritchEnd.MODID, "primordial_logs"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(PRIMORDIAL_LOGS)
                .add(BlockRegistry.PRIMORDIAL_LOG);
    }
}
