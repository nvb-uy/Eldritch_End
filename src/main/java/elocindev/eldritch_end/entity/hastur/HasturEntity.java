package elocindev.eldritch_end.entity.hastur;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.entity.tentacle.UndeadTentacleEntity;
import elocindev.eldritch_end.registry.EffectRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.BossBar.Color;
import net.minecraft.entity.boss.BossBar.Style;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class HasturEntity extends HostileEntity implements IAnimatable {
    @SuppressWarnings("removal")
    private AnimationFactory factory = new AnimationFactory(this);

    private final ServerBossBar bossBar;

    public HasturEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar)(new ServerBossBar(Text.of("\uE892"), Color.YELLOW, Style.PROGRESS))
            .setDarkenSky(true)
            .setThickenFog(true);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new AttackGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 16.0F));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source == DamageSource.OUT_OF_WORLD) {
            this.applyDamage(source, amount);
        }
        return false;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        this.setVelocity(this.getVelocity().multiply(1.0D, 0.0D, 1.0D));
        this.velocityModified = true;
    }

    @Override
    public void mobTick() {
        super.mobTick();

        if (this.age % 200 == 0 && !this.world.isClient()) {
            lightningAttack(100);
        }

        if (this.age % 100 == 0 && !this.world.isClient()) {
            summonMinion(100);
        }

        if (this.age == 1) {
            this.setPosition(this.getX(), this.getY() + 5, this.getZ());
        }
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    private void lightningAttack(float damageAmount) {
        if (this.world.getClosestPlayer(this.getX(), this.getY(), this.getZ(), 20, false) != null) {
            PlayerEntity closestPlayer = this.world.getClosestPlayer(this.getX(), this.getY(), this.getZ(), 20, false);
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, this.world);
            lightning.setVelocity(this.getVelocity());
            lightning.setPosition(closestPlayer.getPos());
            this.world.spawnEntity(lightning);
            this.damage(DamageSource.OUT_OF_WORLD, damageAmount);
        }
    }

    private void summonMinion(float damageAmount) {
        LivingEntity minion = new HuskEntity(EntityType.HUSK, this.world);
        minion.setHealth(this.getHealth());
        minion.setVelocity(this.getVelocity());
        minion.setPosition(this.getPos());
        this.world.spawnEntity(minion);
        this.damage(DamageSource.OUT_OF_WORLD, damageAmount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
        // TODO: REPLACE THIS WITH CUSTOM HASTUR CONFIG
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.BOSS_HASTUR.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1000);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::animationPredicate));
    }

    protected <E extends HasturEntity> PlayState animationPredicate(final AnimationEvent<E> event) {
        event.getController().animationSpeed = 1.0F;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hastur.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

        @Override
    public void checkDespawn() {
        if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
            this.discard();
        } else {
            this.despawnCounter = 0;
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}