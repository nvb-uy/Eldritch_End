package elocindev.eldritch_end.client.entity.hastur;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.hastur.HasturEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class HasturAnimator extends AzEntityAnimator<HasturEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/hastur.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<HasturEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
    }

    @Override
    public Identifier getAnimationLocation(HasturEntity animatable) {
        return ANIMATIONS;
    }
}
