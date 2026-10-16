package elocindev.eldritch_end.client.entity.tentacle;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.tentacle.TentacleEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class TentacleAnimator extends AzEntityAnimator<TentacleEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/tentacle.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<TentacleEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
        container.add(AzAnimationController.builder(this, "attack").build());
    }

    @Override
    public Identifier getAnimationLocation(TentacleEntity animatable) {
        return ANIMATIONS;
    }
}
