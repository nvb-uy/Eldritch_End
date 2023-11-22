package elocindev.eldritch_end.item.spawneggs;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DendlerEgg extends SpawnEggItem {
    public DendlerEgg(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }
    
    // TODO SOON: ADD INFO ABOUT THE RIDING
    String[] TOOLTIP_NORMAL = {
        ""
    };

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (String line : TOOLTIP_NORMAL) {
            tooltip.add(Text.of(line));
        }
    }
}
