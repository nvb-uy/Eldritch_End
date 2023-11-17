package elocindev.eldritch_end.corruption;

import net.minecraft.item.Item;
import net.minecraft.text.Text;

public class DummyCI extends Item {
    public DummyCI(Settings settings) {
        super(settings);
    }
    
    @Override
    public Text getName() {
        return Text.of("Dummy Corruption Item");
    }
}
