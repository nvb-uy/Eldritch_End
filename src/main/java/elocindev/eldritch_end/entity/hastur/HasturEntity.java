package elocindev.eldritch_end.entity.hastur;

import elocindev.eldritch_end.config.Configs;
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

public class HasturEntity extends HostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

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
        if (source == this.getDamageSources().outOfWorld()) {
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

        if (this.age % 200 == 0 && !this.getWorld().isClient()) {
            lightningAttack(100);
        }

        if (this.age % 100 == 0 && !this.getWorld().isClient()) {
            summonMinion(100);
        }

        if (this.age == 1) {
            this.setPosition(this.getX(), this.getY() + 5, this.getZ());
        }
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    private void lightningAttack(float damageAmount) {
        if (this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), 20, false) != null) {
            PlayerEntity closestPlayer = this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), 20, false);
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, this.getWorld());
            lightning.setVelocity(this.getVelocity());
            lightning.setPosition(closestPlayer.getPos());
            this.getWorld().spawnEntity(lightning);

            this.damage(this.getDamageSources().outOfWorld(), damageAmount);
        }
    }

    private void summonMinion(float damageAmount) {
        LivingEntity minion = new HuskEntity(EntityType.HUSK, this.getWorld());
        minion.setHealth(this.getHealth());
        minion.setVelocity(this.getVelocity());
        minion.setPosition(this.getPos());
        this.getWorld().spawnEntity(minion);

        this.damage(this.getDamageSources().outOfWorld(), damageAmount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
        // TODO: REPLACE THIS WITH CUSTOM HASTUR CONFIG
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.Entity.HASTUR.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1000);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }

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
        if (this.getWorld().getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
            this.discard();
        } else {
            this.despawnCounter = 0;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "animationPredicate", 10, event -> {
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