package com.ruby.throwingdisc;

import com.ruby.throwingdisc.config.DiscConfig;
import com.ruby.throwingdisc.registry.ModCreativeTabs;
import com.ruby.throwingdisc.registry.ModEnchantments;
import com.ruby.throwingdisc.registry.ModEntities;
import com.ruby.throwingdisc.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ThrowingDiscMod.MOD_ID)
public class ThrowingDiscMod {
    public static final String MOD_ID = "throwing_disc";

    public ThrowingDiscMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModEnchantments.ENCHANTMENTS.register(bus);
        ModCreativeTabs.CREATIVE_TABS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DiscConfig.SPEC);
    }
}
