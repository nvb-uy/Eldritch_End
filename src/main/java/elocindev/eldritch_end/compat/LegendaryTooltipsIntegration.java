package elocindev.eldritch_end.compat;

import java.util.List;

import com.anthonyhilyard.legendarytooltips.config.LegendaryTooltipsConfig;
import com.anthonyhilyard.prism.util.ColorUtil;

import elocindev.eldritch_end.EldritchEnd;
import net.minecraft.util.Identifier;

public class LegendaryTooltipsIntegration {
    public static void init() {
        LegendaryTooltipsConfig.INSTANCE.addFrameDefinition(
                new Identifier(EldritchEnd.MODID, "textures/tooltip/tooltip_borders.png"),
                0,
                () -> ColorUtil.combineARGB(255, 117, 77, 176),
                () -> ColorUtil.combineARGB(255, 101, 52, 173),
                () -> ColorUtil.combineARGB(230, 12, 9, 15),
                9999,
                List.of(
                    "eldritch_end:xalarath",
                    "eldritch_end:necronomicon",
                    "eldritch_end:corruption",
                    "eldritch_end:eldritch_pedestal"
                )
            );
    }
}
