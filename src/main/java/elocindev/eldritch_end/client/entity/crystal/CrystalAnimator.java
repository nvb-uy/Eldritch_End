package elocindev.eldritch_end.client.entity.crystal;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.entity.crystal_entity.CrystalEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.util.Identifier;

public class CrystalAnimator extends AzEntityAnimator<CrystalEntity> {
    private static final Identifier ANIMATIONS = new Identifier(EldritchEnd.MODID, "animations/crystal.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<CrystalEntity> container) {
        container.add(AzAnimationController.builder(this, "base").build());
        container.add(AzAnimationController.builder(this, "fall").build());
    }

    @Override
    public Identifier getAnimationLocation(CrystalEntity animatable) {
        return ANIMATIONS;
    }
}
