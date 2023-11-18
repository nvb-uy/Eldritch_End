package elocindev.eldritch_end.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.eldritch_end.mixin.worldgen.biome.SurfaceRuleAccessor;
import elocindev.eldritch_end.registry.BiomeRegistry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

@Mixin(MinecraftServer.class)
public abstract class BiomeMixin {
    @Inject(method = "createWorlds", at = @At("RETURN"))
    private void eldritch_end$createWorlds(CallbackInfo ci) {
        DimensionOptions dimensionOption = ((MinecraftServer)(Object)this).getCombinedDynamicRegistries().getCombinedRegistryManager().get(RegistryKeys.DIMENSION).get(DimensionOptions.END);
        if (dimensionOption == null) return;
        
        ChunkGenerator generator = dimensionOption.chunkGenerator();

        if (generator instanceof NoiseChunkGenerator noiseGenerator) {
            ChunkGeneratorSettings settings = noiseGenerator.getSettings().value();

            if (generator.getBiomeSource().getBiomes().stream().anyMatch(
            biome -> biome.getKey().orElseThrow().equals(BiomeRegistry.HASTURIAN_WASTES)))
                ((SurfaceRuleAccessor)(Object)settings).addSurfaceRule(
                        MaterialRules.sequence(BiomeRegistry.SurfaceRules.createHasturianWastes(), settings.surfaceRule())
                );

            if (generator.getBiomeSource().getBiomes().stream().anyMatch(
            biome -> biome.getKey().orElseThrow().equals(BiomeRegistry.PRIMORDIAL_ABYSS)))
                ((SurfaceRuleAccessor)(Object)settings).addSurfaceRule(
                        MaterialRules.sequence(BiomeRegistry.SurfaceRules.createPrimordialAbyss(), settings.surfaceRule())
                );
        }
    }
}

