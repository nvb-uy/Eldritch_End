package elocindev.eldritch_end.entity.dendler;

import elocindev.eldritch_end.entity.EntityAnimations;
import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehaviors;
import elocindev.eldritch_end.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class DendlerEntity extends HostileEntity {
 
    public DendlerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new WanderAroundFarGoal(this, Configs.Entity.DENDLER.WANDER_SPEED));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.Entity.DENDLER.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, Configs.Entity.DENDLER.MOVEMENT_SPEED_ATTRIBUTE);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }

    private String lastAnimation = "";

    private void tickAnimations() {
        if (!this.getWorld().isClient) {
            return;
        }
        String animation = this.isOnGround() && EntityAnimations.isMovingHorizontally(this) ? "walk" : "idle";
        lastAnimation = EntityAnimations.sendIfChanged(this, lastAnimation, animation,
                AzCommand.create(EntityAnimations.BASE_CONTROLLER, animation, AzPlayBehaviors.LOOP));
    }

    @Override
    public void tick() {
        super.tick();
        this.tickAnimations();
    }
}
