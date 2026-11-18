package com.ruby.throwingdisc.enchantment;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * In 1.21 the disc enchantments are datapack-defined
 * (data/throwing_disc/enchantment/*.json), so the code only needs their
 * levels, looked up by registry id without a compile-time holder.
 */
public final class DiscEnchants {
    /** Bounces off creatures instead of piercing them. */
    public static final String DELICATE = "delicate";
    /** +2 seconds of flight and +13 blocks of range per level. */
    public static final String LINGERING = "lingering";
    /** One extra disc per level, thrown in a fan. */
    public static final String SPLITTING = "splitting";

    private DiscEnchants() {
    }

    public static int level(ItemStack stack, String name) {
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : stack.getEnchantments().entrySet()) {
            ResourceKey<Enchantment> key = entry.getKey().getKey();
            if (key != null && key.location().getNamespace().equals("throwing_disc")
                    && key.location().getPath().equals(name)) {
                return entry.getIntValue();
            }
        }
        return 0;
    }
}
