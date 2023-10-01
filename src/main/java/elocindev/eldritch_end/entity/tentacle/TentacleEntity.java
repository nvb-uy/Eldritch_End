package elocindev.eldritch_end.entity.tentacle;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.effects.Corruption;
import elocindev.eldritch_end.registry.EffectRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class TentacleEntity extends HostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    public TentacleEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 0, false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hasStatusEffect(EffectRegistry.HASTUR_PRESENCE) && this instanceof TentacleEntity) {
            LivingEntity tentacle = new UndeadTentacleEntity(EntityRegistry.UNDEAD_TENTACLE, this.getWorld());
            tentacle.setHealth(this.getHealth());
            tentacle.setVelocity(this.getVelocity());
            tentacle.setPosition(this.getPos());
            this.getWorld().spawnEntity(tentacle);
            this.discard();
            return;
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity victim) {
            StatusEffect effect = EffectRegistry.CORRUPTION;

            if (victim.hasStatusEffect(effect)) {
                victim.addStatusEffect(new StatusEffectInstance(effect, victim.getStatusEffect(effect).getDuration(), victim.getStatusEffect(effect).getAmplifier() + 1, false, false));
                
                float extradamage = ((float) Configs.ENTITY_ABERRATION.ATTACK_DAMAGE_ATTRIBUTE * 0.25f) * (victim.getStatusEffect(effect).getAmplifier() + 1);
                
                victim.damage(Corruption.of(this.getWorld(), Corruption.DAMAGE), extradamage);
            } else
                victim.addStatusEffect(new StatusEffectInstance(effect, Configs.ENTITY_ABERRATION.initital_corruption_duration_ticks, 0, false, false));
        }

        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.ENTITY_TENTACLE.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.ENTITY_TENTACLE.ATTACK_DAMAGE_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.ENTITY_TENTACLE.ATTACK_SPEED_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "animationPredicate", 10, event -> {
            if (this.age < 20) {
                event.getController().setTransitionLength(0);
                event.getController().setAnimation(RawAnimation.begin().then("appear", Animation.LoopType.LOOP));

                return PlayState.CONTINUE;
            }
            if (this.handSwinging) {
                this.handSwinging = false;
                event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));

                return PlayState.CONTINUE;
            }

            event.getController().setAnimationSpeed(1.0F);
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;

        })).add((new AnimationController<>(this, "spawnAnimationPredicate", 10, event -> {
            event.getController().setAnimationSpeed(1.0F);
            if (this.age < 20) {
                event.getController().forceAnimationReset();
                event.getController().setAnimation(RawAnimation.begin().then("appear", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;
            }
            return PlayState.CONTINUE;
        }))).add(new AnimationController<>(this, "attackAnimationPredicate", 10, event -> {
            if (this.isAttacking()) {
                event.getController().setAnimationSpeed(4.0F);
                event.getController().forceAnimationReset();
                event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}