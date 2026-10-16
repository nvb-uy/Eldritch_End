package elocindev.eldritch_end.entity.aberration;

import elocindev.eldritch_end.entity.EntityAnimations;
import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehaviors;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.corruption.CorruptionUtils;
import elocindev.eldritch_end.effects.Corruption;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EffectRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class AberrationEntity extends HostileEntity {

    public AberrationEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, Configs.Entity.ABERRATION.CHASE_SPEED, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, Configs.Entity.ABERRATION.WANDER_SPEED));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.age % 20 == 0 && this.getWorld().getBlockState(new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ())).getBlock() == BlockRegistry.ABYSMAL_FRONDS) {
            this.heal(this.getMaxHealth() / 10);
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.handSwinging = true;

        if (target instanceof PlayerEntity victim) {
            StatusEffect effect = EffectRegistry.CORRUPTION;

            if (victim.hasStatusEffect(effect)) {
                victim.addStatusEffect(new StatusEffectInstance(effect, victim.getStatusEffect(effect).getDuration(), victim.getStatusEffect(effect).getAmplifier() + 1, false, false));
                
                victim.damage(Corruption.of(this.getWorld(), Corruption.DAMAGE), CorruptionUtils.getDamageAmount(victim, (float) Configs.Entity.ABERRATION.ATTACK_DAMAGE_ATTRIBUTE * 0.25f, true));
            } else
                victim.addStatusEffect(new StatusEffectInstance(effect, Configs.Entity.ABERRATION.initital_corruption_duration_ticks, 0, false, false));
        }

        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.Entity.ABERRATION.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, Configs.Entity.ABERRATION.MOVEMENT_SPEED_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.Entity.ABERRATION.ATTACK_DAMAGE_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.Entity.ABERRATION.ATTACK_SPEED_ATTRIBUTE);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }

    private String lastAnimation = "";

    private void tickAnimations() {
        if (!this.getWorld().isClient) {
            return;
        }
        if (this.handSwinging) {
            this.handSwinging = false;
            lastAnimation = "attack";
            AzCommand.create(EntityAnimations.BASE_CONTROLLER, "attack", AzPlayBehaviors.PLAY_ONCE).sendForEntity(this);
            return;
        }
        if (this.isOnGround() && EntityAnimations.isMovingHorizontally(this)) {
            // Walk plays at 3x speed, as in the old predicate.
            lastAnimation = EntityAnimations.sendIfChanged(this, lastAnimation, "walk",
                    AzCommand.compose(
                            AzCommand.create(EntityAnimations.BASE_CONTROLLER, "walk", AzPlayBehaviors.LOOP),
                            AzCommand.controllerBuilder().setSpeed(EntityAnimations.BASE_CONTROLLER, 3.0F).build()));
        } else {
            lastAnimation = EntityAnimations.sendIfChanged(this, lastAnimation, "idle",
                    AzCommand.compose(
                            AzCommand.create(EntityAnimations.BASE_CONTROLLER, "idle", AzPlayBehaviors.LOOP),
                            AzCommand.controllerBuilder().setSpeed(EntityAnimations.BASE_CONTROLLER, 1.0F).build()));
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.tickAnimations();
    }
}
