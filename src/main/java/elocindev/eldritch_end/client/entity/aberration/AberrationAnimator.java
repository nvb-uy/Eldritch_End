package elocindev.eldritch_end.client.entity.aberration;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.aberration.AberrationEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class AberrationAnimator extends AzEntityAnimator<AberrationEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/aberration.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<AberrationEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
    }

    @Override
    public Identifier getAnimationLocation(AberrationEntity animatable) {
        return ANIMATIONS;
    }
}
