package elocindev.eldritch_end.entity.aberration;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.BlockRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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


public class AberrationEntity extends HostileEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public AberrationEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this,  1));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.age % 20 == 0 && this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1, this.getZ())).getBlock() == BlockRegistry.ABYSMAL_FRONDS) {
            this.heal(this.getMaxHealth() / 10);
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity victim) {
            StatusEffect effect = StatusEffects.SLOWNESS; // TODO: Replace with corruption effect

            if (victim.hasStatusEffect(effect))
                victim.addStatusEffect(new StatusEffectInstance(effect, victim.getStatusEffect(effect).getDuration(), victim.getStatusEffect(effect).getAmplifier() + 1));
            else
                victim.addStatusEffect(new StatusEffectInstance(effect, 200, 0));
        }

        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.ENTITY_ABERRATION.HEALTH)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, Configs.ENTITY_ABERRATION.MOVEMENT_SPEED)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.ENTITY_ABERRATION.ATTACK_DAMAGE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.ENTITY_ABERRATION.ATTACK_SPEED);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::animationPredicate));
        data.addAnimationController(new AnimationController<>(this, "attackController", 10, this::attackAnimationPredicate));
    }

    protected <E extends AberrationEntity> PlayState animationPredicate(final AnimationEvent<E> event) {
        if (event.isMoving() && this.isOnGround()) {
            // TODO: Make this scale with the movement attribute
            event.getController().animationSpeed = 3.0F;

            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().animationSpeed = 1.0F;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    protected <E extends AberrationEntity> PlayState attackAnimationPredicate(final AnimationEvent<E> event) {
        if (this.isAttacking()) {
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