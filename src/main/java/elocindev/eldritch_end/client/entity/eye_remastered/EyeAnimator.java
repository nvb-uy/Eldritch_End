package elocindev.eldritch_end.client.entity.eye_remastered;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.eye_remastered.EyeEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class EyeAnimator extends AzEntityAnimator<EyeEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/eye.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<EyeEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
        container.add(AzAnimationController.builder(this, "attack").build());
    }

    @Override
    public Identifier getAnimationLocation(EyeEntity animatable) {
        return ANIMATIONS;
    }
}
