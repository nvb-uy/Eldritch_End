package elocindev.eldritch_end.api.infusion;

import java.util.UUID;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

public class InfusionAttributeHolder {
    public class Presets {
        public static final UUID CORRUPTION = UUID.fromString("d0ba544b-c712-44ae-9a1e-447067ede38e");
        public static final UUID ETYR = UUID.fromString("5706e0df-3976-427f-b0b2-b2b99a586572");
    }

    public EntityAttribute attribute;
    public Operation operation;
    public double amount;
    public UUID uuid;

    public InfusionAttributeHolder(EntityAttribute attribute, Operation operation, double amount, UUID uuid) {
        this.attribute = attribute;
        this.operation = operation;
        this.amount = amount;
        this.uuid = uuid;
    }

    public InfusionAttributeHolder(EntityAttribute attribute, double amount, UUID uuid) {
        this(attribute, Operation.ADDITION, amount, uuid);
    }
}
