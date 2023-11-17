package elocindev.eldritch_end.api;

import java.util.List;

import elocindev.eldritch_end.worldgen.util.TextUtils;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class CorruptionAPI {
    class Icons {
        public static final MutableText RIGHT_CLICK_ABILITY = Text.literal("\uA996 ");
        public static final MutableText CORRUPTION_SCALING = Text.literal("\uA997 ");
        public static final MutableText CORRUPTION_PLUS = Text.literal("\uA998 ");
        public static final MutableText CORRUPTION = Text.literal("\uA999 ");
    }

    public static MutableText getCMenuTitle() {
        return TextAPI.Styles.getStaticGradient(Text.translatable("eldritch_end.corruption.gui.player"), 0x484682, 0x654682);
    }

    public static List<Text> getCMenuTooltip() {
        int corruption = 0;

        MinecraftClient client = MinecraftClient.getInstance();
        MutableText title = ;

        Style descriptionStyle = Style.EMPTY.withColor(0xc95f1c);

        List <Text> base = List.of(
            title.setStyle(title.getStyle().withBold(true)),
            Text.translatable("eldritch_end.corruption.gui.desc.1").setStyle(descriptionStyle),
            Text.translatable("eldritch_end.corruption.gui.desc.2").setStyle(descriptionStyle),
            Text.empty(),
            Icons.CORRUPTION.append(Text.literal(corruption+" Corruption").setStyle(TextUtils.Styles.DAMAGE_CORRUPTION))
        );

        // TODO: ADD SIDE EFFECTS WITH SHIFT TOGGLE

        return base;
    }
}
