package elocindev.eldritch_end;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.config.ConfigBuilder;
import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.FeatureRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;

public class EldritchEnd implements ModInitializer {
	public static final String MODID = "eldritch_end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	
	private static boolean CFG_STARTED = ConfigBuilder.hasStarted(); 
	public static PrimordialAbyssConfig BIOME_PRIMORDIAL_CFG = ConfigBuilder.loadPrimordialAbyss();

	public static final ItemGroup EldritchEnd = FabricItemGroupBuilder.create(
		new Identifier(MODID, "tab"))
		.icon(() -> new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM))
		.appendItems(stacks -> { 
			stacks.add(new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM)); 
			stacks.add(new ItemStack(BlockRegistry.SUSPICIOUS_FRONDS_ITEM));
			stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_LOG_ITEM));
		})
		.build();

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success)
		->  {
			if (CFG_STARTED) {
				BIOME_PRIMORDIAL_CFG = ConfigBuilder.loadPrimordialAbyss();
			}
			
		});
		LOGGER.info("Eldritch End's Config Loaded!");

		FeatureRegistry.register();
		BiomeRegistry.register();
		BlockRegistry.register();
		ItemRegistry.register();
	}
}
