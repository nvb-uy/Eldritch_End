package elocindev.eldritch_end.entity.eye_remastered;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.compat.SpellEngineCompat;
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
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.spell_engine.api.spell.ParticleBatch;
import net.spell_engine.api.spell.Sound;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.SpellInfo;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.internals.WorldScheduler;
import net.spell_engine.network.Packets;
import net.spell_engine.particle.Particles;
import net.spell_engine.utils.SoundHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellPower;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

import java.util.*;

import static java.lang.StrictMath.asin;
import static java.lang.StrictMath.atan2;
import static net.spell_power.api.SpellSchools.*;

public class EyeEntity extends HostileEntity implements GeoEntity {
    public static ParticleBatch glyph_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(Particles.snowflake.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.2F,0.2F,angle,0,45,false);
        }

        if(school.equals(FIRE)){
            return  new ParticleBatch(Particles.flame.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.2F,0.2F,angle,0,45,false);
        }
        return  new ParticleBatch(Particles.arcane_spell.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,45,false);

    }
    public static ParticleBatch glyph_outer_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(Particles.frost_shard.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,200,false);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(Particles.flame_medium_a.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,200,false);
        }
        return  new ParticleBatch(Particles.arcane_spell.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,200,false);

    }
    public static ParticleBatch glyph_center_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(Particles.frost_shard.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,350,false);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(Particles.flame_spark.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,350,false);
        }
        return  new ParticleBatch(Particles.arcane_hit.id.toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,200,0.02F,0.02F,angle,0,350,false);

    }
    private boolean performing = false;
    private int teleporttimer = 80;
    private int missiletimer = 0;
    private int lasertimer = 0;
    private int crystaltimer = 160;

    private boolean teleporting;
    private int teleportduration;
    private Vec3d teleportLocation;

    public EyeEntity(EntityType<? extends EyeEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new EyeEntityMoveControl(this);
        this.lookControl = new EyeEntityLookControl(this);

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
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[]{EyeEntity.class})));
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
        super.tick();
    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        if(type.equals(SpellEngineCompat.CRYSTAL)){
            return false;
        }
        return super.canTarget(type);
    }

    @Override
    protected void mobTick() {
        if(!this.getWorld().isClient() && crystaltimer > 200 && !this.teleporting &&  !this.performing && this.getTarget() != null ) {
            Spell.Release.Target.Area area = new Spell.Release.Target.Area();
            area.angle_degrees = 120;
            List<Entity> list = TargetHelper.targetsFromArea(this,64,area, entity ->  entity instanceof LivingEntity && !(entity instanceof CrystalEntity));
            int ii = 0;
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

                        }
                        SoundHelper.playSound(this.getWorld(),this,SpellRegistry.getSpell(new Identifier(EldritchEnd.MODID,"arcane_missile")).release.sound);
                    }

                }

            crystaltimer = 0;
        }
        if(!this.getWorld().isClient() && missiletimer > 120 && !this.teleporting &&  !this.performing && this.getTarget() != null ) {
            Spell.Release.Target.Area area = new Spell.Release.Target.Area();
            area.angle_degrees = 120;
            List<Entity> list = TargetHelper.targetsFromArea(this,64,area, entity ->  entity instanceof LivingEntity && !(entity instanceof CrystalEntity));
            for(int i = 0; i < 3; i++){
                int ii = 0;
                for(Entity entity: list) {
                    int finalI = i;

                    ((WorldScheduler) this.getWorld()).schedule((ii * 2) + 1, () -> {

                                ((WorldScheduler) this.getWorld()).schedule((finalI * 5) + 5, () -> {
                                    if (entity != null) {
                                        SpellHelper.shootProjectile(this.getWorld(), this, entity, new SpellInfo(SpellRegistry.getSpell(Identifier.of(EldritchEnd.MODID, "arcane_missile")), Identifier.of(EldritchEnd.MODID, "arcane_missile"))
                                                , new SpellHelper.ImpactContext().power(SpellPower.getSpellPower(SpellSchools.ARCANE, this)).position(this.getPos()));
                                        sendBatches(this,new ParticleBatch[]{glyph_center_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
                                        sendBatches(this,new ParticleBatch[]{glyph_outer_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);
                                        sendBatches(this,new ParticleBatch[]{glyph_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, ARCANE,true)},this.getYaw(),this.getPitch(),1,PlayerLookup.tracking(this),false);

                                        SoundHelper.playSound(this.getWorld(),this,SpellRegistry.getSpell(new Identifier(EldritchEnd.MODID,"arcane_missile")).release.sound);

                                    }

                                });
                            }
                    );
                    ii++;
                }
            }
            missiletimer = 0;
        }
        if(!this.getWorld().isClient() && lasertimer > 160 && !this.teleporting &&  !this.performing && this.getTarget() != null ) {
            Spell.Release.Target.Area area = new Spell.Release.Target.Area();
            area.angle_degrees = 120;
            List<Entity> list = TargetHelper.targetsFromArea(this,64,area, entity ->  entity instanceof LivingEntity && !(entity instanceof CrystalEntity));
            for(int i = 0; i < 3; i++){
                int ii = 0;
                for(Entity entity: list) {
                    int finalI = i;


                        Vec3d pos = entity.getBoundingBox().getCenter();
                    ((WorldScheduler) this.getWorld()).schedule((i * 5) + 5, () -> {
                        lineParticles(this,pos,pos,64);
                    });
                    ((WorldScheduler) this.getWorld()).schedule((ii * 2) + 1, () -> {

                                ((WorldScheduler) this.getWorld()).schedule((finalI * 10) + 20, () -> {
                                    if (entity != null) {
                                        Spell.Release.Target.Area area2 = new Spell.Release.Target.Area();
                                        this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES,pos);
                                        area.angle_degrees = 10;

                                        List<Entity> list2 = TargetHelper.targetsFromArea(this,64,area, entity2 ->  entity2 instanceof LivingEntity );
                                        for(Entity entity1 : list2){
                                            SpellHelper.performImpacts(this.getWorld(),this,entity1,entity1,new SpellInfo(SpellRegistry.getSpell(Identifier.of(EldritchEnd.MODID, "arcane_laser")), Identifier.of(EldritchEnd.MODID, "arcane_laser"))
                                                    ,new SpellHelper.ImpactContext().power(SpellPower.getSpellPower(SpellSchools.ARCANE, this)).position(this.getPos()),false);
                                        }
                                        Vec3d vec = pos.subtract(this.getBoundingBox().getCenter()).normalize();
                                        float pitch = (float) (180*asin(-(vec.y))/Math.PI);
                                        float yaw = (float) (-180*atan2(vec.x, vec.z)/Math.PI);
                                        if ( this.getWorld() instanceof ServerWorld world) {


                                            sendBatches(this,SpellRegistry.getSpell(new Identifier(EldritchEnd.MODID,"arcane_laser")).release.particles,yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{glyph_center_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, FIRE,true)},yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{glyph_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, FIRE,true)},yaw,pitch,1,PlayerLookup.tracking(this),false);
                                            sendBatches(this,new ParticleBatch[]{glyph_outer_release(1,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, FIRE,true)},yaw,pitch,1,PlayerLookup.tracking(this),false);

                                            SoundHelper.playSound(this.getWorld(),this,SpellRegistry.getSpell(new Identifier(EldritchEnd.MODID,"arcane_laser")).release.sound);

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
        if(!this.getWorld().isClient() && teleporttimer > 200 && !this.teleporting &&  !this.performing && this.getTarget() != null ) {

            Vec3d randomLocation = randomLocation(this.getTarget(),64);
            if(this.getTarget() != null) {
                this.teleportLocation = randomLocation;
                this.teleporting = true;
                this.teleportduration = 0;
                this.performing = true;

            }
            this.teleporttimer = this.getRandom().nextInt(80);
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
                    return SpellHelper.launchPoint(livingEntity);
                }

                return entity.getPos().add(0.0, (double)(entity.getHeight() * 0.5F), 0.0);
            default:
                return entity.getPos();
        }
    }

    public static void sendBatches(Entity trackedEntity, ParticleBatch[] batches, float yaw, float pitch, float countMultiplier, Collection<ServerPlayerEntity> trackers, boolean includeSourceEntity) {
        if (batches != null && batches.length != 0) {
            int sourceEntityId = trackedEntity.getId();
            Packets.ParticleBatches.SourceType sourceType = Packets.ParticleBatches.SourceType.COORDINATE;
            ArrayList<Packets.ParticleBatches.Spawn> spawns = new ArrayList();
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

                spawns.add(new Packets.ParticleBatches.Spawn(includeSourceEntity ? sourceEntityId : 0, yaw,pitch, sourceLocation, batch));
            }

            PacketByteBuf packet = (new Packets.ParticleBatches(sourceType, spawns)).write(countMultiplier);
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
            if (ServerPlayNetworking.canSend(serverPlayer, Packets.ParticleBatches.ID)) {
                ServerPlayNetworking.send(serverPlayer, Packets.ParticleBatches.ID, packet);
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
                    System.out.println(new Vec3d(e,f,g));
                    break;
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
        if(source.equals(this.getDamageSources().fall())){
            return false;
        }
        return super.damage(source, amount);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar animationData) {
        animationData.add(new AnimationController<EyeEntity>(this, "walk",
                0, this::predicate2)
        );
    }
    public AnimatableInstanceCache instanceCache = AzureLibUtil.createInstanceCache(this);

        @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
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

}
