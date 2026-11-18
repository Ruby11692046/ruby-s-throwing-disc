package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.item.DiscItem;
import com.ruby.throwingdisc.item.DiscTier;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ThrowingDiscMod.MOD_ID);

    /**
     * The disc items that exist in this install, keyed by tier, in
     * tier-declaration order. A compat disc is registered only while its
     * home mod is installed; without the mod, the disc does not exist.
     */
    public static final Map<DiscTier, DeferredItem<DiscItem>> DISCS = new EnumMap<>(DiscTier.class);

    static {
        for (DiscTier tier : DiscTier.values()) {
            String required = tier.requiredMod();
            if (required != null && !ModList.get().isLoaded(required)) {
                continue;
            }
            DISCS.put(tier, ITEMS.register(tier.registryName(), () -> new DiscItem(tier)));
        }
    }
}
