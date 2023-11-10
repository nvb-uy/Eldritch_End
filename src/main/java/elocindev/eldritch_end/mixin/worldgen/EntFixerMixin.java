package elocindev.eldritch_end.mixin.worldgen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

@Mixin(ServerWorld.class)
public class EntFixerMixin {
    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    private void eldritch_end$fixSpawn(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (!(entity instanceof AberrationEntity ent)) return;

        if (((ServerWorld)(Object)this).getBlockState(
            new BlockPos(new Vec3i((int)ent.getPos().x, (int)ent.getPos().y, (int)ent.getPos().z)).down(5)
            ).isOf(Blocks.AIR)
        ) info.setReturnValue(false);
    }
}
