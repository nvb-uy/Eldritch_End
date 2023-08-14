package elocindev.eldritch_end.entity.aberration;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.BlockRegistry;
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


public class AberrationEntity extends HostileEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public AberrationEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, Configs.ENTITY_ABERRATION.CHASE_SPEED, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, Configs.ENTITY_ABERRATION.WANDER_SPEED));
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
            StatusEffect effect = EffectRegistry.CORRUPTION; // TODO: Replace with corruption effect

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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, Configs.ENTITY_ABERRATION.MOVEMENT_SPEED_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.ENTITY_ABERRATION.ATTACK_DAMAGE_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.ENTITY_ABERRATION.ATTACK_SPEED_ATTRIBUTE);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }


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