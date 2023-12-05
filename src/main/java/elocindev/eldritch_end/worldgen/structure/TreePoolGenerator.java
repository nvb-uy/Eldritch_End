package elocindev.eldritch_end.worldgen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.mojang.logging.LogUtils;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.block.JigsawBlock;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.JigsawJunction;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.Structure;
import org.apache.commons.lang3.mutable.MutableObject;
import org.slf4j.Logger;

public class TreePoolGenerator {
    static final Logger LOGGER = LogUtils.getLogger();

    public TreePoolGenerator() {
    }

    public static Optional<Structure.StructurePosition> generate(Structure.Context context, RegistryEntry<StructurePool> structurePool, Optional<Identifier> id, int size, BlockPos pos, boolean useExpansionHack, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter) {
        DynamicRegistryManager dynamicRegistryManager = context.dynamicRegistryManager();
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        StructureTemplateManager structureTemplateManager = context.structureTemplateManager();
        HeightLimitView heightLimitView = context.world();
        ChunkRandom chunkRandom = context.random();
        Registry<StructurePool> registry = dynamicRegistryManager.get(RegistryKeys.TEMPLATE_POOL);
        BlockRotation blockRotation = BlockRotation.NONE;
        StructurePool structurePool2 = (StructurePool)structurePool.value();
        StructurePoolElement structurePoolElement = structurePool2.getRandomElement(chunkRandom);
        if (structurePoolElement == EmptyPoolElement.INSTANCE) {
            return Optional.empty();
        } else {
            BlockPos blockPos;
            if (id.isPresent()) {
                Identifier identifier = (Identifier)id.get();
                Optional<BlockPos> optional = findStartingJigsawPos(structurePoolElement, identifier, pos, blockRotation, structureTemplateManager, chunkRandom);
                if (optional.isEmpty()) {
                    LOGGER.error("No starting jigsaw {} found in start pool {}", identifier, structurePool.getKey().map((key) -> {
                        return key.getValue().toString();
                    }).orElse("<unregistered>"));
                    return Optional.empty();
                }

                blockPos = (BlockPos)optional.get();
            } else {
                blockPos = pos;
            }

            Vec3i vec3i = blockPos.subtract(pos);
            BlockPos blockPos2 = pos.subtract(vec3i);
            PoolStructurePiece poolStructurePiece = new PoolStructurePiece(structureTemplateManager, structurePoolElement, blockPos2, structurePoolElement.getGroundLevelDelta(), blockRotation, structurePoolElement.getBoundingBox(structureTemplateManager, blockPos2, blockRotation));
            BlockBox blockBox = poolStructurePiece.getBoundingBox();
            int i = (blockBox.getMaxX() + blockBox.getMinX()) / 2;
            int j = (blockBox.getMaxZ() + blockBox.getMinZ()) / 2;
            int k;
            if (projectStartToHeightmap.isPresent()) {
                k = pos.getY() + chunkGenerator.getHeightOnGround(i, j, (Heightmap.Type)projectStartToHeightmap.get(), heightLimitView, context.noiseConfig());
            } else {
                k = blockPos2.getY();
            }

            int bX = Math.abs(blockBox.getBlockCountX() / 2);

            int bZ = Math.abs(blockBox.getBlockCountZ() / 2);

            int l = blockBox.getMinY() + poolStructurePiece.getGroundLevelDelta();
            poolStructurePiece.translate(-bX, k - l, -bZ);
            int m = k + vec3i.getY();
            return Optional.of(new Structure.StructurePosition(new BlockPos(i, m, j), (collector) -> {
                List<PoolStructurePiece> list = Lists.newArrayList();
                list.add(poolStructurePiece);
                if (size > 0) {
                    Box box = new Box((double)(i - maxDistanceFromCenter), (double)(m - maxDistanceFromCenter), (double)(j - maxDistanceFromCenter), (double)(i + maxDistanceFromCenter + 1), (double)(m + maxDistanceFromCenter + 1), (double)(j + maxDistanceFromCenter + 1));
                    VoxelShape voxelShape = VoxelShapes.combineAndSimplify(VoxelShapes.cuboid(box), VoxelShapes.cuboid(Box.from(blockBox)), BooleanBiFunction.ONLY_FIRST);
                    generate(context.noiseConfig(), size, useExpansionHack, chunkGenerator, structureTemplateManager, heightLimitView, chunkRandom, registry, poolStructurePiece, list, voxelShape);
                    Objects.requireNonNull(collector);
                    list.forEach(collector::addPiece);
                }
            }));
        }
    }

