package elocindev.eldritch_end.entity.ominous_eye;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class OminousEyeEntity extends VexEntity {
    public OminousEyeEntity(EntityType<? extends VexEntity> entityType, World world) {
        super(entityType, world);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(4, new FlyGoal(this, 1.0));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 15.0f, 1.0f));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }
}
