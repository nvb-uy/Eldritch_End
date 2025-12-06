package elocindev.eldritch_end.api.targeting;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.registry.Registries;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class TargetHelper {
    public static float launchPointOffsetDefault = 0.5F;

    private static final boolean[][] TABLE_OF_ULTIMATE_JUSTICE = new boolean[][]{{false, true, true, true, true}, {false, false, false, true, true}, {true, true, true, false, true}, {true, true, false, false, true}};
    public static Vec3d launchPoint(LivingEntity caster) {
        return launchPoint(caster, launchPointOffsetDefault);
    }

    public static Vec3d launchPoint(LivingEntity caster, float forward) {
        Vec3d look = caster.getRotationVector().multiply((double)(forward * caster.getScaleFactor()));
        return caster.getPos().add(0.0, (double)launchHeight(caster), 0.0).add(look);
    }
    public static float launchHeight(LivingEntity livingEntity) {
        float eyeHeight = livingEntity.getStandingEyeHeight();
        double shoulderDistance = (double)livingEntity.getHeight() * 0.15;
        return (float)(((double)eyeHeight - shoulderDistance) * (double)livingEntity.getScaleFactor());
    }
    public TargetHelper() {
    }

    public static Relation getRelation(LivingEntity attacker, Entity target) {
        if (attacker == target) {
            return Relation.FRIENDLY;
        } else {
            AbstractTeam casterTeam = attacker.getScoreboardTeam();
            AbstractTeam targetTeam = target.getScoreboardTeam();
            if (target instanceof Tameable) {
                Tameable tameable = (Tameable) target;
                LivingEntity owner = tameable.getOwner();
                if (owner != null) {
                    return getRelation(attacker, owner);
                }
            }

            if (target instanceof AbstractDecorationEntity) {
                return Relation.NEUTRAL;
            } else {
                if (casterTeam != null && targetTeam != null) {
                    return attacker.isTeammate(target) ? Relation.FRIENDLY : Relation.HOSTILE;
                } else {
                    Identifier id = Registries.ENTITY_TYPE.getId(target.getType());
                    Relation mappedRelation = Relation.NEUTRAL;
                    if (mappedRelation != null) {
                        return mappedRelation;
                    } else if (target instanceof PassiveEntity) {
                        return Relation.coalesce(Relation.NEUTRAL, Relation.HOSTILE);
                    } else {
                        return target instanceof HostileEntity ? Relation.coalesce(Relation.HOSTILE, Relation.HOSTILE) : Relation.coalesce(Relation.NEUTRAL, Relation.HOSTILE);
                    }
                }
            }
        }
    }

    public static boolean actionAllowed(TargetingMode targetingMode, Intent intent, LivingEntity attacker, Entity target) {
        Relation relation = getRelation(attacker, target);
        int row = 0;
        if (intent == Intent.HELPFUL) {
            row += 2;
        }

        if (targetingMode == TargetingMode.AREA) {
            ++row;
        }

        int column = 0;
        switch (relation) {
            case FRIENDLY:
                column = 0;
                break;
            case SEMI_FRIENDLY:
                column = 1;
                break;
            case NEUTRAL:
                column = 2;
                break;
            case HOSTILE:
                column = 3;
                break;
            case MIXED:
                column = 4;
        }

        return TABLE_OF_ULTIMATE_JUSTICE[row][column];
    }

    public static boolean allowedToHurt(Entity e1, Entity e2) {
        AbstractTeam abstractTeam = e1.getScoreboardTeam();
        AbstractTeam abstractTeam2 = e2.getScoreboardTeam();
        if (abstractTeam == null) {
            return true;
        } else {
            return !abstractTeam.isEqual(abstractTeam2) || abstractTeam.isFriendlyFireAllowed();
        }
    }

    public static Entity targetFromRaycast(Entity caster, float range, Predicate<Entity> predicate) {
        Vec3d start = caster.getEyePos();
        Vec3d look = caster.getRotationVec(1.0F).normalize().multiply((double) range);
        Vec3d end = start.add(look);
        Box searchAABB = caster.getBoundingBox().expand((double) range, (double) range, (double) range);
        EntityHitResult hitResult = ProjectileUtil.raycast(caster, start, end, searchAABB, (target) -> {
            return !target.isSpectator() && target.canHit() && predicate.test(target);
        }, (double) (range * range));
        return hitResult == null || hitResult.getPos() != null && !raycastObstacleFree(caster, start, hitResult.getPos()) ? null : hitResult.getEntity();
    }

    public static List<Entity> targetsFromRaycast(Entity caster, float range, Predicate<Entity> predicate) {
        Vec3d start = caster.getEyePos();
        Vec3d look = caster.getRotationVec(1.0F).normalize().multiply((double) range);
        Vec3d end = start.add(look);
        Box searchAABB = caster.getBoundingBox().expand((double) range, (double) range, (double) range);
        List<EntityHit> entitiesHit = raycastMultiple(caster, start, end, searchAABB, (target) -> {
            return !target.isSpectator() && target.canHit() && predicate.test(target);
        }, (double) (range * range));
        return entitiesHit.stream().filter((hit) -> {
            return hit.position() == null || raycastObstacleFree(caster, start, hit.position());
        }).sorted(new Comparator<EntityHit>() {
            public int compare(EntityHit hit1, EntityHit hit2) {
                if (hit1.squaredDistanceToSource == hit2.squaredDistanceToSource) {
                    return 0;
                } else {
                    return hit1.squaredDistanceToSource < hit2.squaredDistanceToSource ? -1 : 1;
                }
            }
        }).map((hit) -> {
            return hit.entity;
        }).toList();
    }

    private static @Nullable List<EntityHit> raycastMultiple(Entity sourceEntity, Vec3d min, Vec3d max, Box searchBox, Predicate<Entity> predicate, double squaredDistance) {
        World world = sourceEntity.getWorld();
        double e = squaredDistance;
        List<EntityHit> entities = new ArrayList();
        Vec3d vec3d = null;
        Iterator var12 = world.getOtherEntities(sourceEntity, searchBox, predicate).iterator();

        while (true) {
            while (var12.hasNext()) {
                Entity entity = (Entity) var12.next();
                Box box2 = entity.getBoundingBox().expand((double) entity.getTargetingMargin());
                Optional<Vec3d> raycastResult = box2.raycast(min, max);
                if (box2.contains(min)) {
                    if (e >= 0.0) {
                        vec3d = (Vec3d) raycastResult.orElse(min);
                        entities.add(new EntityHit(entity, vec3d, 0.0));
                        e = 0.0;
                    }
                } else {
                    Vec3d hitPosition;
                    if (raycastResult.isPresent() && (min.squaredDistanceTo(hitPosition = (Vec3d) raycastResult.get()) < e || e == 0.0)) {
                        if (entity.getRootVehicle() == sourceEntity.getRootVehicle()) {
                            if (e == 0.0) {
                                entities.add(new EntityHit(entity, hitPosition, entity.squaredDistanceTo(sourceEntity)));
                            }
                        } else {
                            entities.add(new EntityHit(entity, hitPosition, entity.squaredDistanceTo(sourceEntity)));
                        }
                    }
                }
            }

            return entities;
        }
    }

    public static List<Entity> targetsFromArea(Entity caster, float range, Area area, @Nullable Predicate<Entity> predicate) {
        Vec3d origin = caster.getEyePos();
        return targetsFromArea(caster, origin, range, area, predicate);
    }
    public static class Area {
        public float horizontal_range_multiplier;
        public float vertical_range_multiplier;
        public float angle_degrees;
        public boolean include_caster;

        public Area() {
            this.horizontal_range_multiplier = 1.0F;
            this.vertical_range_multiplier = 1.0F;
            this.angle_degrees = 0.0F;
            this.include_caster = false;
        }
    }

    public static List<Entity> targetsFromArea(Entity centerEntity, Vec3d origin, float range, Area area, @Nullable Predicate<Entity> predicate) {
        float horizontal = range * area.horizontal_range_multiplier;
        float vertical = range * area.vertical_range_multiplier;
        Box box = centerEntity.getBoundingBox().expand((double) (horizontal + 0.5F), (double) (vertical + 0.5F), (double) (horizontal + 0.5F));
        float squaredDistance = range * range;
        Vec3d look = centerEntity.getRotationVector();
        float angle = area.angle_degrees / 2.0F;
        return centerEntity.getWorld().getOtherEntities(centerEntity, box, (target) -> {
            Vec3d targetCenter = target.getPos().add(0.0, (double) (target.getHeight() / 2.0F), 0.0);
            Vec3d distanceVector = VectorHelper.distanceVector(origin, target.getBoundingBox());
            boolean var10000;
            if (!target.isSpectator() && target.canHit() && (predicate == null || predicate.test(target))) {
                label48:
                {
                    if (range > 1.0F) {
                        if (!(targetCenter.squaredDistanceTo(origin) <= (double) squaredDistance)) {
                            break label48;
                        }
                    } else if (!(distanceVector.length() <= (double) range)) {
                        break label48;
                    }

                    if ((angle <= 0.0F || VectorHelper.angleBetween(look, targetCenter.subtract(origin)) <= (double) angle || VectorHelper.angleBetween(look, distanceVector) <= (double) angle) && (range < 1.0F || raycastObstacleFree(centerEntity, origin, targetCenter) || raycastObstacleFree(centerEntity, origin, origin.add(distanceVector)))) {
                        var10000 = true;
                        return var10000;
                    }
                }
            }

            var10000 = false;
            return var10000;
        });
    }

    public static boolean isInLineOfSight(Entity attacker, Entity target) {
        Vec3d origin = attacker.getEyePos();
        Vec3d targetCenter = target.getPos().add(0.0, (double) (target.getHeight() / 2.0F), 0.0);
        Vec3d distanceVector = VectorHelper.distanceVector(origin, target.getBoundingBox());
        return raycastObstacleFree(attacker, origin, targetCenter) || raycastObstacleFree(attacker, origin, origin.add(distanceVector));
    }

    private static boolean raycastObstacleFree(Entity entity, Vec3d start, Vec3d end) {
        BlockHitResult hit = entity.getWorld().raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity));
        return hit.getType() != HitResult.Type.BLOCK;
    }





    public static @Nullable Vec3d findSolidBlockBelow(LivingEntity entity, Vec3d position, World world, float height) {
        BlockHitResult hit = world.raycast(new RaycastContext(position, position.add(0.0, (double) height, 0.0), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity));
        return hit.getType() == HitResult.Type.BLOCK ? new Vec3d(position.getX(), (double) ((float) hit.getBlockPos().getY() + 1.0F), position.getZ()) : null;
    }

    public static @Nullable Vec3d findTeleportDestination(LivingEntity entity, Vec3d look, float distance, int clearanceY) {
        World world = entity.getWorld();
        Vec3d start = entity.getEyePos();
        Vec3d end = start.add(look.multiply((double) distance));
        BlockHitResult hit = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity));
        Vec3d hitPosition = null;
        if (hit.getType() == HitResult.Type.MISS) {
            hitPosition = end;
        }

        if (hit.getType() == HitResult.Type.BLOCK && hit.getBlockPos() != null) {
            hitPosition = hit.getPos();
        }

        if (hitPosition != null) {
            Vec3d inverseLook = look.multiply(-1.0);
            Vec3d paddedHitPosition = hitPosition.add(inverseLook.multiply(0.5));
            double hitDistance = start.distanceTo(paddedHitPosition);

            for (float reverted = 0.0F; (double) reverted < hitDistance; paddedHitPosition = paddedHitPosition.add(inverseLook)) {
                BlockPos blockPos = new BlockPos((int) paddedHitPosition.getX(), (int) paddedHitPosition.getY(), (int) paddedHitPosition.getZ());
                if (isSafeWithClearance(world, blockPos, clearanceY)) {
                    return paddedHitPosition;
                }

                ++reverted;
            }
        }

        return null;
    }

    private static boolean isSafeWithClearance(World world, BlockPos blockPos, int clearanceY) {
        if (!isSafeTeleportDestination(world, blockPos)) {
            return false;
        } else {
            boolean clearanceSafe = true;

            for (int i = 0; i < clearanceY; ++i) {
                BlockPos clearancePos = blockPos.up(i);
                if (!isSafeTeleportDestination(world, clearancePos)) {
                    clearanceSafe = false;
                    break;
                }
            }

            return clearanceSafe;
        }
    }

    private static boolean isSafeTeleportDestination(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return !state.isSolid() && !state.shouldSuffocate(world, pos);
    }

    public static enum Relation {
        FRIENDLY,
        SEMI_FRIENDLY,
        NEUTRAL,
        HOSTILE,
        MIXED;

        private Relation() {
        }

        public static Relation coalesce(Relation value, Relation fallback) {
            return value != null ? value : fallback;
        }
    }

    public static enum Intent {
        HELPFUL,
        HARMFUL;

        private Intent() {
        }
    }

    public static enum TargetingMode {
        DIRECT,
        AREA;

        private TargetingMode() {
        }
    }

    private static record EntityHit(Entity entity, Vec3d position, double squaredDistanceToSource) {
        private EntityHit(Entity entity, Vec3d position, double squaredDistanceToSource) {
            this.entity = entity;
            this.position = position;
            this.squaredDistanceToSource = squaredDistanceToSource;
        }

        public Entity entity() {
            return this.entity;
        }

        public Vec3d position() {
            return this.position;
        }

        public double squaredDistanceToSource() {
            return this.squaredDistanceToSource;
        }
    }

}
