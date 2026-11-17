package com.ruby.throwingdisc.item;

import com.ruby.throwingdisc.entity.DiscProjectileEntity;
import com.ruby.throwingdisc.registry.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DiscItem extends Item {
    private final DiscTier tier;

    public DiscItem(DiscTier tier) {
        super(buildProperties(tier));
        this.tier = tier;
    }

    private static Properties buildProperties(DiscTier tier) {
        Properties props = new Properties().stacksTo(1).durability(tier.durability());
        if (tier.fireResistant()) {
            props = props.fireResistant();
        }
        return props;
    }

    public DiscTier getTier() {
        return tier;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        // Void metal slowly repairs itself while carried (1 point per 2 s).
        if (tier.selfRepairs() && !level.isClientSide && stack.isDamaged()
                && level.getGameTime() % 40 == 0) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return tier.enchantability();
    }

    // Which enchantments the disc accepts is controlled by data instead of
    // code in 1.21: the discs are listed in the item tag
    // data/minecraft/tags/item/enchantable/durability.json, which is exactly
    // the supported-items tag of Unbreaking and Mending.

    /**
     * Throw the disc. The real stack (damage, enchantments) moves into the
     * projectile entity and the hand is emptied, so the disc can never be
     * duplicated by throwing it. It comes back to the same hand on catch.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            DiscProjectileEntity disc = new DiscProjectileEntity(ModEntities.DISC_PROJECTILE.get(), level);
            disc.setOwner(player);
            disc.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            disc.setDiscStack(stack.copy());
            disc.setFromHand(hand);
            disc.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f,
                    DiscProjectileEntity.BASE_SPEED * tier.speedFactor(), 0.0f);
            level.addFreshEntity(disc);
            level.playSound(null, player.blockPosition(), SoundEvents.TRIDENT_THROW.value(),
                    SoundSource.PLAYERS, 1.0f, 1.0f);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        player.setItemInHand(hand, ItemStack.EMPTY);
        return InteractionResultHolder.success(ItemStack.EMPTY);
    }
}
