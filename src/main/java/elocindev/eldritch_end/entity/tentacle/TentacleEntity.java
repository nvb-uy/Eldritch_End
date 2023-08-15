package elocindev.eldritch_end.entity.tentacle;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.EffectRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class TentacleEntity extends HostileEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public TentacleEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 0, false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity victim) {
            StatusEffect effect = EffectRegistry.CORRUPTION;

            if (victim.hasStatusEffect(effect)) {
                victim.addStatusEffect(new StatusEffectInstance(effect, victim.getStatusEffect(effect).getDuration(), victim.getStatusEffect(effect).getAmplifier() + 1, false, false));
                
                float extradamage = ((float) Configs.ENTITY_ABERRATION.ATTACK_DAMAGE_ATTRIBUTE * 0.25f) * (victim.getStatusEffect(effect).getAmplifier() + 1);
                
                victim.damage(DamageSource.mob(this), extradamage);
            } else
                victim.addStatusEffect(new StatusEffectInstance(effect, Configs.ENTITY_ABERRATION.initital_corruption_duration_ticks, 0, false, false));
        }

        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.ENTITY_ABERRATION.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.ENTITY_ABERRATION.ATTACK_DAMAGE_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.ENTITY_ABERRATION.ATTACK_SPEED_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "spawnController", 10, this::spawnAnimationPredicate));
        data.addAnimationController(new AnimationController<>(this, "attackController", 10, this::attackAnimationPredicate));
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::animationPredicate));
    }

    protected <E extends TentacleEntity> PlayState animationPredicate(final AnimationEvent<E> event) {
        if (this.age < 20) {
            event.getController().transitionLengthTicks = 0;
            event.getController().setAnimation(new AnimationBuilder().addAnimation("appear", ILoopType.EDefaultLoopTypes.LOOP));
        
            return PlayState.CONTINUE;
        }
        if (this.handSwinging) {
            this.handSwinging = false;
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", ILoopType.EDefaultLoopTypes.LOOP));
        
            return PlayState.CONTINUE;
        }

        event.getController().animationSpeed = 1.0F;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    protected <E extends TentacleEntity> PlayState spawnAnimationPredicate(final AnimationEvent<E> event) {
        event.getController().animationSpeed = 1.0F;
        
        if (this.age < 20) {

            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("appear", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        
            return PlayState.CONTINUE;
        }
        
        return PlayState.CONTINUE;
    }

    protected <E extends TentacleEntity> PlayState attackAnimationPredicate(final AnimationEvent<E> event) {
        if (this.isAttacking()) {
            event.getController().animationSpeed = 2.0F;

            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        }
        
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}