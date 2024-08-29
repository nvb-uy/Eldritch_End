package elocindev.eldritch_end.item.dark_magic;

import elocindev.eldritch_end.api.RitualAPI;
import elocindev.eldritch_end.api.RitualAPI.RitualStructure;
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

    public SummonPartItem(Settings settings, RitualStructure ritualStructure, EntityType<?> summon) {
        super(settings);
        
        this.ritualStructure = ritualStructure;
        this.summon = summon;        
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

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.PASS;

        if (RitualAPI.isBasicRitual(context.getBlockPos(), world)) {
            context.getPlayer().sendMessage(Text.of("Ritual is valid!"), true);
        }        
 
        return ActionResult.PASS;
    }
}
