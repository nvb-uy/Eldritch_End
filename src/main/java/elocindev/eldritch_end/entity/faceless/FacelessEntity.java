package elocindev.eldritch_end.entity.faceless;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.client.particle.EldritchParticles;
import elocindev.eldritch_end.registry.ItemRegistry;
import elocindev.eldritch_end.registry.SoundEffectRegistry;
import mod.azure.azurelib.ai.pathing.AzureNavigation;
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
import net.minecraft.entity.boss.BossBar.Color;
import net.minecraft.entity.boss.BossBar.Style;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.HashMap;

@SuppressWarnings("resource")
public class FacelessEntity extends HostileEntity implements GeoEntity {

    // Todo: smooth transition instead of teleport

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if (this.getWorld().isClient) return;
        for (PlayerEntity playerEntity : this.getWorld().getEntitiesByClass(PlayerEntity.class, new Box(this.getBlockPos()).expand(32), entity -> true)) {
            if (!playerEntity.isDead()) {
                playerEntity.addExperience(1400);
                playerEntity.giveItemStack(new ItemStack(ItemRegistry.XAL));
            }
        }
    }

    @Nullable Entity entityToPull;
    @Nullable HashMap<PlayerEntity, Integer> pullTargets = new HashMap<>();
    
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
    private final ServerBossBar bossBar;
    private float animationProgressTicks = 0;
    private float animationDuration = 42;

    /** curse variables **/
    private int curseThreshold = 5;

    /** shadow surge variables **/
    private boolean shouldSurge = false;
    private float shadowSurgeProgress = 0;
    private float shadowSurgeDuration = 94;
    private float firstImpactTicks = 43;
    private float secondImpactTicks = 52;
    private float thirdImpactTicks = 63;
    private static final float SURGE_RADIUS = 24f;

    /** shadow pull variables **/
    private float pullDuration = 40;

    public FacelessEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar)(new ServerBossBar(Text.of("\uC892"), Color.PURPLE, Style.PROGRESS))
            .setDarkenSky(true)
            .setThickenFog(true);
        navigation = new AzureNavigation(this, world);
        this.setStepHeight(1.0F);
        this.animationProgressTicks = 0;
    }

    private void curse(PlayerEntity target) {
        if (target == null || Math.abs(this.getPos().y - target.getPos().y) < curseThreshold || target.getWorld().isClient) return;

        Vec3d rotationVector = this.getRotationVector().normalize();
        target.teleport(this.getX() + rotationVector.multiply(2).x, this.getY(), this.getZ() + rotationVector.multiply(2).z);
    }

    private void shadowSurge() {
        this.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, (int) shadowSurgeDuration, 255, false, false, false), null);
        this.shadowSurgeProgress = 0;
        this.shouldSurge = true;
        EldritchParticles.playEffek("shadowsurge", this.getWorld(), this.getPos(),
                true, 0.60F).bindOnEntity(this);
    }

    private void meleeLogic() {
        if (animationProgressTicks < animationDuration) animationProgressTicks++;
        if (animationProgressTicks == 18)  {
            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEffectRegistry.PUNCH_EVENT, this.getSoundCategory(), 1F, 1.0f);
            performKeyframeAttack(this.getTarget());
        }
    }

    private void shadowSurgeLogic() {
        if (shadowSurgeProgress < shadowSurgeDuration) {
            shadowSurgeProgress++;
        } else {
            this.shouldSurge = false;
            this.shadowSurgeProgress = 0;
        }

        if (shadowSurgeProgress == 0 && shouldSurge) shadowSurge();
        else if (shadowSurgeProgress == firstImpactTicks && shouldSurge) shadowSurgeAttack();
        else if (shadowSurgeProgress == secondImpactTicks && shouldSurge) shadowSurgeAttack();
        else if (shadowSurgeProgress == thirdImpactTicks && shouldSurge) shadowSurgeAttack();
    }

    private void shadowSurgeAttack() {
        float missingHealth = (this.getMaxHealth() - this.getHealth()) / 2;
        this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEffectRegistry.ORB_EVENT, this.getSoundCategory(), 1F, 1.0f);
        for (PlayerEntity playerEntity: this.getWorld().getEntitiesByClass(PlayerEntity.class, new Box(this.getBlockPos()).expand(9, 0, 9), entity -> true)) {
            playerEntity.damage(this.getDamageSources().generic(), playerEntity.getMaxHealth() / 3f);
        }
    }

    private void shadowSurgeTeleport(Entity target) {
        if (target == null || this.getWorld().isClient
           || target.distanceTo(this) < SURGE_RADIUS) return;

        Vec3d rotationVector = this.getRotationVector().normalize();
        target.teleport(this.getX() + rotationVector.multiply(2).x, this.getY(), this.getZ() + rotationVector.multiply(2).z);

        this.shadowSurge();
        if (!(target instanceof LivingEntity livingEntity)) return;
        livingEntity.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1, false, false, false), null);
    }


    public static Vec3d lerpedPosition(Vec3d current, Vec3d desired, float timeDelta) {
        return new Vec3d(Math.lerp(current.x, desired.x, timeDelta), Math.lerp(current.y, desired.y, timeDelta), Math.lerp(current.z, desired.z, timeDelta));
    }


    private void pullMove(PlayerEntity target, int elapsedTicks) {
        pullTargets.put(target, (elapsedTicks <= pullDuration) ? elapsedTicks + 1 : 0);
        Vec3d lerpedPos = lerpedPosition(target.getPos(), this.getPos(), pullTargets.get(target) / pullDuration);
        target.teleport(lerpedPos.x, lerpedPos.y, lerpedPos.z);
    }

    private void pullLogic(PlayerEntity target, int elapsedTicks) {
        if (elapsedTicks < pullDuration && !target.getBlockPos().isWithinDistance(this.getPos(), 2)) {
            pullMove(target, elapsedTicks);
            this.setVelocity(0, 0, 0);
            target.setVelocity(0, 0,0);
        } else {
            target.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1, false, false, false), null);
            pullTargets.remove(target);
            if (!this.shouldSurge) this.shadowSurge();
        }
        this.velocityDirty = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) return;
        this.meleeLogic();
        this.shadowSurgeLogic();

        if (pullTargets != null) {
            for (PlayerEntity key: pullTargets.keySet()) {
                pullLogic(key, pullTargets.get(key));
            }
        }

        if (this.age % 10 != 0) {
            for (PlayerEntity playerEntity : this.getWorld().getEntitiesByClass(PlayerEntity.class, new Box(this.getBlockPos()).expand(64), entity -> true)) {
                if (!playerEntity.isCreative() && playerEntity.distanceTo(this) > SURGE_RADIUS) {
                    pullTargets.put(playerEntity, 0);
                    //shadowSurgeTeleport(playerEntity);
                    //curse(playerEntity);
                }
            }
        }

        if (this.age % 400 == 0 && !this.shouldSurge) {
            shadowSurge();
        }
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "baseAnim", 5, event -> {
            return event.setAndContinue(
                    event.isMoving() ? RawAnimation.begin().thenLoop("walk")
                    : RawAnimation.begin().thenLoop("idle"));
        }));

        controllers.add(new AnimationController<>(this, "attackAnim", event -> PlayState.CONTINUE)
                .triggerableAnim("slam",
                RawAnimation.begin().then("slam", Animation.LoopType.PLAY_ONCE)));
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target.getWorld().isClient) return false;
        if (this.animationProgressTicks == animationDuration && !shouldSurge) {
            this.animationProgressTicks = 0;
            triggerAnim("attackAnim", "slam");
            this.handSwinging = false;
        }
        EldritchEnd.LOGGER.info("Attack!");
        return false;
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
    }

    @Override
    public void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {}

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
        //this.setTarget(player);
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


    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                // TODO: REPLACE THIS WITH CUSTOM FACELESS CONFIG
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 800)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1000);
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEffectRegistry.GROWL_EVENT;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }


    private void performKeyframeAttack(Entity target) {
        if (target != null && !target.getWorld().isClient) {
            target.damage(this.getDamageSources().mobAttack(this), this.getAttackDamage());
        }
    }
}