package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.item.DiscItem;
import com.ruby.throwingdisc.item.DiscTier;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThrowingDiscMod.MOD_ID);

    /**
     * The disc items that exist in this install, keyed by tier, in
     * tier-declaration order. A compat disc is registered only while its
     * home mod is installed; without the mod, the disc does not exist.
     */
    public static final Map<DiscTier, RegistryObject<Item>> DISCS = new EnumMap<>(DiscTier.class);

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
