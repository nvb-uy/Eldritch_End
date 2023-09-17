package elocindev.eldritch_end.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;

public class PrimordialLeaves extends LeavesBlock {
    public PrimordialLeaves(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean shouldDecay(BlockState state) {
        return false;
    }
}