    private static Optional<BlockPos> findStartingJigsawPos(StructurePoolElement pool, Identifier id, BlockPos pos, BlockRotation rotation, StructureTemplateManager structureManager, ChunkRandom random) {
        List<StructureTemplate.StructureBlockInfo> list = pool.getStructureBlockInfos(structureManager, pos, rotation, random);
        Optional<BlockPos> optional = Optional.empty();
        Iterator var8 = list.iterator();

        while(var8.hasNext()) {
            StructureTemplate.StructureBlockInfo structureBlockInfo = (StructureTemplate.StructureBlockInfo)var8.next();
            Identifier identifier = Identifier.tryParse(structureBlockInfo.nbt().getString("name"));
            if (id.equals(identifier)) {
                optional = Optional.of(structureBlockInfo.pos());
                break;
            }
        }

        return optional;
    }

    private static void generate(NoiseConfig noiseConfig, int maxSize, boolean modifyBoundingBox, ChunkGenerator chunkGenerator, StructureTemplateManager structureTemplateManager, HeightLimitView heightLimitView, Random random, Registry<StructurePool> structurePoolRegistry, PoolStructurePiece firstPiece, List<PoolStructurePiece> pieces, VoxelShape pieceShape) {
        StructurePoolGenerator structurePoolGenerator = new StructurePoolGenerator(structurePoolRegistry, maxSize, chunkGenerator, structureTemplateManager, pieces, random);
        structurePoolGenerator.structurePieces.addLast(new ShapedPoolStructurePiece(firstPiece, new MutableObject(pieceShape), 0));

        while(!structurePoolGenerator.structurePieces.isEmpty()) {
            ShapedPoolStructurePiece shapedPoolStructurePiece = (ShapedPoolStructurePiece)structurePoolGenerator.structurePieces.removeFirst();
            structurePoolGenerator.generatePiece(shapedPoolStructurePiece.piece, shapedPoolStructurePiece.pieceShape, shapedPoolStructurePiece.currentSize, modifyBoundingBox, heightLimitView, noiseConfig);
        }

    }

