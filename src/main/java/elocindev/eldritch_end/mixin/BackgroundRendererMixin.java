package elocindev.eldritch_end.mixin;
import com.mojang.blaze3d.systems.RenderSystem;

import elocindev.eldritch_end.registry.BiomeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
	// Huge thanks to https://github.com/Steveplays28/biome-fog
	// I used it as reference to understand how fog modification works

	@Inject(method = "applyFog", at = @At("TAIL"))
	private static void eldritchFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		var world = client.world;
		Optional<RegistryKey<Biome>> biomeKey = world.getBiome(camera.getBlockPos()).getKey();
		if (world == null || world.getBiome(camera.getBlockPos()).getKey().isEmpty() || !camera.getSubmersionType().equals(CameraSubmersionType.NONE))
			return;
		

		if (biomeKey.get() == BiomeRegistry.PRIMORDIAL_ABYSS) {
			RenderSystem.setShaderFogStart(MathHelper.lerp(1.0f, vanillaFogStart(viewDistance), 0f + 0.0f));
			RenderSystem.setShaderFogEnd(MathHelper.lerp(1.0f, viewDistance, viewDistance / 3 + 0.0f));
		}
	}

	private static float vanillaFogStart(float viewDistance) {
		float f = MathHelper.clamp(64.0f, viewDistance / 10.0f, 4.0f);
		return viewDistance - f;
	}
}
