package elocindev.eldritch_end.item.artifacts;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.item.artifacts.base.CorruptionArtifact;
import elocindev.eldritch_end.worldgen.util.TextUtils;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Xalarath extends CorruptionArtifact {
    public static int shadowburst_damage = 10;

    public Xalarath(Settings settings) {
        super(settings);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("item.eldritch_end.xalarath.type"), 1, 0x5c3885, 0x8e4ecf, 1.0F);
        MutableText ability_icon = Text.empty().append("\uA996 ");
        MutableText ability_damage = Text.empty().append(shadowburst_damage+" damage").setStyle(TextUtils.Styles.DAMAGE_CORRUPTION);

        tooltip.add(type.fillStyle(type.getStyle().withUnderline(true)));

        tooltip.add(emptyLine());

        MutableText shadowburst = Text.empty().append("Shadowburst");
        shadowburst.setStyle(TextUtils.Styles.CORRUPTION_ABILITY);
        ability_icon.append(shadowburst);       

        tooltip.add(ability_icon);

        MutableText description1 = Text.translatable("item.eldritch_end.xalarath.shadowburst.1").setStyle(TextUtils.Styles.DESCRIPTION);
        MutableText description2 = Text.translatable("item.eldritch_end.xalarath.shadowburst.2").setStyle(TextUtils.Styles.DESCRIPTION);

        tooltip.add(Text.literal(" ").append(description1).append(Text.literal(" \uA997 ").append(ability_damage)));
        tooltip.add(Text.literal(" ").append(description2));

        tooltip.add(emptyLine());

        MutableText corruption = Text.translatable("item.eldritch_end.xalarath.corruption").setStyle(TextUtils.Styles.CORRUPTION_ABILITY);
        tooltip.add(Text.literal("\uA999 ").append(Text.literal("+10 Corruption").setStyle(TextUtils.Styles.DAMAGE_CORRUPTION)));
        
        tooltip.add(Text.literal("This item is not yet implemented!").setStyle(TextUtils.Styles.NOT_IMPLEMENTED));
    }

    private Text emptyLine() {
        return Text.empty();
    }
}
