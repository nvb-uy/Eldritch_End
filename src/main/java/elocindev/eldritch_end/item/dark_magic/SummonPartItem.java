package elocindev.eldritch_end.item.dark_magic;

import elocindev.eldritch_end.api.RitualAPI;
import elocindev.eldritch_end.api.RitualAPI.RitualStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class SummonPartItem extends Item {
    private EntityType<?> summon;
    private RitualStructure ritualStructure;
    private Block mainSummonBlock;
    private BlockState aftermathBlock;

    public SummonPartItem(Settings settings, RitualStructure ritualStructure, Block mainSummonBlock, BlockState aftermathBlock, EntityType<?> summon) {
        super(settings);
        
        this.ritualStructure = ritualStructure;
        this.summon = summon;
        this.mainSummonBlock = mainSummonBlock;
        this.aftermathBlock = aftermathBlock;
    }
    
    public Entity createBoss(World world, double x, double y, double z) {
        Entity boss = summon.create(world);
        boss.setPos(x, y, z);

        return boss;
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

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.PASS;

        if (RitualAPI.isBasicRitual(context.getBlockPos(), world)) {
            // this is temporary, pls delete
            context.getPlayer().sendMessage(Text.of("Ritual is valid!"), true);

            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(getMainSummonBlock())) {
                context.getPlayer().getStackInHand(context.getHand()).decrement(1);

                createBoss(world, context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ());

                world.setBlockState(context.getBlockPos(), getAftermathBlock());

                return ActionResult.SUCCESS;
            }
        }        
 
        return ActionResult.PASS;
    }
}
