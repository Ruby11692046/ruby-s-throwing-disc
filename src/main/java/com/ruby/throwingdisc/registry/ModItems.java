package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.item.DiscItem;
import com.ruby.throwingdisc.item.DiscTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ThrowingDiscMod.MOD_ID);

    /** Every disc item, keyed by tier, in tier-declaration order. */
    public static final Map<DiscTier, DeferredItem<DiscItem>> DISCS = new EnumMap<>(DiscTier.class);

    static {
        for (DiscTier tier : DiscTier.values()) {
            DISCS.put(tier, ITEMS.register(tier.registryName(), () -> new DiscItem(tier)));
        }
    }
}
