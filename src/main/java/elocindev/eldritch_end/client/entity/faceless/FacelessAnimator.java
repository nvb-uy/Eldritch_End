package elocindev.eldritch_end.client.entity.faceless;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.faceless.FacelessEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class FacelessAnimator extends AzEntityAnimator<FacelessEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/faceless.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<FacelessEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
        container.add(AzAnimationController.builder(this, "attackAnim").build());
    }

    @Override
    public Identifier getAnimationLocation(FacelessEntity animatable) {
        return ANIMATIONS;
    }
}
