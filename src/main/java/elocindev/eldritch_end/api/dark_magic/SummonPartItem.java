package elocindev.eldritch_end.api.dark_magic;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.api.RitualAPI;
import elocindev.eldritch_end.api.RitualAPI.RitualStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.List;

public class SummonPartItem extends Item {
    private EntityType<?> summon;
    private RitualStructure ritualStructure;
    private Block mainSummonBlock;
    private BlockState aftermathBlock;

    private boolean shouldSpawnLightning = true;

    @Nullable private List<Identifier> allowed_biomes = null;

    public SummonPartItem(Settings settings, RitualStructure ritualStructure, Block mainSummonBlock, BlockState aftermathBlock, EntityType<?> summon) {
        super(settings);
        
        this.ritualStructure = ritualStructure;
        this.summon = summon;
        this.mainSummonBlock = mainSummonBlock;
        this.aftermathBlock = aftermathBlock;
    }

    public SummonPartItem(Settings settings, RitualStructure ritualStructure, Block mainSummonBlock, BlockState aftermathBlock, EntityType<?> summon, boolean shouldSpawnLightning) {
        this(settings, ritualStructure, mainSummonBlock, aftermathBlock, summon);
        this.shouldSpawnLightning = shouldSpawnLightning;
    }

    public SummonPartItem(Settings settings, RitualStructure ritualStructure, Block mainSummonBlock, BlockState aftermathBlock, EntityType<?> summon, boolean shouldSpawnLightning, List<Identifier> allowed_biomes) {
        this(settings, ritualStructure, mainSummonBlock, aftermathBlock, summon, shouldSpawnLightning);
        this.allowed_biomes = allowed_biomes;
    }
    
    public Entity createBoss(World world, double x, double y, double z) {
        Entity boss = summon.create(world);
        boss.setPos(x, y, z);

        return boss;
    }

    public boolean shouldSpawnLightning() {
        return shouldSpawnLightning;
    }

    public EntityType<?> getSummonableEntityType() {
        return summon;
    }

    public RitualStructure getRitualStructure() {
        return ritualStructure;
    }

    public Block getMainSummonBlock() {
        return mainSummonBlock;
    }

    public BlockState getAftermathBlock() {
        return aftermathBlock;
    }

    public boolean getSummoningConditions(BlockPos pos, World world) {
        return RitualAPI.isBasicRitual(pos, world);
    }

    public static void playInteractionEffect(BlockPos pos, World world) {
        world.addParticle(ParticleTypes.SQUID_INK, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0.05F, 0);
        world.playSound(null, pos, net.minecraft.sound.SoundEvents.BLOCK_MUD_HIT, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, 1.3F);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.PASS;

        playInteractionEffect(context.getBlockPos(), world);
        context.getStack().decrement(1);

        if (this.getSummoningConditions(context.getBlockPos(), world)) {
            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(getMainSummonBlock())) {
                if (allowed_biomes != null) {
                    var biome = world.getBiome(context.getBlockPos());

                    for (var allowed : allowed_biomes) {
                        if (!biome.matchesId(allowed)) {

                            return ActionResult.FAIL;
                        }
                    }
                }

                context.getPlayer().getStackInHand(context.getHand()).decrement(1);

                var boss = createBoss(world, context.getBlockPos().getX(), context.getBlockPos().getY()+2, context.getBlockPos().getZ());
                world.spawnEntity(boss);

                world.setBlockState(context.getBlockPos(), getAftermathBlock());

                if (shouldSpawnLightning) {
                    for (int x = -2; x <= 2; x++) {
                        for (int z = -2; z <= 2; z++) {
                            var ent = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                            ent.setCosmetic(true);
                            ent.setPos(context.getBlockPos().getX() + x, context.getBlockPos().getY(), context.getBlockPos().getZ() + z);

                            world.spawnEntity(ent);
                        }
                    }
                }

                return ActionResult.SUCCESS;
            }
        }        
 
        return ActionResult.PASS;
    }
}
