package com.ruby.throwingdisc.item;

import com.ruby.throwingdisc.entity.DiscProjectileEntity;
import com.ruby.throwingdisc.registry.ModEnchantments;
import com.ruby.throwingdisc.registry.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
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
    public int getEnchantmentValue(ItemStack stack) {
        return tier.enchantability();
    }

    /**
     * Mojang has not announced which enchantments the vanilla disc takes.
     * This mod allows Unbreaking and Mending, plus its own three disc
     * enchantments: Delicate, Lingering and Splitting.
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.UNBREAKING || enchantment == Enchantments.MENDING
                || enchantment == ModEnchantments.DELICATE.get()
                || enchantment == ModEnchantments.LINGERING.get()
                || enchantment == ModEnchantments.SPLITTING.get();
    }

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
                    DiscProjectileEntity.baseSpeed() * tier.speedFactor(), 0.0f);
            level.addFreshEntity(disc);
            // Splitting: one extra disc per level, fanned out beside the
            // real one. The copies are phantoms — they vanish on return and
            // never drop or duplicate the item.
            int splitting = EnchantmentHelper.getItemEnchantmentLevel(
                    ModEnchantments.SPLITTING.get(), stack);
            for (int i = 1; i <= splitting; i++) {
                float yawOffset = ((i + 1) / 2) * 4.0f * (i % 2 == 1 ? -1.0f : 1.0f);
                DiscProjectileEntity copy = new DiscProjectileEntity(ModEntities.DISC_PROJECTILE.get(), level);
                copy.setOwner(player);
                copy.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
                copy.setDiscStack(stack.copy());
                copy.setFromHand(hand);
                copy.setSplitCopy(true);
                copy.shootFromRotation(player, player.getXRot(), player.getYRot() + yawOffset, 0.0f,
                        DiscProjectileEntity.baseSpeed() * tier.speedFactor(), 0.0f);
                level.addFreshEntity(copy);
            }
            level.playSound(null, player.blockPosition(), SoundEvents.TRIDENT_THROW,
                    SoundSource.PLAYERS, 1.0f, 1.0f);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        player.setItemInHand(hand, ItemStack.EMPTY);
        return InteractionResultHolder.success(ItemStack.EMPTY);
    }
}
