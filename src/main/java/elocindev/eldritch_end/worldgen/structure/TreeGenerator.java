package elocindev.eldritch_end.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import elocindev.eldritch_end.registry.StructureRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class TreeGenerator extends Structure {
    public static final Codec<TreeGenerator> CODEC = RecordCodecBuilder.<TreeGenerator>mapCodec(instance ->
            instance.group(TreeGenerator.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, TreeGenerator::new)).codec();

    private final RegistryEntry<StructurePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Type> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public TreeGenerator(Structure.Config config,
                         RegistryEntry<StructurePool> startPool,
                         Optional<Identifier> startJigsawName,
                         int size,
                         HeightProvider startHeight,
                         Optional<Heightmap.Type> projectStartToHeightmap,
                         int maxDistanceFromCenter)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    @Override
    public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {      
        int startY = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));

        ChunkPos chunkPos = context.chunkPos();
        BlockPos origin = new BlockPos(chunkPos.getStartX(), startY, chunkPos.getStartZ());
        
        Optional<StructureTemplate> template = context.structureTemplateManager().getTemplate(new Identifier("eldritch_end", "primordial_tree_big/variation_1"));
        
        BlockPos halfLengths = new BlockPos(
                template.get().getSize().getX() / 2,
                template.get().getSize().getY() / 2,
                template.get().getSize().getZ() / 2);

        BlockPos.Mutable mutable = new BlockPos.Mutable().set(origin);
        BlockPos position = new BlockPos(origin.getX(), startY, origin.getZ());
    
        mutable.set(position).move(-halfLengths.getX(), 0, -halfLengths.getZ());

        BlockPos finalPos = new BlockPos(
            mutable.getX(),                
            context.chunkGenerator().getHeightInGround(
                mutable.getX(),
                mutable.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG,
                context.world(),
                context.noiseConfig()),
            mutable.getZ()
        );

        // if (context.chunkGenerator().getHeightInGround(
        //         mutable.getX(),
        //         mutable.getZ(),
        //         Heightmap.Type.WORLD_SURFACE_WG,
        //         context.world(),
        //         context.noiseConfig()) < 5) {
        //     return Optional.empty();
        // }

        Optional<StructurePosition> structurePiecesGenerator =
                TreePoolGenerator.generate(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.size,
                        finalPos,
                        false,
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter);

        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> getType() {
        return StructureRegistry.ELDRITCH_END_TREES;
    }
}