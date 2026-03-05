package elocindev.eldritch_end.mixin.worldgen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import elocindev.eldritch_end.entity.dendler.DendlerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(ServerWorld.class)
public class EntFixerMixin {
    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    private void eldritch_end$fixSpawn(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (!(entity instanceof AberrationEntity) && !(entity instanceof DendlerEntity)) return;

        ServerWorld world = (ServerWorld) (Object) this;
        BlockPos pos = entity.getBlockPos().down();

        if (!world.getBlockState(pos).allowsSpawning(world, pos, entity.getType())) {
            info.setReturnValue(false);
        }
    }
}
