package elocindev.eldritch_end.block.artifact;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.worldgen.util.TextUtils;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EldritchPedestal extends BlockItem {

    public EldritchPedestal(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("block.eldritch_end.eldritch_pedestal.type"), 1, 0x5c3885, 0x446e5c, 1.0F);
        MutableText ability_icon = Text.empty().append("\uB996 ");
        MutableText ability = Text.empty().append("Summoning Altar").setStyle(TextUtils.Styles.CORRUPTION_ABILITY);

        tooltip.add(type.fillStyle(type.getStyle().withUnderline(true)));

        tooltip.add(emptyLine());

        tooltip.add(ability_icon.append(ability));
        //tooltip.add(Text.literal("This item is not yet implemented!").setStyle(TextUtils.Styles.NOT_IMPLEMENTED));
    }

    private Text emptyLine() {
        return Text.empty();
    }
}
