package com.ruby.throwingdisc.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/** A throwing-disc enchantment with a simple linear cost curve. */
public class DiscEnchantment extends Enchantment {
    private final int maxLevel;
    private final int baseCost;
    private final int costPerLevel;

    public DiscEnchantment(Rarity rarity, EnchantmentCategory category,
                           int maxLevel, int baseCost, int costPerLevel) {
        super(rarity, category, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
        this.maxLevel = maxLevel;
        this.baseCost = baseCost;
        this.costPerLevel = costPerLevel;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public int getMinCost(int level) {
        return this.baseCost + (level - 1) * this.costPerLevel;
    }

    @Override
    public int getMaxCost(int level) {
        return getMinCost(level) + 50;
    }
}
