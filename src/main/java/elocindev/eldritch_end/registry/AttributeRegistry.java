package elocindev.eldritch_end.registry;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AttributeRegistry {
    public static final EntityAttribute CORRUPTION = new ClampedEntityAttribute("eldritch_end.corruption", 0.0D, 0.0D, 100.0D).setTracked(true);
    public static final EntityAttribute CORRUPTION_RESISTANCE = new ClampedEntityAttribute("eldritch_end.corruption_resistance", 0.0D, 0.0D, 100.0D).setTracked(true);

    public static void register() {
        Registry.register(Registries.ATTRIBUTE, new Identifier("eldritch_end", "corruption"), CORRUPTION);
        Registry.register(Registries.ATTRIBUTE, new Identifier("eldritch_end", "corruption_resistance"), CORRUPTION_RESISTANCE);
    }
}