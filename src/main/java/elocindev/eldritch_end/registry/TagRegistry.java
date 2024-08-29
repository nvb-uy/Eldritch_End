package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagRegistry {
    // Blocks
    public static final TagKey<Block> FACELESS_SUMMON = TagKey.of(RegistryKeys.BLOCK, new Identifier(EldritchEnd.MODID, "faceless_summon"));
}
