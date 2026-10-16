package elocindev.eldritch_end.client.entity.dendler;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.dendler.DendlerEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class DendlerAnimator extends AzEntityAnimator<DendlerEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/dendler.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<DendlerEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
    }

    @Override
    public Identifier getAnimationLocation(DendlerEntity animatable) {
        return ANIMATIONS;
    }
}