    public static boolean generate(ServerWorld world, RegistryEntry<StructurePool> structurePool, Identifier id, int size, BlockPos pos, boolean keepJigsaws) {
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        StructureTemplateManager structureTemplateManager = world.getStructureTemplateManager();
        StructureAccessor structureAccessor = world.getStructureAccessor();
        Random random = world.getRandom();
        Structure.Context context = new Structure.Context(world.getRegistryManager(), chunkGenerator, chunkGenerator.getBiomeSource(), world.getChunkManager().getNoiseConfig(), structureTemplateManager, world.getSeed(), new ChunkPos(pos), world, (biome) -> {
            return true;
        });
        Optional<Structure.StructurePosition> optional = generate(context, structurePool, Optional.of(id), size, pos, false, Optional.empty(), 128);
        if (optional.isPresent()) {
            StructurePiecesCollector structurePiecesCollector = ((Structure.StructurePosition)optional.get()).generate();
            Iterator var13 = structurePiecesCollector.toList().pieces().iterator();

            while(var13.hasNext()) {
                StructurePiece structurePiece = (StructurePiece)var13.next();
                if (structurePiece instanceof PoolStructurePiece) {
                    PoolStructurePiece poolStructurePiece = (PoolStructurePiece)structurePiece;
                    poolStructurePiece.generate(world, structureAccessor, chunkGenerator, random, BlockBox.infinite(), pos, keepJigsaws);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    static final class StructurePoolGenerator {
        private final Registry<StructurePool> registry;
        private final int maxSize;
        private final ChunkGenerator chunkGenerator;
        private final StructureTemplateManager structureTemplateManager;
        private final List<? super PoolStructurePiece> children;
        private final Random random;
        final Deque<ShapedPoolStructurePiece> structurePieces = Queues.newArrayDeque();

        StructurePoolGenerator(Registry<StructurePool> registry, int maxSize, ChunkGenerator chunkGenerator, StructureTemplateManager structureTemplateManager, List<? super PoolStructurePiece> children, Random random) {
            this.registry = registry;
            this.maxSize = maxSize;
            this.chunkGenerator = chunkGenerator;
            this.structureTemplateManager = structureTemplateManager;
            this.children = children;
            this.random = random;
        }

        void generatePiece(PoolStructurePiece piece, MutableObject<VoxelShape> pieceShape, int minY, boolean modifyBoundingBox, HeightLimitView world, NoiseConfig noiseConfig) {
            StructurePoolElement structurePoolElement = piece.getPoolElement();
            BlockPos blockPos = piece.getPos();
            BlockRotation blockRotation = piece.getRotation();
            StructurePool.Projection projection = structurePoolElement.getProjection();
            boolean bl = projection == Projection.RIGID;
            MutableObject<VoxelShape> mutableObject = new MutableObject();
            BlockBox blockBox = piece.getBoundingBox();
            int i = blockBox.getMinY();
            Iterator var15 = structurePoolElement.getStructureBlockInfos(this.structureTemplateManager, blockPos, blockRotation, this.random).iterator();

            while(true) {
                label129:
                while(var15.hasNext()) {
                    StructureTemplate.StructureBlockInfo structureBlockInfo = (StructureTemplate.StructureBlockInfo)var15.next();
                    Direction direction = JigsawBlock.getFacing(structureBlockInfo.state());
                    BlockPos blockPos2 = structureBlockInfo.pos();
                    BlockPos blockPos3 = blockPos2.offset(direction);
                    int j = blockPos2.getY() - i;
                    int k = -1;
                    RegistryKey<StructurePool> registryKey = getPoolKey(structureBlockInfo);
                    Optional<? extends RegistryEntry<StructurePool>> optional = this.registry.getEntry(registryKey);
                    if (optional.isEmpty()) {
                        TreePoolGenerator.LOGGER.warn("Empty or non-existent pool: {}", registryKey.getValue());
                    } else {
                        RegistryEntry<StructurePool> registryEntry = (RegistryEntry)optional.get();
                        if (((StructurePool)registryEntry.value()).getElementCount() == 0 && !registryEntry.matchesKey(StructurePools.EMPTY)) {
                            TreePoolGenerator.LOGGER.warn("Empty or non-existent pool: {}", registryKey.getValue());
                        } else {
                            RegistryEntry<StructurePool> registryEntry2 = ((StructurePool)registryEntry.value()).getFallback();
                            if (((StructurePool)registryEntry2.value()).getElementCount() == 0 && !registryEntry2.matchesKey(StructurePools.EMPTY)) {
                                TreePoolGenerator.LOGGER.warn("Empty or non-existent fallback pool: {}", registryEntry2.getKey().map((key) -> {
                                    return key.getValue().toString();
                                }).orElse("<unregistered>"));
                            } else {
                                boolean bl2 = blockBox.contains(blockPos3);
                                MutableObject mutableObject2;
                                if (bl2) {
                                    mutableObject2 = mutableObject;
                                    if (mutableObject.getValue() == null) {
                                        mutableObject.setValue(VoxelShapes.cuboid(Box.from(blockBox)));
                                    }
                                } else {
                                    mutableObject2 = pieceShape;
                                }

                                List<StructurePoolElement> list = Lists.newArrayList();
                                if (minY != this.maxSize) {
                                    list.addAll(((StructurePool)registryEntry.value()).getElementIndicesInRandomOrder(this.random));
                                }

                                list.addAll(((StructurePool)registryEntry2.value()).getElementIndicesInRandomOrder(this.random));
                                Iterator var29 = list.iterator();

                                while(var29.hasNext()) {
                                    StructurePoolElement structurePoolElement2 = (StructurePoolElement)var29.next();
                                    if (structurePoolElement2 == EmptyPoolElement.INSTANCE) {
                                        break;
                                    }

                                    Iterator var31 = BlockRotation.randomRotationOrder(this.random).iterator();

                                    label125:
                                    while(var31.hasNext()) {
                                        BlockRotation blockRotation2 = (BlockRotation)var31.next();
                                        List<StructureTemplate.StructureBlockInfo> list2 = structurePoolElement2.getStructureBlockInfos(this.structureTemplateManager, BlockPos.ORIGIN, blockRotation2, this.random);
                                        BlockBox blockBox2 = structurePoolElement2.getBoundingBox(this.structureTemplateManager, BlockPos.ORIGIN, blockRotation2);
                                        int l;
                                        if (modifyBoundingBox && blockBox2.getBlockCountY() <= 16) {
                                            l = list2.stream().mapToInt((blockInfo) -> {
                                                if (!blockBox2.contains(blockInfo.pos().offset(JigsawBlock.getFacing(blockInfo.state())))) {
                                                    return 0;
                                                } else {
                                                    RegistryKey<StructurePool> registryKey2 = getPoolKey(blockInfo);
                                                    Optional<? extends RegistryEntry<StructurePool>> optional3 = this.registry.getEntry(registryKey2);
                                                    Optional<RegistryEntry<StructurePool>> optional2 = optional3.map((entry) -> {
                                                        return ((StructurePool)entry.value()).getFallback();
                                                    });
                                                    int o = (Integer)optional3.map((entry) -> {
                                                        return ((StructurePool)entry.value()).getHighestY(this.structureTemplateManager);
                                                    }).orElse(0);
                                                    int p = (Integer)optional2.map((entry) -> {
                                                        return ((StructurePool)entry.value()).getHighestY(this.structureTemplateManager);
                                                    }).orElse(0);
                                                    return Math.max(o, p);
                                                }
                                            }).max().orElse(0);
                                        } else {
                                            l = 0;
                                        }

                                        Iterator var36 = list2.iterator();

                                        StructurePool.Projection projection2;
                                        boolean bl3;
                                        int n;
                                        int o;
                                        int p;
                                        BlockBox blockBox4;
                                        BlockPos blockPos6;
                                        int r;
                                        do {
                                            StructureTemplate.StructureBlockInfo structureBlockInfo2;
                                            do {
                                                if (!var36.hasNext()) {
                                                    continue label125;
                                                }

                                                structureBlockInfo2 = (StructureTemplate.StructureBlockInfo)var36.next();
                                            } while(!JigsawBlock.attachmentMatches(structureBlockInfo, structureBlockInfo2));

                                            BlockPos blockPos4 = structureBlockInfo2.pos();
                                            BlockPos blockPos5 = blockPos3.subtract(blockPos4);
                                            BlockBox blockBox3 = structurePoolElement2.getBoundingBox(this.structureTemplateManager, blockPos5, blockRotation2);
                                            int m = blockBox3.getMinY();
                                            projection2 = structurePoolElement2.getProjection();
                                            bl3 = projection2 == Projection.RIGID;
                                            n = blockPos4.getY();
                                            o = j - n + JigsawBlock.getFacing(structureBlockInfo.state()).getOffsetY();
                                            if (bl && bl3) {
                                                p = i + o;
                                            } else {
                                                if (k == -1) {
                                                    k = this.chunkGenerator.getHeightOnGround(blockPos2.getX(), blockPos2.getZ(), Type.WORLD_SURFACE_WG, world, noiseConfig);
                                                }

                                                p = k - n;
                                            }

                                            int q = p - m;
                                            blockBox4 = blockBox3.offset(0, q, 0);
                                            blockPos6 = blockPos5.add(0, q, 0);
                                            if (l > 0) {
                                                r = Math.max(l + 1, blockBox4.getMaxY() - blockBox4.getMinY());
                                                blockBox4.encompass(new BlockPos(blockBox4.getMinX(), blockBox4.getMinY() + r, blockBox4.getMinZ()));
                                            }
                                        } while(VoxelShapes.matchesAnywhere((VoxelShape)mutableObject2.getValue(), VoxelShapes.cuboid(Box.from(blockBox4).contract(0.25)), BooleanBiFunction.ONLY_SECOND));

                                        mutableObject2.setValue(VoxelShapes.combine((VoxelShape)mutableObject2.getValue(), VoxelShapes.cuboid(Box.from(blockBox4)), BooleanBiFunction.ONLY_FIRST));
                                        r = piece.getGroundLevelDelta();
                                        int s;
                                        if (bl3) {
                                            s = r - o;
                                        } else {
                                            s = structurePoolElement2.getGroundLevelDelta();
                                        }

                                        PoolStructurePiece poolStructurePiece = new PoolStructurePiece(this.structureTemplateManager, structurePoolElement2, blockPos6, s, blockRotation2, blockBox4);
                                        int t;
                                        if (bl) {
                                            t = i + j;
                                        } else if (bl3) {
                                            t = p + n;
                                        } else {
                                            if (k == -1) {
                                                k = this.chunkGenerator.getHeightOnGround(blockPos2.getX(), blockPos2.getZ(), Type.WORLD_SURFACE_WG, world, noiseConfig);
                                            }

                                            t = k + o / 2;
                                        }

                                        piece.addJunction(new JigsawJunction(blockPos3.getX(), t - j + r, blockPos3.getZ(), o, projection2));
                                        poolStructurePiece.addJunction(new JigsawJunction(blockPos2.getX(), t - n + s, blockPos2.getZ(), -o, projection));
                                        this.children.add(poolStructurePiece);
                                        if (minY + 1 <= this.maxSize) {
                                            this.structurePieces.addLast(new ShapedPoolStructurePiece(poolStructurePiece, mutableObject2, minY + 1));
                                        }
                                        continue label129;
                                    }
                                }
                            }
                        }
                    }
                }

                return;
            }
        }

        private static RegistryKey<StructurePool> getPoolKey(StructureTemplate.StructureBlockInfo blockInfo) {
            return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, new Identifier(blockInfo.nbt().getString("pool")));
        }
    }

    private static final class ShapedPoolStructurePiece {
        final PoolStructurePiece piece;
        final MutableObject<VoxelShape> pieceShape;
        final int currentSize;

        ShapedPoolStructurePiece(PoolStructurePiece piece, MutableObject<VoxelShape> pieceShape, int currentSize) {
            this.piece = piece;
            this.pieceShape = pieceShape;
            this.currentSize = currentSize;
        }
    }
}
