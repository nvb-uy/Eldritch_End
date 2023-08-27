package elocindev.eldritch_end.block;

import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SuspiciousFronds extends AbysmalFronds {
	public SuspiciousFronds() {
		super();
	}

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        // add a boo scary tentacle popping from the floor and them BOOM player goes pheeeew to the air and then BAM to the floor and the tentacle starts attacking epicly
        if (!entity.isSneaking() && entity instanceof PlayerEntity player) {
            // Spawn a TentacleEntity
            TentacleEntity tentacle = EntityRegistry.TENTACLE.create(world);
            tentacle.setPosition(player.getPos());
            world.spawnEntity(tentacle);

            player.setVelocity(0, 0.90, 0);
            player.velocityModified = true;

            player.damage(DamageSource.mob(tentacle), 4.0f);

            world.setBlockState(pos, BlockRegistry.ABYSMAL_FRONDS.getDefaultState());
        }
    }
}
