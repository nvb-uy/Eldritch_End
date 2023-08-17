package elocindev.eldritch_end.datagen;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TagGenerator extends FabricTagProvider.BlockTagProvider {
    public TagGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    private static final TagKey<Block> PRIMORDIAL_LOGS = TagKey.of(Registry.BLOCK_KEY, new Identifier(EldritchEnd.MODID, "primordial_logs"));

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(PRIMORDIAL_LOGS)
                .add(BlockRegistry.PRIMORDIAL_LOG);
    }
}
