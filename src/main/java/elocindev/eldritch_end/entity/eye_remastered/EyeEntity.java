package elocindev.eldritch_end.entity.eye_remastered;

import elocindev.eldritch_end.WorldSchedulerEldritch;
import elocindev.eldritch_end.api.particles.ParticleBatch;
import elocindev.eldritch_end.api.particles.SpellSchools;
import elocindev.eldritch_end.api.particles.packets.ParticlePackets;
import elocindev.eldritch_end.api.targeting.TargetHelper;
import elocindev.eldritch_end.compat.SpellEngineCompat;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.entity.arcane_missile.ArcaneMissile;
import elocindev.eldritch_end.entity.crystal_entity.CrystalEntity;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.azurelib.util.RenderUtils;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;


import java.util.*;

import static elocindev.eldritch_end.api.particles.SpellSchools.*;
import static elocindev.eldritch_end.api.targeting.TargetHelper.launchPoint;
import static java.lang.StrictMath.asin;
import static java.lang.StrictMath.atan2;

public class EyeEntity extends HostileEntity implements GeoEntity {
    private boolean hasCrystals;
    private int crystalsTime;

    public static ParticleBatch glyph_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchools school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch("minecraft:snowflake_particle", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,45,false);
        }

        if(school.equals(FIRE)){
            return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,45,false);
        }
        return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,50,0.2F,0.2F,angle,0,45,false);

    }
    public static ParticleBatch glyph_outer_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchools school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch("minecraft:snowflake_particle", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,100,false);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,100,false);
        }
        return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,100,false);

    }
    public static ParticleBatch glyph_center_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchools school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch("minecraft:snowflake_particle", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,150,false);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,150,false);
        }
        return  new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,150,false);

    }
    ParticleBatch FIREBATCH1 =   new ParticleBatch("minecraft:fire", ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,45,45,50,1F,1F,0,15,0,false);
    ParticleBatch FIREBATCH2 =   new ParticleBatch("minecraft:dragon_breath", ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,45,45,300,0.01F,20F,3,0,0,false);
    ParticleBatch FIREBATCH3 =   new ParticleBatch("minecraft:sculk_soul", ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,45,45,100,1F,2F,10,0,0,false);

    private boolean performing = false;
    private int teleporttimer = 80;
    private int missiletimer = 0;
    private int lasertimer = 0;
    private int crystaltimer = 160;
    private final ServerBossBar bossBar;
    private BlockPos home;

    private boolean teleporting;
    private int teleportduration;
    private Vec3d teleportLocation;

    @Override
    public boolean isPersistent() {
        return true;
    }

    public EyeEntity(EntityType<? extends EyeEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new EyeEntityMoveControl(this);
        this.lookControl = new EyeEntityLookControl(this);
        this.bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE    , BossBar.Style.PROGRESS))
                .setDarkenSky(true)
                .setThickenFog(true);
    }

    @Override
    public double getTick(Object entity) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(8, new LookAtTargetGoal());
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(2, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[]{EyeEntity.class, EndermanEntity.class})));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(4, new ActiveTargetGoal(this, AnimalEntity.class, true));

        super.initGoals();
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.eye.idle");

    @Override
    public void tick() {
        if((this.home == null  && this.firstUpdate) || (this.home != null && !this.home.isWithinDistance(this.getBlockPos(),150))){
            this.home = this.getBlockPos();
        }
        super.tick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        if(type.equals(SpellEngineCompat.CRYSTAL)){
            return false;
        }
        return super.canTarget(type);
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
    protected void mobTick() {
        if(!this.getWorld().isClient() && crystaltimer > Configs.Entity.EYE.cooldowns.COOLDOWN_CRYSTALS && !this.teleporting &&  !this.performing && this.getTarget() != null ) {
            TargetHelper.Area area = new TargetHelper.Area();
            area.angle_degrees = 120;
            List<Entity> list = TargetHelper.targetsFromArea(this,64,area, entity ->  entity instanceof LivingEntity && !(entity instanceof CrystalEntity) && !(entity instanceof EndermanEntity));
            int ii = 0;
                ( this).triggerAnim("attack", "attack");
                this.hasCrystals = true;
                this.crystalsTime = 0;

            ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch(Configs.Entity.EYE.cooldowns.CRYSTALS_TIME, () -> {
                this.hasCrystals = false;
                this.crystalsTime = 0;

                this.dataTracker.set(NUMBER_OF_CRYSTALS,0);
                this.dataTracker.set(CRYSTAL_TIME,0);

            }   );
            for(Entity entity: list) {
                if(ii > 6){
                    break;
                }
                    if (entity != null) {
                        BlockPos.Mutable mutable = new BlockPos.Mutable(entity.getPos().getX(),entity.getPos().getY(),entity.getPos().getZ());

                        while(mutable.getY() > this.getWorld().getBottomY() && !this.getWorld().getBlockState(mutable).blocksMovement()) {
                            mutable.move(Direction.DOWN);
                        }
                        BlockState blockState = this.getWorld().getBlockState(mutable);

                        boolean bl = blockState.blocksMovement();
                        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
                        if (bl && !bl2) {
                            CrystalEntity crystalEntity = new CrystalEntity(SpellEngineCompat.CRYSTAL,this.getWorld(),this);
                            crystalEntity.setOwner(this);
                            crystalEntity.setPosition(mutable.getX(),mutable.getY()+1,mutable.getZ());
                            this.getWorld().spawnEntity(crystalEntity);
                            ii++;
                            this.dataTracker.set(NUMBER_OF_CRYSTALS,this.dataTracker.get(NUMBER_OF_CRYSTALS)+1);
                        }
                        this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL,5,0);
                    }

                }

            crystaltimer = 0;
        }
        if(!this.getWorld().isClient() && missiletimer > Configs.Entity.EYE.cooldowns.COOLDOWN_MISSILE && !this.teleporting &&  !this.performing && this.getTarget() != null ) {
            TargetHelper.Area area = new TargetHelper.Area();
            area.angle_degrees = 120;
            List<Entity> list = TargetHelper.targetsFromArea(this,64,area, entity ->  entity instanceof LivingEntity && !(entity instanceof CrystalEntity) && !(entity instanceof EndermanEntity));

            for(int i = 0; i < 3; i++){
                int ii = 0;
                for(Entity entity: list) {
                    int finalI = i;

                    ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch((ii * 2) + 1, () -> {

                                ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch((finalI * 5) + 5, () -> {
                                    if (entity != null) {
                                        ArcaneMissile missile = new ArcaneMissile(SpellEngineCompat.ARCANEMISSILE,this.getWorld());
                                        this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES,entity.getEyePos());
                                        missile.setPosition(launchPoint(this));
                                        missile.setOwner(this);
                                        missile.setVelocity(this, this.getPitch(), this.getYaw(), 0.0F, 1.5F, 1.0F);

                                        missile.homing_angle = 15;
                                        missile.target = entity;
                                        this.getWorld().spawnEntity(missile);
                                        sendBatches(this,new ParticleBatch[]{glyph_center_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
                                        sendBatches(this,new ParticleBatch[]{glyph_outer_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
                                        sendBatches(this,new ParticleBatch[]{glyph_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);


                                        this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL,5,0);
                                    }

                                });
                            }
                    );
                    ii++;
                }
            }
            missiletimer = 0;
        }
        if(this.hasCrystals){
            this.crystalsTime++;
            this.dataTracker.set(CRYSTAL_TIME,this.dataTracker.get(CRYSTAL_TIME)+1);

        }

        if(!this.getWorld().isClient() && lasertimer > Configs.Entity.EYE.cooldowns.COOLDOWN_FLAMEBLAST && !this.teleporting &&  !this.performing && this.getTarget() != null ) {
            TargetHelper.Area area = new TargetHelper.Area();
            area.angle_degrees = 120;
            List<Entity> list = TargetHelper.targetsFromArea(this,64,area, entity ->  entity instanceof LivingEntity && !(entity instanceof CrystalEntity) && !(entity instanceof EndermanEntity));
            ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch(+ 30, () -> {
                ( this).triggerAnim("attack", "attack");

            });

            for(int i = 0; i < 3; i++){
                int ii = 0;
                for(Entity entity: list) {
                    int finalI = i;


                        Vec3d pos = entity.getBoundingBox().getCenter();
                    ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch((i * 5) + 5, () -> {
                        lineParticles(this,pos,pos,64);
                    });

                    ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch((ii * 2) + 1, () -> {

                                ((WorldSchedulerEldritch) this.getWorld()).scheduleEldritch((finalI * 10) + 20, () -> {
                                    if (entity != null) {
                                        this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES,pos);
                                        area.angle_degrees = 10;

                                        List<Entity> list2 = TargetHelper.targetsFromArea(this,64,area, entity2 ->  entity2 instanceof LivingEntity && this.canSee(entity2));
                                        for(Entity entity1 : list2){
                                            entity1.damage(this.getDamageSources().mobAttack(this), (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)*1.2F);
                                        }
                                        Vec3d vec = pos.subtract(this.getBoundingBox().getCenter()).normalize();
                                        float pitch = (float) (180*asin(-(vec.y))/Math.PI);
                                        float yaw = (float) (-180*atan2(vec.x, vec.z)/Math.PI);
                                        if ( this.getWorld() instanceof ServerWorld world) {


                                           // sendBatches(this,SpellRegistry.getSpell(new Identifier(EldritchEnd.MODID,"arcane_laser")).release.particles,yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{this.FIREBATCH1},yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{this.FIREBATCH2},yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{this.FIREBATCH3},yaw,pitch,1,PlayerLookup.tracking(this),false);

                                            sendBatches(this,new ParticleBatch[]{glyph_center_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, FIRE,true)},yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{glyph_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, FIRE,true)},yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{glyph_outer_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, FIRE,true)},yaw,pitch,1,PlayerLookup.tracking(this),false);

                                            this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE,5,0);

                                        }
                                    }

                                });
                            }
                    );
                    ii++;
                }
            }
            lasertimer = 0;
        }
        if(!this.getWorld().isClient() && teleporttimer > Configs.Entity.EYE.cooldowns.COOLDOWN_TELEPORT && !this.teleporting &&  !this.performing && this.getTarget() != null ) {

            Vec3d randomLocation = randomLocation(this.getTarget(),64);
            if(this.getTarget() != null) {
                this.teleportLocation = randomLocation;
                this.teleporting = true;
                this.teleportduration = 0;
                this.performing = true;

            }
            this.teleporttimer = this.getRandom().nextInt(Configs.Entity.EYE.cooldowns.RANDOM_COOLDOWN_REDUCTION_TELEPORT);
        }
        if(this.getTarget() != null) {
            this.lookControl.lookAt(this.getTarget().getEyePos());
        }
        if(this.teleporting && teleportduration < 10 && this.teleportLocation != null){
            if(this.getTarget() != null) {
                Vec3d vec3d = this.teleportLocation.subtract(this.teleportLocation.subtract(this.getPos()).normalize().multiply(0.2*this.teleportLocation.subtract(this.getPos()).length()));
                this.teleport(vec3d.getX(),vec3d.getY(),vec3d.getZ());
            }
            teleportduration++;
        }

        else{
            this.teleporting = false;
            this.teleportLocation = null;
            this.performing = false;
        }
        if(!this.getWorld().isClient()){
            teleporttimer++;
            lasertimer++;
            missiletimer++;
            crystaltimer++;
        }

        super.mobTick();
    }

    private PlayState predicate2(AnimationState<EyeEntity> state) {

        return state.setAndContinue(IDLE);

    }
    public void lineParticles(LivingEntity caster, Vec3d start, Vec3d end, int range){
        for (int i = 0; i < range; i++) {
            Vec3d pos2 = this.getBoundingBox().getCenter().add((
                    end.subtract(
                            this.getBoundingBox().getCenter()).normalize().multiply(i)));
            if ( caster.getWorld() instanceof ServerWorld world) {

                for(ServerPlayerEntity player: PlayerLookup.tracking(caster)) {
                    world.spawnParticles(player, ParticleTypes.ELECTRIC_SPARK,true,pos2.getX(),pos2.getY(),pos2.getZ(),1,0,0,0,0);
                }


            }
        }
    }
    private static Vec3d origin(Entity entity, ParticleBatch.Origin origin) {
        switch (origin) {
            case FEET:
                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.1F), 0.0);
            case CENTER:
                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.5F), 0.0);
            case LAUNCH_POINT:
                if (entity instanceof LivingEntity livingEntity) {
                    return launchPoint(livingEntity);
                }

                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.5F), 0.0);
            default:
                return entity.getPos();
        }
    }

    public static void sendBatches(Entity trackedEntity, ParticleBatch[] batches, float yaw, float pitch, float countMultiplier, Collection<ServerPlayerEntity> trackers, boolean includeSourceEntity) {
        if (batches != null && batches.length != 0) {
            int sourceEntityId = trackedEntity.getId();
            ParticlePackets.ParticleBatches.SourceType sourceType = ParticlePackets.ParticleBatches.SourceType.COORDINATE;
            ArrayList<ParticlePackets.ParticleBatches.Spawn> spawns = new ArrayList();
            ParticleBatch[] var8 = batches;
            int var9 = batches.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                ParticleBatch batch = var8[var10];
                Vec3d sourceLocation = Vec3d.ZERO;
                switch (sourceType) {
                    case ENTITY:
                    default:
                        break;
                    case COORDINATE:
                        sourceLocation = origin(trackedEntity, batch.origin);
                }

                spawns.add(new ParticlePackets.ParticleBatches.Spawn(includeSourceEntity ? sourceEntityId : 0, yaw,pitch, sourceLocation, batch));
            }

            PacketByteBuf packet = (new ParticlePackets.ParticleBatches(sourceType, spawns)).write(countMultiplier);
            if (trackedEntity instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity)trackedEntity;
                sendWrittenBatchesToPlayer(serverPlayer, packet);
            }

            trackers.forEach((serverPlayerx) -> {
                sendWrittenBatchesToPlayer(serverPlayerx, packet);
            });
        }
    }
    private static void sendWrittenBatchesToPlayer(ServerPlayerEntity serverPlayer, PacketByteBuf packet) {
        try {
            if (ServerPlayNetworking.canSend(serverPlayer, ParticlePackets.ParticleBatches.ID)) {
                ServerPlayNetworking.send(serverPlayer, ParticlePackets.ParticleBatches.ID, packet);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
    Vec3d randomLocation(Entity entity, double d) {
        double e = entity.getX();
        double f= entity.getY();
        double g= entity.getZ();
        for(int i = 0; i < 20; i++) {

           e = entity.getX() + (this.getRandom().nextDouble() - 0.5) * d;
           f = entity.getY() + (double) (this.getRandom().nextDouble() - 0.5) * d;
           g = entity.getZ() + (this.getRandom().nextDouble() - 0.5) * d;
            if(this.getWorld().raycast(new RaycastContext(entity.getBoundingBox().getCenter(), new Vec3d(e, f, g), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY,entity)).getType().equals(HitResult.Type.MISS)
           ){
                float width = this.getWidth() * 0.8F;
                Box box = Box.of(new Vec3d(e, f, g), (double)width, width, (double)width);
                if( BlockPos.stream(box).noneMatch((pos) -> {
                    BlockState blockState = this.getWorld().getBlockState(pos);
                    return !blockState.isAir() && blockState.shouldSuffocate(this.getWorld(), pos) && VoxelShapes.matchesAnywhere(blockState.getCollisionShape(this.getWorld(), pos).offset((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), VoxelShapes.cuboid(box), BooleanBiFunction.AND);
                })){
                    if(home.isWithinDistance(new Vec3i((int)e,(int)f,(int)g),150)){
                        break;

                    }
                }
            }
            e = entity.getX();
            f= entity.getY();
            g= entity.getZ();
        }
        return new Vec3d(e, f, g);
    }

    private class LookAtTargetGoal extends Goal {
        public LookAtTargetGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return !EyeEntity.this.getMoveControl().isMoving() && EyeEntity.this.random.nextInt(toGoalTicks(7)) == 0;
        }

        public boolean shouldContinue() {
            return false;
        }

        public void tick() {

            BlockPos    blockPos = EyeEntity.this.getBlockPos();

            for(int i = 0; i < 3; ++i) {
                BlockPos blockPos2 = blockPos.add(EyeEntity.this.random.nextInt(15) - 7, EyeEntity.this.random.nextInt(11) - 5, EyeEntity.this.random.nextInt(15) - 7);
                if (EyeEntity.this.getWorld().isAir(blockPos2)) {
                    EyeEntity.this.moveControl.moveTo((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5, 0.25);
                    if (EyeEntity.this.getTarget() != null) {
                        EyeEntity.this.getLookControl().lookAt(EyeEntity.this.getTarget().getX(),EyeEntity.this.getTarget().getY(),EyeEntity.this.getTarget().getZ(), 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if(!source.isOf(DamageTypes.GENERIC_KILL) &&(!(source.getAttacker() instanceof PlayerEntity) || source.equals(this.getDamageSources().fall()))){
            return false;
        }

        return super.damage(source, amount);
    }
    public static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("animation.eye.attack");
    public static final TrackedData<Integer> NUMBER_OF_CRYSTALS ;
    public static final TrackedData<Integer> CRYSTAL_TIME ;

    static {
        NUMBER_OF_CRYSTALS = DataTracker.registerData(EyeEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CRYSTAL_TIME = DataTracker.registerData(EyeEntity.class, TrackedDataHandlerRegistry.INTEGER);

    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(NUMBER_OF_CRYSTALS, 0);
        this.dataTracker.startTracking(CRYSTAL_TIME, 0);

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
        animationData.add(new AnimationController<EyeEntity>(this, "walk",
                0, this::predicate2)
        );
        animationData.add(
                new AnimationController<>(this, "attack", event -> PlayState.CONTINUE)
                        .triggerableAnim("attack", ATTACK));
    }
    public AnimatableInstanceCache instanceCache = AzureLibUtil.createInstanceCache(this);

        @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if(Registries.STATUS_EFFECT.get(Identifier.of("adventurez","fame")) != null){
            Box box = new Box(this.getBlockPos());
            List<PlayerEntity> list = this.getWorld().getEntitiesByClass(PlayerEntity.class, box.expand(128D), EntityPredicates.EXCEPT_SPECTATOR);
            for (PlayerEntity player : list) {
                if ((PlayerEntity) player != null) {
                    ((PlayerEntity) player).addStatusEffect(new StatusEffectInstance(Registries.STATUS_EFFECT.get(Identifier.of("adventurez", "fame")), 48000, 0, false, false, true));
                }
            }
        }

    }

    private class EyeEntityMoveControl extends MoveControl {
        public EyeEntityMoveControl(EyeEntity owner) {
            super(owner);
        }

        public void tick() {
            if (this.state == State.MOVE_TO) {
                Vec3d vec3d = new Vec3d(this.targetX - EyeEntity.this.getX(), this.targetY - EyeEntity.this.getY(), this.targetZ - EyeEntity.this.getZ());
                double d = vec3d.length();
                if (d < EyeEntity.this.getBoundingBox().getAverageSideLength()) {
                    this.state = State.WAIT;
                    EyeEntity.this.setVelocity(EyeEntity.this.getVelocity().multiply(0.5));
                } else {
                    EyeEntity.this.setVelocity(EyeEntity.this.getVelocity().add(vec3d.multiply(this.speed * 0.05 / d)));
                    if (EyeEntity.this.getTarget() == null) {
                        Vec3d vec3d2 = EyeEntity.this.getVelocity();
                        EyeEntity.this.setYaw(-((float) MathHelper.atan2(vec3d2.x, vec3d2.z)) * 57.295776F);
                        EyeEntity.this.bodyYaw = EyeEntity.this.getYaw();
                    } else {
                        double e = EyeEntity.this.getTarget().getX() - EyeEntity.this.getX();
                        double f = EyeEntity.this.getTarget().getZ() - EyeEntity.this.getZ();
                        EyeEntity.this.setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776F);
                        EyeEntity.this.bodyYaw = EyeEntity.this.getYaw();
                    }
                }

            }
        }
    }
    public class EyeEntityLookControl extends LookControl {
        protected final MobEntity entity;
        protected float maxYawChange;
        protected float maxPitchChange;
        protected int lookAtTimer;
        protected double x;
        protected double y;
        protected double z;

        public EyeEntityLookControl(MobEntity entity) {
            super(entity);
            this.entity = entity;
        }

        public void lookAt(Vec3d direction) {
            this.lookAt(direction.x, direction.y, direction.z);
        }

        public void lookAt(Entity entity) {
            this.lookAt(entity.getX(), getLookingHeightFor(entity), entity.getZ());
        }

        public void lookAt(Entity entity, float maxYawChange, float maxPitchChange) {
            this.lookAt(entity.getX(), getLookingHeightFor(entity), entity.getZ(), maxYawChange, maxPitchChange);
        }

        public void lookAt(double x, double y, double z) {
            this.lookAt(x, y, z, (float)this.entity.getMaxLookYawChange(), (float)this.entity.getMaxLookPitchChange());
        }

        public void lookAt(double x, double y, double z, float maxYawChange, float maxPitchChange) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.maxYawChange = maxYawChange;
            this.maxPitchChange = maxPitchChange;
            this.lookAtTimer = 2;
        }

        public void tick() {
            if (this.shouldStayHorizontal()) {
                this.entity.setPitch(0.0F);
            }

            if (this.lookAtTimer > 0) {
                --this.lookAtTimer;
                this.getTargetYaw().ifPresent((yaw) -> {
                    this.entity.headYaw = this.changeAngle(this.entity.headYaw, yaw, this.maxYawChange);
                });
                this.getTargetPitch().ifPresent((pitch) -> {
                    this.entity.setPitch(this.changeAngle(this.entity.getPitch(), pitch, this.maxPitchChange));
                });
            } else {
                this.entity.headYaw = this.changeAngle(this.entity.headYaw, this.entity.bodyYaw, 10.0F);
            }

            this.clampHeadYaw();
        }

        protected void clampHeadYaw() {
            if (!this.entity.getNavigation().isIdle()) {
                this.entity.headYaw = MathHelper.clampAngle(this.entity.headYaw, this.entity.bodyYaw, (float)this.entity.getMaxHeadRotation());
            }

        }

        protected boolean shouldStayHorizontal() {
            return false;
        }

        public boolean isLookingAtSpecificPosition() {
            return this.lookAtTimer > 0;
        }

        public double getLookX() {
            return this.x;
        }

        public double getLookY() {
            return this.y;
        }

        public double getLookZ() {
            return this.z;
        }

        protected Optional<Float> getTargetPitch() {
            double d = this.x - this.entity.getX();
            double e = this.y - this.entity.getEyeY();
            double f = this.z - this.entity.getZ();
            double g = Math.sqrt(d * d + f * f);
            return !(Math.abs(e) > 9.999999747378752E-6) && !(Math.abs(g) > 9.999999747378752E-6) ? Optional.empty() : Optional.of((float)(-(MathHelper.atan2(e, g) * 57.2957763671875)));
        }

        protected Optional<Float> getTargetYaw() {
            double d = this.x - this.entity.getX();
            double e = this.z - this.entity.getZ();
            return !(Math.abs(e) > 9.999999747378752E-6) && !(Math.abs(d) > 9.999999747378752E-6) ? Optional.empty() : Optional.of((float)(MathHelper.atan2(e, d) * 57.2957763671875) - 90.0F);
        }

        protected float changeAngle(float from, float to, float max) {
            float f = MathHelper.subtractAngles(from, to);
            float g = MathHelper.clamp(f, -max, max);
            return from + g;
        }

        private static double getLookingHeightFor(Entity entity) {
            return entity instanceof LivingEntity ? entity.getEyeY() : (entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0;
        }
    }
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,Configs.Entity.EYE.attributes.MAX_HEALTH)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,Configs.Entity.EYE.attributes.ATTACK_DAMAGE)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,128)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,10)
                .add(EntityAttributes.GENERIC_ARMOR,Configs.Entity.EYE.attributes.ARMOR);
    }


}
