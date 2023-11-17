package elocindev.eldritch_end.entity.aberration;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.corruption.CorruptionUtils;
import elocindev.eldritch_end.effects.Corruption;
import elocindev.eldritch_end.registry.BlockRegistry;
import elocindev.eldritch_end.registry.EffectRegistry;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.Animation;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
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


public class AberrationEntity extends HostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "animationPredicate", 10, event -> {
            if (this.handSwinging) {
                event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
                this.handSwinging = false;
                return PlayState.CONTINUE;
            }

            if (event.isMoving() && this.isOnGround()) {
                // TODO: Make this scale with the movement attribute
                event.getController().setAnimationSpeed(3.0F);

                event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
                return PlayState.CONTINUE;
            }

            event.getController().setAnimationSpeed(1.0F);
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}