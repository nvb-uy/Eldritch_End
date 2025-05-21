package elocindev.eldritch_end.entity.arcane_missile;

import elocindev.eldritch_end.api.particles.ParticleBatch;
import elocindev.eldritch_end.api.targeting.VectorHelper;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static elocindev.eldritch_end.api.particles.ParticleHelper.sendBatches;

public class ArcaneMissile extends ThrownItemEntity implements FlyingItemEntity {
    public ArcaneMissile(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    static ParticleBatch FLYPARTICLES;
    public  Entity target;
    public  double homing_angle;



    static {
        FLYPARTICLES =  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER, null,45,45,5,0.02F,0.05F,0,0,0,false);

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if(this.getOwner() instanceof LivingEntity living && ((EntityHitResult)entityHitResult).getEntity() != null){
            ((EntityHitResult)entityHitResult).getEntity().damage(this.getDamageSources().mobAttack(living), (float) (living.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)*0.8F));
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

        super.onBlockHit(blockHitResult);
    }

    private void followTarget() {
        Entity target = this.target;
        if (target != null && homing_angle > 0.0F) {
            Vec3d distanceVector = target.getPos().add(0.0, (double)(target.getHeight() / 2.0F), 0.0).subtract(this.getPos().add(0.0, (double)(this.getHeight() / 2.0F), 0.0));
            Vec3d newVelocity = VectorHelper.rotateTowards(this.getVelocity(), distanceVector, (double)this.homing_angle);
            if (newVelocity.lengthSquared() > 0.0) {
                this.setVelocity(newVelocity);
                this.velocityDirty = true;
            }
        }

    }



    @Override
    public void tick() {
        if(this.getWorld() instanceof ServerWorld) {
            sendBatches(this, new ParticleBatch[]{FLYPARTICLES}, 1, PlayerLookup.tracking(this), false);
        }
        this.followTarget();
        super.tick();
    }

    @Override
    protected void onCollision(HitResult hitResult) {

        super.onCollision(hitResult);
        this.discard();
    }


    @Override
    protected Item getDefaultItem() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }
}
