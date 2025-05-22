package elocindev.eldritch_end.entity.crystal_entity;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.api.particles.ParticleBatch;
import elocindev.eldritch_end.api.particles.SpellSchools;
import elocindev.eldritch_end.api.targeting.TargetHelper;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static elocindev.eldritch_end.api.particles.SpellSchools.ARCANE;
import static elocindev.eldritch_end.api.particles.SpellSchools.FIRE;
import static elocindev.eldritch_end.api.particles.SpellSchools.FROST;
import static elocindev.eldritch_end.entity.eye_remastered.EyeEntity.sendBatches;
import static elocindev.eldritch_end.registry.EffectRegistry.CORRUPTION;

public class CrystalEntity extends HostileEntity implements Ownable, GeoEntity {
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.crystal.idle");

    public static ParticleBatch glyph_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchools school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch("minecraft:snowflake_particle", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.2F,0.2F,angle,0,20,false);
        }

        if(school.equals(FIRE)){
            return  new ParticleBatch("minecraft:flame", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.2F,0.2F,angle,0,20,false);
        }
        return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,20,false);

    }
    public static ParticleBatch glyph_outer_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchools school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch("minecraft:snowflake_particle", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,100,false);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch("minecraft:flame", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,100,false);
        }
        return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,100,false);

    }
    public static ParticleBatch glyph_center_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchools school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch("minecraft:snowflake_particle", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,150,false);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch("minecraft:flame", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,150,false);
        }
        return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,150,false);

    }
    public CrystalEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public CrystalEntity(EntityType<? extends HostileEntity> entityType, World world, Entity owner) {
        super(entityType, world);
    }
    private PlayState predicate2(AnimationState<CrystalEntity> state) {

        return state.setAndContinue(IDLE);

    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
        animationData.add(new AnimationController<CrystalEntity>(this, "walk",
                0, this::predicate2)
        );
        animationData.add(
                new AnimationController<>(this, "fall", event -> PlayState.CONTINUE)
                        .triggerableAnim("fall", FALL));
    }
    public static final RawAnimation FALL = RawAnimation.begin().thenPlay("animation.crystal.fall");

    public static final TrackedData<Integer> OWNER;

    public AnimatableInstanceCache instanceCache = AzureLibUtil.createInstanceCache(this);

    static {

    OWNER =DataTracker.registerData(CrystalEntity .class,TrackedDataHandlerRegistry.INTEGER);
}
    private UUID ownerUuid;

    private Entity owner;

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(OWNER, -1);


    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        if (this.ownerUuid != null) {
            nbt.putUuid("Owner", this.ownerUuid);
        }


    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.containsUuid("Owner")) {
            this.ownerUuid = nbt.getUuid("Owner");
            this.owner = null;
        }

    }

    @Nullable

    public Entity getOwner() {
        if (this.owner != null && !this.owner.isRemoved()) {
            return this.owner;
        } else if (this.ownerUuid != null && this.getWorld() instanceof ServerWorld) {
            this.owner = ((ServerWorld)this.getWorld()).getEntity(this.ownerUuid);
            return this.owner;
        } else {
            return null;
        }
    }

    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUuid = entity.getUuid();
            this.owner = entity;
        }

    }


    @Override
    public void pushAwayFrom(Entity entity) {

    }

    @Override
    protected void pushAway(Entity entity) {

    }

    @Override
    protected void onKilledBy(@Nullable LivingEntity adversary) {
        if(this.getOwner() instanceof LivingEntity living && adversary != null){
            this.getOwner().damage(adversary.getDamageSources().create(DamageTypes.GENERIC,adversary),living.getMaxHealth()*0.04F);
        }
        else if(this.getOwner() instanceof LivingEntity living && this.getLastAttacker() != null){
            this.getOwner().damage(this.getDamageSources().create(DamageTypes.GENERIC,this.getLastAttacker()),living.getMaxHealth()*0.04F);

        } else if(this.getOwner() instanceof LivingEntity living ){
            this.getOwner().damage(this.getDamageSources().generic(),living.getMaxHealth()*0.04F);

        }

        super.onKilledBy(adversary);
    }
    @Override
    public boolean damage(DamageSource source, float amount) {
        if(this.getOwner() != null && source.getAttacker() != null && this.getOwner().equals(source.getAttacker())){
            return false;
        }
        if(this.getOwner() != null && !source.isOf(DamageTypes.THORNS)){
            DamageSource source1 = new DamageSource(source.getTypeRegistryEntry(),source.getSource(),source.getAttacker());
            this.getOwner().damage(source1,amount);
        }

        return super.damage(source, amount);
    }

    @Override
    protected void applyDamage(DamageSource source, float amount) {

        super.applyDamage(source, amount);
    }

    @Override
    public void tick() {
        if(this.firstUpdate && !this.getWorld().isClient()){
            (this).triggerAnim("fall","fall");
            this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL,5,1);
        }
        if(this.age == 40 && !this.getWorld().isClient()){

            this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL,5,1);
            TargetHelper.Area area = new TargetHelper.Area();
            area.angle_degrees = 360;
            for(Entity target: TargetHelper.targetsFromArea(this,this.getPos(),6,area,entity ->
                entity != this.getOwner() && !(entity instanceof CrystalEntity))) {
                target.damage(this.getDamageSources().mobAttack(this), (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
            }
            sendBatches(this,new ParticleBatch[]{glyph_center_release(1,0, ParticleBatch.Origin.FEET,null, ARCANE,true)},this.getYaw(),this.getPitch(),1, PlayerLookup.tracking(this),false);
            sendBatches(this,new ParticleBatch[]{glyph_outer_release(1,0, ParticleBatch.Origin.FEET, null, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
            sendBatches(this,new ParticleBatch[]{glyph_release(1,0, ParticleBatch.Origin.FEET, null, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
        }
        if(this.age > 240 && !this.getWorld().isClient()){

            this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL,5,1);
            TargetHelper.Area area = new TargetHelper.Area();
            area.angle_degrees = 360;
            for(Entity target: TargetHelper.targetsFromArea(this,this.getPos(),6,area,entity ->
                    entity != this.getOwner() && !(entity instanceof CrystalEntity))) {
                if(target instanceof LivingEntity living) {
                    target.damage(this.getDamageSources().create(DamageTypes.GENERIC,this), ( float)living.getMaxHealth()*0.01F);
                }
            }
            sendBatches(this,new ParticleBatch[]{glyph_center_release(1,0, ParticleBatch.Origin.FEET, null, ARCANE,true)},this.getYaw(),this.getPitch(),1, PlayerLookup.tracking(this),false);
            sendBatches(this,new ParticleBatch[]{glyph_outer_release(1,0, ParticleBatch.Origin.FEET, null, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
            sendBatches(this,new ParticleBatch[]{glyph_release(1,0, ParticleBatch.Origin.FEET, null, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);

            this.discard();

        }
        super.tick();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }


}
