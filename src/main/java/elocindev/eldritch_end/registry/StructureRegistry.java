package elocindev.eldritch_end.registry;

import elocindev.eldritch_end.EldritchEnd;
import elocindev.eldritch_end.worldgen.structure.TreeGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class StructureRegistry {
    public static StructureType<TreeGenerator> ELDRITCH_END_TREES;

    public static void register() {
        ELDRITCH_END_TREES = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(EldritchEnd.MODID, "tree"), () -> TreeGenerator.CODEC);
    }
}
