package elocindev.eldritch_end.compat;

import java.util.UUID;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.registry.AttributeRegistry;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class EtyrAttributeBuilder {
    
    public static final TagKey<Item> ETYR_ARMORS = TagKey.of(RegistryKeys.ITEM, new Identifier(EldritchEnd.MODID, "etyr_armor"));

    public static void buildItemTag() {
        ModifyItemAttributeModifiersCallback.EVENT.register((stack, slot, builder) -> {
            if (stack.isIn(ETYR_ARMORS) && stack.getItem() instanceof ArmorItem) {
                if (((ArmorItem)stack.getItem()).getSlotType() != slot) return;

                UUID slotUUID = UUID.randomUUID();

                UUID head = UUID.fromString("697fe278-8564-11ee-b9d1-0242ac120002");
                UUID chest = UUID.fromString("697fe278-8564-11ee-b9d1-0242ac120003");
                UUID legs = UUID.fromString("697fe278-8564-11ee-b9d1-0242ac120004");
                UUID feet = UUID.fromString("697fe278-8564-11ee-b9d1-0242ac120005");
                
                switch (slot) {
                    case HEAD:
                        slotUUID = head;
                        break;
                    case CHEST:
                        slotUUID = chest;
                        break;
                    case LEGS:
                        slotUUID = legs;
                        break;
                    case FEET:
                        slotUUID = feet;
                        break;
                    default:
                        break;
                }
            
            
                builder.put(
                    AttributeRegistry.CORRUPTION_RESISTANCE,
                    new EntityAttributeModifier(
                    slotUUID, 
                    "Corruption resistance modifier", 
                    10.0, 
                    EntityAttributeModifier.Operation.ADDITION
                ));
                    
            }
        });
    } 
}
