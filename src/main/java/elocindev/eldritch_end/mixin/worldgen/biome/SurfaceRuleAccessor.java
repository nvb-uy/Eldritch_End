package elocindev.eldritch_end.mixin.worldgen.biome;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

@Mixin(ChunkGeneratorSettings.class)
public interface SurfaceRuleAccessor { @Accessor("surfaceRule") @Mutable void addSurfaceRule(MaterialRules.MaterialRule ruleSource); }