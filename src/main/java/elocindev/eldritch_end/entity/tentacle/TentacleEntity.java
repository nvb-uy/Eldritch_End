package elocindev.eldritch_end.entity.tentacle;

import elocindev.eldritch_end.entity.EntityAnimations;
import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehaviors;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.effects.Corruption;
import elocindev.eldritch_end.registry.EffectRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class TentacleEntity extends HostileEntity {

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
        this.tickAnimations();
        super.tick();
        if (this.getWorld().getBlockState(this.getBlockPos().down()) == Blocks.AIR.getDefaultState()) {
            this.kill();
        }

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
    public void tickMovement() {
        this.getWorld().getProfiler().push("ai");
        if (this.isImmobile()) {
            this.jumping = false;
            this.sidewaysSpeed = 0.0F;
            this.forwardSpeed = 0.0F;
        } else if (this.canMoveVoluntarily()) {
            this.getWorld().getProfiler().push("newAi");
            this.tickNewAi();
            this.getWorld().getProfiler().pop();
        }
    }


        @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity victim) {
            StatusEffect effect = EffectRegistry.CORRUPTION;

            if (victim.hasStatusEffect(effect)) {
                victim.addStatusEffect(new StatusEffectInstance(effect, victim.getStatusEffect(effect).getDuration(), victim.getStatusEffect(effect).getAmplifier() + 1, false, false));
                
                float extradamage = ((float) Configs.Entity.ABERRATION.ATTACK_DAMAGE_ATTRIBUTE * 0.25f) * (victim.getStatusEffect(effect).getAmplifier() + 1);
                
                victim.damage(Corruption.of(this.getWorld(), Corruption.DAMAGE), extradamage);
            } else
                victim.addStatusEffect(new StatusEffectInstance(effect, Configs.Entity.ABERRATION.initital_corruption_duration_ticks, 0, false, false));
        }

        return super.tryAttack(target);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.Entity.TENTACLE.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.Entity.TENTACLE.ATTACK_DAMAGE_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.Entity.TENTACLE.ATTACK_SPEED_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) { }

    private String lastAnimation = "";
    private boolean wasAttacking = false;

    private void tickAnimations() {
        if (!this.getWorld().isClient) {
            return;
        }
        // Base controller: appear on spawn, swing when handSwinging, otherwise idle.
        if (this.age < 20) {
            lastAnimation = EntityAnimations.sendIfChanged(this, lastAnimation, "appear",
                    AzCommand.create(EntityAnimations.BASE_CONTROLLER, "appear", AzPlayBehaviors.PLAY_ONCE));
        } else if (this.handSwinging) {
            this.handSwinging = false;
            lastAnimation = EntityAnimations.sendIfChanged(this, lastAnimation, "attack",
                    AzCommand.create(EntityAnimations.BASE_CONTROLLER, "attack", AzPlayBehaviors.LOOP));
        } else {
            lastAnimation = EntityAnimations.sendIfChanged(this, lastAnimation, "idle",
                    AzCommand.create(EntityAnimations.BASE_CONTROLLER, "idle", AzPlayBehaviors.LOOP));
        }

        // Attack controller: restart the attack at 4x speed when the attack starts.
        boolean attacking = this.isAttacking();
        if (attacking && !this.wasAttacking) {
            AzCommand.compose(
                    AzCommand.create("attack", "attack", AzPlayBehaviors.PLAY_ONCE),
                    AzCommand.controllerBuilder().setSpeed("attack", 4.0F).build()).sendForEntity(this);
        }
        this.wasAttacking = attacking;
    }
}
