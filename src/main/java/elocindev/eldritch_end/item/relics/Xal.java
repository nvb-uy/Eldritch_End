package elocindev.eldritch_end.item.relics;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import elocindev.eldritch_end.item.relics.base.CorruptionRelic;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.worldgen.util.TextUtils;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Xal extends CorruptionRelic {
    public static int shadowburst_damage = 10;

    public Xal(Settings settings) {
        super(settings);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("item.eldritch_end.xal.type"), 1, 0x5c3885, 0x8e4ecf, 1.0F);
        MutableText ability_icon = Text.empty().append("\uA996 ");
        MutableText ability_damage = Text.empty().append(shadowburst_damage+" damage").setStyle(TextUtils.Styles.DAMAGE_CORRUPTION);

        tooltip.add(type.fillStyle(type.getStyle().withUnderline(true)));

        tooltip.add(emptyLine());

        MutableText shadowburst = Text.empty().append("Shadowburst");
        shadowburst.setStyle(TextUtils.Styles.CORRUPTION_ABILITY);
        ability_icon.append(shadowburst);       

        tooltip.add(ability_icon);

        MutableText description1 = Text.translatable("item.eldritch_end.xal.shadowburst.1").setStyle(TextUtils.Styles.DESCRIPTION);
        MutableText description2 = Text.translatable("item.eldritch_end.xal.shadowburst.2").setStyle(TextUtils.Styles.DESCRIPTION);

        tooltip.add(Text.literal(" ").append(description1).append(Text.literal(" \uA997 ").append(ability_damage)));
        tooltip.add(Text.literal(" ").append(description2));

        tooltip.add(emptyLine());
    }

    private Text emptyLine() {
        return Text.literal(" ");
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(super.getAttributeModifiers(slot));
        
        UUID uuid = UUID.fromString("399fe278-8564-11ee-b9d1-0242ac120002");

        if (slot == EquipmentSlot.MAINHAND)
            modifiers.put(
                AttributeRegistry.CORRUPTION,
                new EntityAttributeModifier(
                    uuid, 
                    "Corruption modifier", 
                    10.0, 
                    EntityAttributeModifier.Operation.ADDITION
                )
            );
        
        return modifiers;
    }
}
