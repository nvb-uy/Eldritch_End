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
        if (!world.isClient) {
            int steps = 0;
            while (!entity.isSneaking()) {
                for (steps = 0; steps < 10; steps++) {
                    entity.teleport(entity.getX(), entity.getY() - 0.2f, entity.getZ());
                }
                break;
            }
        }
    }
}
