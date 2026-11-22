package com.ruby.throwingdisc;

import com.ruby.throwingdisc.config.DiscConfig;
import com.ruby.throwingdisc.registry.ModCreativeTabs;
import com.ruby.throwingdisc.registry.ModEntities;
import com.ruby.throwingdisc.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(ThrowingDiscMod.MOD_ID)
public class ThrowingDiscMod {
    public static final String MOD_ID = "throwing_disc";

    public ThrowingDiscMod(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, DiscConfig.SPEC);
    }
}
