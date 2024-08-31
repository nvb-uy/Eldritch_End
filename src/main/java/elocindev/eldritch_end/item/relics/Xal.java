package elocindev.eldritch_end.item.relics;

import java.util.List;
import java.util.UUID;

import elocindev.eldritch_end.effects.Corruption;
import elocindev.eldritch_end.registry.SoundEffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import elocindev.eldritch_end.api.infusion.InfusableItemMaterial;
import elocindev.eldritch_end.api.infusion.InfusionAttributeHolder;
import elocindev.eldritch_end.client.particle.EldritchParticles;
import elocindev.eldritch_end.config.Configs;
import elocindev.eldritch_end.item.relics.base.CorruptionRelic;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.registry.ItemRegistry;
import elocindev.eldritch_end.worldgen.util.TextUtils;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Xal extends CorruptionRelic implements InfusableItemMaterial {
    private final String ATTACK_PROGRESS = "attackProgress";
    private final String TARGET_X = "targetX";
    private final String TARGET_Y = "targetY";
    private final String TARGET_Z = "targetZ";
    private final int ATTACK_DURATION = 94;

    public Xal(Settings settings) {
        super(settings);
    }

    private int getSurgeProgress(ItemStack stack) {
        return stack.getNbt().getInt(ATTACK_PROGRESS);
    }

    private void shadowSurge(PlayerEntity player, ItemStack stack, World world) {
        int targetX = stack.getNbt().getInt(TARGET_X);
        int targetY = stack.getNbt().getInt(TARGET_Y);
        int targetZ = stack.getNbt().getInt(TARGET_Z);

        world.playSound((PlayerEntity)null, targetX, targetY, targetZ, SoundEffectRegistry.ORB_EVENT, player.getSoundCategory(), 1F, 1.0f);
        for (Entity entity: world.getEntitiesByClass(Entity.class, new Box(new BlockPos(targetX, targetY, targetZ)).expand(6, 3, 6), entity -> true)) {
            entity.damage(Corruption.of(world, Corruption.DAMAGE), Configs.Items.XAL.DAMAGE_PER_STRIKE);
        }
    }

    // Hits occur at 43, 52, 63
    // hi joe nicole was here :D

    @SuppressWarnings("resource")
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (stack.getNbt() == null || entity.getWorld().isClient || !(entity instanceof PlayerEntity player)) return;
        if (getSurgeProgress(stack) == 43 || getSurgeProgress(stack) == 52 || getSurgeProgress(stack) == 63) {
            shadowSurge(player, stack, world);
        }
        if (getSurgeProgress(stack) < ATTACK_DURATION) {
            stack.getNbt().putInt(ATTACK_PROGRESS, getSurgeProgress(stack) + 1);
        }
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText type = TextAPI.Styles.getGradient(Text.translatable("item.eldritch_end.xal.type"), 1, 0x5c3885, 0x8e4ecf, 1.0F);
        MutableText ability_icon = Text.empty().append("\uA996 ");
        MutableText ability_damage = Text.empty().append(Configs.Items.XAL.DAMAGE_PER_STRIKE+" damage").setStyle(TextUtils.Styles.DAMAGE_CORRUPTION);

        tooltip.add(type.fillStyle(type.getStyle().withUnderline(true)));

        tooltip.add(emptyLine());

        MutableText shadowburst = Text.empty().append("Shadowburst");
        shadowburst.setStyle(TextUtils.Styles.CORRUPTION_ABILITY);
        ability_icon.append(shadowburst);       

        tooltip.add(ability_icon);

        MutableText description1 = Text.translatable("item.eldritch_end.xal.shadowburst.1").setStyle(TextUtils.Styles.DESCRIPTION);
        MutableText description2 = Text.translatable("item.eldritch_end.xal.shadowburst.2").setStyle(TextUtils.Styles.DESCRIPTION);

        tooltip.add(Text.literal(" ").append(description1).append(Text.literal(" \uA997 ").append(ability_damage)));
        tooltip.add(Text.literal(" ").append(description2));

        tooltip.add(emptyLine());

        var appliesto = Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor && Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons ? 
            "infusion.eldritch_end.applies_to_all" : 
            Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor ? 
                "infusion.eldritch_end.applies_to_armor" : 
                "infusion.eldritch_end.applies_to_weapons";

        tooltip.add(Text.translatable("infusion.eldritch_end.infusable").append(Text.translatable(appliesto)).append(" (Tier III)").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
    }

    private Text emptyLine() {
        return Text.literal(" ");
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(super.getAttributeModifiers(slot));

        UUID uuid = UUID.fromString("399fe278-8564-11ee-b9d1-0242ac120002");

        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND)
            modifiers.put(
                    AttributeRegistry.CORRUPTION,
                    new EntityAttributeModifier(
                            uuid,
                            "Corruption modifier",
                            10.0,
                            EntityAttributeModifier.Operation.ADDITION
                    )
            );

        return modifiers;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getNbt() == null) createStackNBT(itemStack);

        if (itemStack.getNbt().getInt(ATTACK_PROGRESS) == ATTACK_DURATION) {
            itemStack.getNbt().putInt(ATTACK_PROGRESS, 0);

            itemStack.getNbt().putInt(TARGET_X, (int) user.getPos().x);
            itemStack.getNbt().putInt(TARGET_Y, (int) user.getPos().y);
            itemStack.getNbt().putInt(TARGET_Z, (int) user.getPos().z);
        }

        EldritchParticles.playEffek("shadowsurge", world, user.getPos(), true, 0.30F)
            .bindOnEntity(user);
        user.getItemCooldownManager().set(this, Configs.Items.XAL.COOLDOWN_TICKS);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private void createStackNBT(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putInt(ATTACK_PROGRESS, 0);
    }

    @Override
    public List<InfusionAttributeHolder> getInfusionAttributes() {
        return List.of(new InfusionAttributeHolder(AttributeRegistry.CORRUPTION, 35, InfusionAttributeHolder.Presets.CORRUPTION));
    }

    @Override
    public Item getInfusionTemplate() {
        return ItemRegistry.CORRUPTION_UPGRADE_PATTERN;
    }

    @Override
    public boolean applyToArmor() {
        return Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_armor;
    }

    @Override
    public boolean applyToWeapons() {
        return Configs.Mechanics.INFUSIONS.corruption_infusion.can_apply_to_weapons;
    }

    @Override
    public List<String> canSwapInfusionTo() {
        return List.of(
            "eldritch_end:aberration_heart",
            "eldritch_end:aberration_limb"
        );
    }
}
