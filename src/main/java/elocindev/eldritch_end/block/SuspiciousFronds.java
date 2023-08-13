package elocindev.eldritch_end.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SuspiciousFronds extends AbysmalFronds {
	public SuspiciousFronds() {
		super();
	}

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        // add a boo scary tentacle popping from the floor and them BOOM player goes pheeeew to the air and then BAM to the floor and the tentacle starts attacking epicly
    }
}
