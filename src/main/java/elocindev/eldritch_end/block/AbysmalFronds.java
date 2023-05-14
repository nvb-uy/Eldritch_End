package elocindev.eldritch_end.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public class AbysmalFronds extends Block {
	public AbysmalFronds() {
		super(FabricBlockSettings
        .copyOf(Blocks.END_STONE)
        .sounds(BlockSoundGroup.NYLIUM)
        );
	}
    
}
