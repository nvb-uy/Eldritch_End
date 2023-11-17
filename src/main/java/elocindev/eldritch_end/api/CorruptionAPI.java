package elocindev.eldritch_end.api;

import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class CorruptionAPI {
    public class Icons {
        public static final MutableText RIGHT_CLICK_ABILITY = Text.literal("\uA996 ");
        public static final MutableText CORRUPTION_SCALING = Text.literal("\uA997 ");
        public static final MutableText CORRUPTION_PLUS = Text.literal("\uA998 ");
        public static final MutableText CORRUPTION = Text.literal("\uA999 ");
    }

    public static MutableText getCMenuTitle() {
        return TextAPI.Styles.getStaticGradient(Text.translatable("eldritch_end.corruption.gui.player"), 0x484682, 0x654682);
    }
}
