package elocindev.eldritch_end.mixin.server;

import elocindev.eldritch_end.WorldSchedulerEldritch;
import elocindev.eldritch_end.compat.SpellEngineCompat;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin({ServerWorld.class})
public abstract class ServerWorldMixin implements WorldSchedulerEldritch {
    private Map<Long, List<Runnable>> scheduledTasks = new HashMap();

    public ServerWorldMixin() {
    }
    @Shadow
    private  ServerEntityManager<Entity> entityManager;


    public long getSchedulerTimeEldritch() {
        World world = (World) (Object) this;
        return (world).getTime();
    }
    @Inject(
            method = {"addEntity"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void addEntityEYE(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(FabricLoader.getInstance().isModLoaded("adventurez") && Configs.Entity.EYE.misc.REPLACE_ADVENTUREZ_EYE){
            if(Registries.ENTITY_TYPE.getId(entity.getType()).equals(Identifier.of("adventurez","the_eye"))){
                if (entity.isRemoved()) {
                    cir.setReturnValue(false);
                    return;
                } else {
                    EyeEntity eye = new EyeEntity(SpellEngineCompat.EYE,entity.getWorld());
                    eye.setPosition(entity.getPos());
                     cir.setReturnValue(this.entityManager.addEntity(eye));
                     return;
                }
            }
        }
    }
    public Map<Long, List<Runnable>> getScheduledTasksEldritch() {
        return this.scheduledTasks;
    }

    @Inject(
            method = {"tick"},
            at = {@At("TAIL")}
    )
    private void tick_TAIL_EldritchEnd(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        this.updateScheduledTasksEldritch();
    }
}