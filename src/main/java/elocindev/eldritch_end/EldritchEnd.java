package elocindev.eldritch_end;

import elocindev.eldritch_end.registry.EntityRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.eldritch_end.config.ConfigBuilder;
import elocindev.eldritch_end.config.ConfigLoader;
import elocindev.eldritch_end.registry.BiomeRegistry;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.FeatureRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;

public class EldritchEnd implements ModInitializer {
	public static final String MODID = "eldritch_end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	private static boolean config = ConfigBuilder.hasStarted();

	public static final ItemGroup EldritchEnd = FabricItemGroupBuilder.create(
		new Identifier(MODID, "tab"))
		.icon(() -> new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM))
		.appendItems(stacks -> { 
			stacks.add(new ItemStack(BlockRegistry.ABYSMAL_FRONDS_ITEM)); 
			stacks.add(new ItemStack(BlockRegistry.SUSPICIOUS_FRONDS_ITEM));
			stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_LOG_ITEM));
			
			stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_PLANKS_ITEM));
			stacks.add(new ItemStack(BlockRegistry.PRIMORDIAL_SIGN_ITEM));
		})
		.build();

	@Override
	public void onInitialize() {
		ConfigLoader.init(config);

		GeckoLib.initialize();
		EntityRegistry.register();
		FeatureRegistry.register();
		BiomeRegistry.register();
		BlockRegistry.register();
		ItemRegistry.register();
	}
}
