package elocindev.eldritch_end.entity.tentacle;

import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.registry.EffectRegistry;
import elocindev.eldritch_end.registry.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class UndeadTentacleEntity extends TentacleEntity {
    public UndeadTentacleEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        /*
        if (!this.hasStatusEffect(EffectRegistry.HASTUR_PRESENCE) && this instanceof UndeadTentacleEntity) {
            LivingEntity tentacle = new UndeadTentacleEntity(EntityRegistry.TENTACLE, this.world);
            tentacle.setHealth(this.getHealth());
            this.world.spawnEntity(tentacle);
            this.discard();
            return;
        }

         */
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Configs.ENTITY_TENTACLE.HEALTH_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, Configs.ENTITY_TENTACLE.ATTACK_DAMAGE_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, Configs.ENTITY_TENTACLE.ATTACK_SPEED_ATTRIBUTE)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2);
    }
}
